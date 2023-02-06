package mscs.hms.dto.selectors;

import mscs.hms.model.Role;

public class RoleSelectorDTO extends SelectorDTO<Integer, Role> {

    public RoleSelectorDTO(Role entityObject) {
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
    public Integer getId() {
        return entityObject.getId();
    }
    
    @Override
    public SelectorDTO<Integer, Role> createDTOObject(Role role){
        return new RoleSelectorDTO(role);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != Role.class)
        {
            return (obj.getClass() == this.getClass() && (((RoleSelectorDTO)obj).equals(this)));
        }
        Role other = (Role)obj;
        return this.id.equals(other.getId());
    }
}
