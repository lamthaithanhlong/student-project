package mscs.hms.dto.selectors;

import mscs.hms.model.Apartment;
import mscs.hms.model.Property;
import mscs.hms.model.House;

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
        return entityObject.getName().toString();
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
        if(!(obj.getClass() != Property.class || obj.getClass() == Apartment.class || obj.getClass() == House.class))
        {
            return (obj.getClass() == this.getClass() && obj.equals(this));
        }
        if(obj instanceof Property){
            Property other = (Property)obj;
            return this.id.equals(other.getId());
        }
        return false;
    }
}
