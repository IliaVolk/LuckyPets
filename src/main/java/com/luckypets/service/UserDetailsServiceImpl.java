package com.luckypets.service;

import com.luckypets.entity.User;
import com.luckypets.entity.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUser(email);
        //TODO: разобраться что здесь должно происходить
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(UserRole.USER.name()));
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(
                        user.getLogin(), user.getPassword(), roles);
        return userDetails;
    }

}
