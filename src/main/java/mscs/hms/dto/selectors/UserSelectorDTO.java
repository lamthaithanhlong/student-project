package mscs.hms.dto.selectors;

import mscs.hms.model.User;

public class UserSelectorDTO extends SelectorDTO<Long, User> {

    public UserSelectorDTO(User entityObject) {
        super(entityObject);
    }
    @Override    
    public void setParentId(){
        this.id = entityObject.getId();
    }
    @Override
    public String getDisplayText() {
        return entityObject.getUsername();
    }

    @Override
    public Long getId() {
        return entityObject.getId();
    }
    
    @Override
    public SelectorDTO<Long, User> createDTOObject(User user){
        return new UserSelectorDTO(user);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != User.class)
        {
            return (obj.getClass() == this.getClass() && (((UserSelectorDTO)obj).equals(this)));
        }
        User other = (User)obj;
        return this.id.equals(other.getId());
    }
}
