package mscs.hms.service;

import mscs.hms.entity.Property;
import mscs.hms.entity.paging.Page;
import mscs.hms.entity.paging.PageArray;
import mscs.hms.entity.paging.PagingRequest;

public interface PropertyService {
    public Property saveProperty(Property property);
    public Page<Property> getProperties(PagingRequest pagingRequest);
    public PageArray getPropertyArray(PagingRequest pagingRequest);
}
