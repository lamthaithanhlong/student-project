package mscs.hms.dto.selectors;

import mscs.hms.model.Preference;

public class PreferenceSelectorDTO extends SelectorDTO<Long, Preference> {

    public PreferenceSelectorDTO(Preference entityObject) {
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
    public Long getId() {
        return entityObject.getId();
    }
    
    @Override
    public SelectorDTO<Long, Preference> createDTOObject(Preference address){
        return new PreferenceSelectorDTO(address);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != Preference.class)
        {
            return (obj.getClass() == this.getClass() && (((PreferenceSelectorDTO)obj).equals(this)));
        }
        Preference other = (Preference)obj;
        return this.id.equals(other.getId());
    }
}
