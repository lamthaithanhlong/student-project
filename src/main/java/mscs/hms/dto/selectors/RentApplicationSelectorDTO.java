package mscs.hms.dto.selectors;

import mscs.hms.model.RentApplication;

public class RentApplicationSelectorDTO extends SelectorDTO<Integer, RentApplication> {

    public RentApplicationSelectorDTO(RentApplication entityObject) {
        super(entityObject);
    }
    @Override    
    public void setParentId(){
        this.id = entityObject.getId();
    }
    @Override
    public String getDisplayText() {
        return entityObject.getApplicationId()+ "," + entityObject.getTitle();
    }

    @Override
    public Integer getId() {
        return entityObject.getId();
    }
    
    @Override
    public SelectorDTO<Integer, RentApplication> createDTOObject(RentApplication address){
        return new RentApplicationSelectorDTO(address);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != RentApplication.class)
        {
            return (obj.getClass() == this.getClass() && (((RentApplicationSelectorDTO)obj).equals(this)));
        }
        RentApplication other = (RentApplication)obj;
        return this.id.equals(other.getId());
    }
}
