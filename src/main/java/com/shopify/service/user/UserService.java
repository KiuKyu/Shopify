package com.shopify.service.user;

import com.shopify.model.dto.UserDTO;
import com.shopify.model.persistence.user.Role;
import com.shopify.model.persistence.user.User;
import com.shopify.model.dto.UserPrincipal;
import com.shopify.repository.IRoleRepository;
import com.shopify.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User testSave(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles());

        long currentTime = System.currentTimeMillis();
        user.setCreateDate(currentTime);
        userRepository.save(user);
        return user;
    }

    @Override
    public User testFind(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return null;
        }
        User user = userOptional.get();
        return user;
    }

    @Override
    public User testUpdate(Long id, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return null;
        }
        updatedUser.setId(id);
        long currentTime = System.currentTimeMillis();
        updatedUser.setModifyDate(currentTime);
        return updatedUser;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            return null;
        } else {
            User user = userOptional.get();
            return user;
        }
    }

    @Override
    public User saveUser(User user, String roleName) {
        Optional<Role> roleOptional = roleRepository.findByName(roleName);
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(roleOptional.get().getId(), roleOptional.get().getName()));
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setRoles(roles);
        user.setCreateDate(System.currentTimeMillis());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            return null;
        }
        User user = userOptional.get();
        return UserPrincipal.build(user);
    }
}
