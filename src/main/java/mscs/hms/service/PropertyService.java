package mscs.hms.service;

import mscs.hms.model.Property;
import mscs.hms.dto.paging.Page;
import mscs.hms.dto.paging.PageArray;
import mscs.hms.dto.paging.PagingRequest;

public interface PropertyService {
    public Property saveProperty(Property property);
    public Page<Property> getProperties(PagingRequest pagingRequest);
    public PageArray getPropertyArray(PagingRequest pagingRequest);
}
