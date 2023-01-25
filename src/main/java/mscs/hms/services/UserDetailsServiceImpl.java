package mscs.hms.services;

import mscs.hms.models.Role;
import mscs.hms.models.UserInfo;
import mscs.hms.models.UserInfoDetail;
import mscs.hms.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements IUserService, UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserInfoDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> user = userRepository.getUserByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new UserInfoDetail(user);
    }

    /*
    private static Collection<? extends GrantedAuthority> getAuthorities(UserInfo user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
    */

    @Override
    public UserInfo saveUser(UserInfo user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserInfo> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Role> getAllRoles() {
        return userRepository.getAllRoles();
    }

    @Override
    public Role getAllRoleByName(String name) {
        return userRepository.getAllRoleByName(name);
    }
}
