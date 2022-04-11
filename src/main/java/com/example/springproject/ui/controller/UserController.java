package com.example.springproject.ui.controller;


import com.example.springproject.service.UserService;
import com.example.springproject.service.impl.UserServiceImpl;
import com.example.springproject.shared.dto.UserDto;
import com.example.springproject.ui.model.request.UserDetailsRequestModel;
import com.example.springproject.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//Register this class as rest controller and be able to receive requests
@RestController
@RequestMapping("users") // http://localhost:8080/users
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping
    public String getuser (){
        return "Get user was called";
    }

    @PostMapping
    public UserRest createuser (@RequestBody UserDetailsRequestModel userDetails){
        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetails,userDto);
        System.out.println("userDetails copied into userDto");

        UserDto createdUser;
        System.out.println("Reference of createdUser created");
        createdUser = userService.createuser(userDto);
        System.out.println("userservice.createuser is called");
        BeanUtils.copyProperties(createdUser,returnValue);
        System.out.println("createdUser copied into returnValue");
        return returnValue;
    }

    @PutMapping
    public String updateuser (){
        return "Update user was called";
    }

    @DeleteMapping
    public String deleteuser (){
        return "Delete user was called";
    }
}
