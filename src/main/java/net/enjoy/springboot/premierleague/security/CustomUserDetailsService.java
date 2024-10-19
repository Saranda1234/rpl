package net.enjoy.springboot.premierleague.security;

import net.enjoy.springboot.premierleague.entity.Role;
import net.enjoy.springboot.premierleague.entity.User;
import net.enjoy.springboot.premierleague.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            Collection<? extends GrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());
            System.out.println("User email: " + user.getEmail());
            System.out.println("User roles: " + authorities);
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    authorities);
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}