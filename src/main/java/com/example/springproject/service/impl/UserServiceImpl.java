package com.example.springproject.service.impl;

import com.example.springproject.UserRepository;
import com.example.springproject.entity.io.UserEntity;
import com.example.springproject.service.UserService;
import com.example.springproject.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createuser(UserDto user) {
        UserEntity userEntity = new UserEntity();
        //BeanUtils.copyProperties(user,userEntity);
        userEntity.setFirstName(user.getFirstname());
        userEntity.setFirstName(user.getLastname());
        userEntity.setFirstName(user.getEmail());
        userEntity.setEncryptedPassword("test");
        userEntity.setUserId("testUserId");
        UserEntity storedUserDetails;
        storedUserDetails=userRepository.save(userEntity);
        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails,returnValue);
        return returnValue;
    }
}
