package mscs.hms.service;

import mscs.hms.model.Property;
import mscs.hms.dto.paging.Page;
import mscs.hms.dto.paging.PageArray;
import mscs.hms.dto.paging.PagingRequest;
import java.util.List;

public interface PropertyService {
    public Property saveProperty(Property property);
    public Page<Property> getProperties(PagingRequest pagingRequest);
    public PageArray getPropertyArray(PagingRequest pagingRequest);
    public List<Property> getProperties();
    public Property getById(Integer id);
    public org.springframework.data.domain.Page<? extends Property> getAll(String searchString, Integer page, Integer pageSize);
}
