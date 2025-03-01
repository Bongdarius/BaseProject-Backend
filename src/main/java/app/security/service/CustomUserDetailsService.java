package app.security.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.auth.repository.UserRepository;
import app.security.CustomUserDetail;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        CustomUserDetail customUserDetail = repository.findByUsername(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return customUserDetail;
    }

    private CustomUserDetail createUserDetails(app.auth.entity.User user) {
        Collection<? extends GrantedAuthority> authorities = Collections
                .singleton(new SimpleGrantedAuthority(user.getRoleCd()));

        return new CustomUserDetail(
                user.getUsername(),
                user.getPassword(),
                authorities);

    }
}
