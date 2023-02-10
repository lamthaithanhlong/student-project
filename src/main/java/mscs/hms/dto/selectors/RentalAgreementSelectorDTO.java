package mscs.hms.dto.selectors;

import mscs.hms.model.RentalAgreement;

public class RentalAgreementSelectorDTO extends SelectorDTO<Long, RentalAgreement> {

    public RentalAgreementSelectorDTO(RentalAgreement entityObject) {
        super(entityObject);
    }
    @Override    
    public void setParentId(){
        this.id = entityObject.getId();
    }
    @Override
    public String getDisplayText() {
        return entityObject.getTitle().toString();
    }

    @Override
    public Long getId() {
        return entityObject.getId();
    }
    
    @Override
    public SelectorDTO<Long, RentalAgreement> createDTOObject(RentalAgreement address){
        return new RentalAgreementSelectorDTO(address);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != RentalAgreement.class)
        {
            return (obj.getClass() == this.getClass() && (((RentalAgreementSelectorDTO)obj).equals(this)));
        }
        RentalAgreement other = (RentalAgreement)obj;
        return this.id.equals(other.getId());
    }
}
