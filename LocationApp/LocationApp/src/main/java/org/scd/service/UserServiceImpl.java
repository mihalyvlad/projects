package org.scd.service;

import org.scd.config.exception.BusinessException;
import org.scd.model.User;
import org.scd.model.dto.UserLoginDTO;
import org.scd.model.dto.UserRegisterDTO;
import org.scd.model.security.Role;
import org.scd.repository.UserRepository;
import org.scd.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User login(UserLoginDTO userLoginDTO) throws BusinessException {

        if (Objects.isNull(userLoginDTO)) {
            throw new BusinessException(401, "Body null !");
        }

        if (Objects.isNull(userLoginDTO.getEmail())) {
            throw new BusinessException(400, "Email cannot be null ! ");
        }

        if (Objects.isNull(userLoginDTO.getPassword())) {
            throw new BusinessException(400, "Password cannot be null !");
        }

        final User user = userRepository.findByEmail(userLoginDTO.getEmail());

        if (Objects.isNull(user)) {
            throw new BusinessException(401, "Bad credentials !");
        }

        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new BusinessException(401, "Bad credentials !");
        }

        return user;
    }

    @Override
    public User register(UserRegisterDTO userRegisterDTO) throws BusinessException {
        if (Objects.isNull(userRegisterDTO)) {
            throw new BusinessException(401, "Body null !");
        }

        if (Objects.isNull(userRegisterDTO.getEmail())) {
            throw new BusinessException(400, "Email cannot be null !");
        }

        if (Objects.isNull(userRegisterDTO.getPassword())) {
            throw new BusinessException(400, "Password cannot be null !");
        }

        if (Objects.isNull(userRegisterDTO.getFirstName())) {
            throw new BusinessException(400, "First Name cannot be null !");
        }

        if (Objects.isNull(userRegisterDTO.getLastName())) {
            throw new BusinessException(400, "Last Name cannot be null !");
        }

        User basicUser = new User();
        Role basicRole = roleRepository.findById(new Long(2)).get();
        Set<Role> rolesSet = new HashSet<>();
        rolesSet.add(basicRole);


        basicUser.setEmail(userRegisterDTO.getEmail());
        basicUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        basicUser.setFirstName(userRegisterDTO.getFirstName());
        basicUser.setLastName(userRegisterDTO.getLastName());
        basicUser.setRoles(rolesSet);
        userRepository.save(basicUser);

        return basicUser;
    }


}
