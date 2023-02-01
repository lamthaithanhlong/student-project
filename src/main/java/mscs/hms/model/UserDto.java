package mscs.hms.model;

import mscs.hms.entity.User;

public class UserDto {
    // this will be used for UI mapping
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
