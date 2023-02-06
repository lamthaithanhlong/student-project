package mscs.hms.dto.selectors;
/**
 * <T> type of the Id column
 */
public abstract class SelectorDTO<I, E> {
    protected I id;
    protected E entityObject;
    public SelectorDTO(E entityObject){
        this.entityObject = entityObject;
        setParentId();        
    }
    public abstract I getId();
    public abstract void setParentId();
    public abstract String getDisplayText();
    public String toString(){
        return getDisplayText();
    }
    public abstract SelectorDTO<I,E> createDTOObject(E entityObject);
    
    @Override
    /** Note this will not work bothways */
    public abstract boolean equals(Object obj);
    
}
