package com.example.buysell.services;

import com.example.buysell.models.User;
import com.example.buysell.models.enums.Role;
import com.example.buysell.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Новый юзер создан с емайлом: {}", email);
        userRepository.save(user);

        return true;
    }

    public List<User> list(){
        return userRepository.findAll();
    }

    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user!=null){
            if (user.isActive()){
                user.setActive(false);
                log.info("Бан юзера с id {}; email:{}",user.getId(),user.getEmail());
            }else{
                user.setActive(true);
                log.info("анбан юзера с id {}; email:{}",user.getId(),user.getEmail());
            }


        }
        userRepository.save(user);
    }

    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)// для каждой рол из это коллекции вызываем name
                .collect(Collectors.toSet());
        user.getRoles().clear();// преобразовываем в строковый вид


        for (String key:form.keySet()
             ) {
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
            
        }
        userRepository.save(user);
    }
}
