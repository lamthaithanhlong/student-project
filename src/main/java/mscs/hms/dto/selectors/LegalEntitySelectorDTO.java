package mscs.hms.dto.selectors;

import mscs.hms.model.Company;
import mscs.hms.model.LegalEntity;
import mscs.hms.model.Person;

public class LegalEntitySelectorDTO extends SelectorDTO<Integer, LegalEntity> {

    public LegalEntitySelectorDTO(LegalEntity entityObject) {
        super(entityObject);
    }
    @Override    
    public void setParentId(){
        this.id = entityObject.getId();
    }
    @Override
    public String getDisplayText() {
        return entityObject.getLegalEntityName();
    }

    @Override
    public Integer getId() {
        return entityObject.getId();
    }
    
    @Override
    public SelectorDTO<Integer, LegalEntity> createDTOObject(LegalEntity address){
        return new LegalEntitySelectorDTO(address);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj.getClass() == LegalEntity.class || obj.getClass() == Person.class || obj.getClass() == Company.class))
        {
            return (obj.getClass() == this.getClass() && obj.equals(this));
        }
        LegalEntity other = (LegalEntity)obj;
        return this.id.equals(other.getId());
    }
}
