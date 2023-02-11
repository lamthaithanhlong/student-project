package mscs.hms.service.impl;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import mscs.hms.model.Apartment;
import mscs.hms.model.House;
import mscs.hms.model.Property;
import mscs.hms.helper.PropertyComparators;
import mscs.hms.dto.paging.Column;
import mscs.hms.dto.paging.Order;
import mscs.hms.dto.paging.Page;
import mscs.hms.dto.paging.PageArray;
import mscs.hms.dto.paging.PagingRequest;
import mscs.hms.repository.ApartmentRepository;
import mscs.hms.repository.HouseRepository;
import mscs.hms.service.PropertyService;

@Service
public class PropertyServiceImpl extends AbsBaseService implements PropertyService {

    @Autowired
    HouseRepository houseRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Override
    public Property saveProperty(Property property) {
        if (property.getClass() == House.class)
            return houseRepository.save((House) property);
        else
            return apartmentRepository.save((Apartment) property);
    }

    @Override
    public Page<Property> getProperties(PagingRequest pagingRequest) {
        return getPage(getProperties(), pagingRequest);
    }

    @Override
    public PageArray getPropertyArray(PagingRequest pagingRequest) {
        pagingRequest.setColumns(Stream.of("noOfBathRooms", "noOfRooms")
                .map(Column::new)
                .collect(Collectors.toList()));
        Page<Property> employeePage = getProperties(pagingRequest);

        PageArray pageArray = new PageArray();
        pageArray.setRecordsFiltered(employeePage.getRecordsFiltered());
        pageArray.setRecordsTotal(employeePage.getRecordsTotal());
        pageArray.setDraw(employeePage.getDraw());
        pageArray.setData(employeePage.getData()
                .stream()
                .map(this::toStringList)
                .collect(Collectors.toList()));
        return pageArray;
    }

    private static final Comparator<Property> EMPTY_COMPARATOR = (e1, e2) -> 0;

    private List<String> toStringList(Property employee) {
        return Arrays.asList(employee.getNoOfBathRooms().toString(), employee.getNoOfRooms().toString());
    }

    private Page<Property> getPage(List<Property> properties, PagingRequest pagingRequest) {
        List<Property> filtered = properties.stream()
                .sorted(sortProperties(pagingRequest))
                .filter(filterProperties(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .collect(Collectors.toList());

        long count = properties.stream()
                .filter(filterProperties(pagingRequest))
                .count();

        Page<Property> page = new Page<>(filtered);
        page.setRecordsFiltered((int) count);
        page.setRecordsTotal((int) count);
        page.setDraw(pagingRequest.getDraw());

        return page;
    }

    private Predicate<Property> filterProperties(PagingRequest pagingRequest) {
        if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch()
                .getValue())) {
            return employee -> true;
        }

        String value = pagingRequest.getSearch()
                .getValue();

        return employee -> employee.getNoOfBathRooms().toString()
                .toLowerCase()
                .contains(value)
                || employee.getNoOfRooms().toString()
                        .toLowerCase()
                        .contains(value);
    }

    private Comparator<Property> sortProperties(PagingRequest pagingRequest) {
        if (pagingRequest.getOrder() == null) {
            return EMPTY_COMPARATOR;
        }

        try {
            Order order = pagingRequest.getOrder()
                    .get(0);

            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns()
                    .get(columnIndex);

            Comparator<Property> comparator = PropertyComparators.getComparator(column.getData(), order.getDir());
            if (comparator == null) {
                return EMPTY_COMPARATOR;
            }

            return comparator;

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return EMPTY_COMPARATOR;
    }

    @Override
    public List<Property> getProperties() {
        List<Property> properties = new ArrayList<>();
        houseRepository.findAll().forEach(x -> properties.add(x));
        apartmentRepository.findAll().forEach(x -> properties.add(x));

        return properties;
    }

    @Override
    public Property getById(Integer id) {
        Property property = houseRepository.findById(id).orElse(null);
        if (property == null) {
            return property = apartmentRepository.findById(id).orElse(null);
        }
        return property;
    }

    public org.springframework.data.domain.Page<? extends Property> getAll(String searchString, Integer page,
            Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        org.springframework.data.domain.Page<House> resultHouse;
        org.springframework.data.domain.Page<Apartment> resultApartment;
        //TODO: Query only the remainig page size for apartments
        if (searchString == null || searchString.isBlank()){
            resultHouse = houseRepository.findAll(pageRequest);
            resultApartment = apartmentRepository.findAll(pageRequest);
        }
        else if (NumberUtils.isParsable(searchString)) {
            resultHouse = houseRepository.findByLandExtentOrNoOfRoomsOrNoOfBathRooms(Double.parseDouble(searchString), Integer.parseInt(searchString), Integer.parseInt(searchString), pageRequest);
            resultApartment = apartmentRepository.findByNoOfRoomsOrNoOfBathRooms(Integer.parseInt(searchString),Integer.parseInt(searchString), pageRequest);
        } else {
            resultHouse = houseRepository.findByNameContainsIgnoreCase(searchString, pageRequest);
            resultApartment = apartmentRepository.findByNameContainsIgnoreCase(searchString, pageRequest);
        }
        List<Property> resultList = new ArrayList<>();
        resultList.addAll(resultHouse.getContent());
        if(resultHouse.getNumberOfElements() < pageSize)
        {
            int remainigPageSize = pageSize - resultHouse.getNumberOfElements();
            List<Apartment> apartmentContent = resultApartment.getContent();
            resultList.addAll(apartmentContent.subList(0, remainigPageSize <= apartmentContent.size() ? remainigPageSize : apartmentContent.size()));
        }
        final org.springframework.data.domain.Page<Property> pageResult = new PageImpl<>(resultList, pageRequest, resultHouse.getTotalElements() + resultApartment.getTotalElements());
        
        return pageResult;
    }
}
