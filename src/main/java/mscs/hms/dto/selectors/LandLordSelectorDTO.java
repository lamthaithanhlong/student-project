package mscs.hms.dto.selectors;

import mscs.hms.model.Landlord;

public class LandLordSelectorDTO extends SelectorDTO<Integer, Landlord> {

    public LandLordSelectorDTO(Landlord entityObject) {
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
    public SelectorDTO<Integer, Landlord> createDTOObject(Landlord landLord){
        return new LandLordSelectorDTO(landLord);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != Landlord.class)
        {
            return (obj.getClass() == this.getClass() && (((LandLordSelectorDTO)obj).equals(this)));
        }
        Landlord other = (Landlord)obj;
        return this.id.equals(other.getId());
    }
}
