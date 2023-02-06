package mscs.hms.dto.selectors;

import mscs.hms.model.Property;

public class PropertySelectorDTO extends SelectorDTO<Integer, Property> {

    public PropertySelectorDTO(Property entityObject) {
        super(entityObject);
    }
    @Override    
    public void setParentId(){
        this.id = entityObject.getId();
    }
    @Override
    public String getDisplayText() {
        return entityObject.getId().toString();
    }

    @Override
    public Integer getId() {
        return entityObject.getId();
    }
    
    @Override
    public SelectorDTO<Integer, Property> createDTOObject(Property address){
        return new PropertySelectorDTO(address);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != Property.class)
        {
            return (obj.getClass() == this.getClass() && (((PropertySelectorDTO)obj).equals(this)));
        }
        Property other = (Property)obj;
        return this.id.equals(other.getId());
    }
}
