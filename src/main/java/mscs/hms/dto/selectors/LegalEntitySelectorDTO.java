package mscs.hms.dto.selectors;

import mscs.hms.model.LegalEntity;

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
        return entityObject.getId().toString();
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
        if(obj.getClass() != LegalEntity.class)
        {
            return (obj.getClass() == this.getClass() && (((LegalEntitySelectorDTO)obj).equals(this)));
        }
        LegalEntity other = (LegalEntity)obj;
        return this.id.equals(other.getId());
    }
}
