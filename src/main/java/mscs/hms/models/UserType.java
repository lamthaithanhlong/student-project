package mscs.hms.models;

@Deprecated
public enum UserType {

    ADMIN("Admin"), LANDLORD("Owner"), RENTER("Renter"), GUEST("Guest");

    private String userType;

    UserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }
}
