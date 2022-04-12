package com.example.springproject.service.impl;

import com.example.springproject.UserRepository;
import com.example.springproject.entity.io.UserEntity;
import com.example.springproject.service.UserService;
import com.example.springproject.shared.Utils;
import com.example.springproject.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;
    @Override
    public UserDto createuser(UserDto user) {
//        UserEntity storedUserDetails= userRepository.findByemail(user.getEmail());

        if(userRepository.findByemail(user.getEmail()) !=null ) throw new RuntimeException("Record already exists");


        UserEntity userEntity = new UserEntity();
        //BeanUtils.copyProperties(user,userEntity);
        userEntity.setFirstName(user.getFirstname());
        userEntity.setLastName(user.getLastname());
        userEntity.setEmail(user.getEmail());
        userEntity.setEncryptedPassword("test");


        String publicUserId = utils.generateUserId(30);
        userEntity.setUserId(publicUserId);





        UserEntity storedUserDetails= userRepository.save(userEntity);
        UserDto returnValue = new UserDto();
        returnValue.setUserId(storedUserDetails.getUserId());
        returnValue.setFirstname(storedUserDetails.getFirstName());
        returnValue.setLastname(storedUserDetails.getLastName());
        returnValue.setEmail(storedUserDetails.getEmail());

      //  BeanUtils.copyProperties(storedUserDetails,returnValue);
        return returnValue;
    }
}
