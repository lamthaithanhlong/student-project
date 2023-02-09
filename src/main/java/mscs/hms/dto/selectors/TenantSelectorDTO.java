package mscs.hms.dto.selectors;

import mscs.hms.model.Tenant;

public class TenantSelectorDTO extends SelectorDTO<Long, Tenant> {

    public TenantSelectorDTO(Tenant entityObject) {
        super(entityObject);
    }
    @Override    
    public void setParentId(){
        this.id = entityObject.getId();
    }
    @Override
    public String getDisplayText() {
        return entityObject.getName();
    }

    @Override
    public Long getId() {
        return entityObject.getId();
    }
    
    @Override
    public SelectorDTO<Long, Tenant> createDTOObject(Tenant tenant){
        return new TenantSelectorDTO(tenant);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != Tenant.class)
        {
            return (obj.getClass() == this.getClass() && (((TenantSelectorDTO)obj).equals(this)));
        }
        Tenant other = (Tenant)obj;
        return this.id.equals(other.getId());
    }
}
