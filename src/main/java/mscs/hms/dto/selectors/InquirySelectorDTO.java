package mscs.hms.dto.selectors;

import mscs.hms.model.Inquiry;

public class InquirySelectorDTO extends SelectorDTO<Integer, Inquiry> {

    public InquirySelectorDTO(Inquiry entityObject) {
        super(entityObject);
    }
    @Override    
    public void setParentId(){
        this.id = entityObject.getId();
    }
    @Override
    public String getDisplayText() {
        return entityObject.getInquiryDate()+ "," + entityObject.getTitle();
    }

    @Override
    public Integer getId() {
        return entityObject.getId();
    }
    
    @Override
    public SelectorDTO<Integer, Inquiry> createDTOObject(Inquiry address){
        return new InquirySelectorDTO(address);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != Inquiry.class)
        {
            return (obj.getClass() == this.getClass() && (((InquirySelectorDTO)obj).equals(this)));
        }
        Inquiry other = (Inquiry)obj;
        return this.id.equals(other.getId());
    }
}
