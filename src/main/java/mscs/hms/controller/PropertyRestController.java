package mscs.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mscs.hms.entity.Property;
import mscs.hms.entity.paging.Page;
import mscs.hms.entity.paging.PageArray;
import mscs.hms.entity.paging.PagingRequest;
import mscs.hms.service.PropertyService;

@RestController
@RequestMapping("properties")
public class PropertyRestController extends AbsBaseController {

    @Autowired
    private PropertyService propertyService;

    public PropertyRestController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public Page<Property> list(@RequestBody PagingRequest pagingRequest) {
        return propertyService.getProperties(pagingRequest);
    }

    @PostMapping("/array")
    public PageArray array(@RequestBody PagingRequest pagingRequest) {
        return propertyService.getPropertyArray(pagingRequest);
    }
}