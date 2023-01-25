package mscs.hms.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;

public class UserInfoDetail implements UserDetails {

    private Optional<UserInfo> userInfo;

    public UserInfoDetail(Optional<UserInfo> userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] userRoles = this.getUserInfo().getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }

    @Override
    public String getPassword() {
        return userInfo.get().getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.get().getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserInfo getUserInfo() {
        return userInfo.get();
    }

    public String getFullName() {
        return userInfo.get().getFirstName() + " " + userInfo.get().getLastName();
    }
}
