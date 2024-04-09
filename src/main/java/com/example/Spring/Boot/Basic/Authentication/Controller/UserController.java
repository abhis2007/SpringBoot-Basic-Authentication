package com.example.Spring.Boot.Basic.Authentication.Controller;

import com.example.Spring.Boot.Basic.Authentication.DTOs.RequestDTO;
import com.example.Spring.Boot.Basic.Authentication.DTOs.ResponseDTO;
import com.example.Spring.Boot.Basic.Authentication.Entity.LoginRequest;
import com.example.Spring.Boot.Basic.Authentication.Entity.SignUpRequest;
import com.example.Spring.Boot.Basic.Authentication.Entity.UserEntity;
import com.example.Spring.Boot.Basic.Authentication.Exceptions.CustomizedResponseEntityExceptionHandler;
import com.example.Spring.Boot.Basic.Authentication.Service.UserService;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.CredentialException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService ;

    @Autowired
    ModelMapper modelMapper ;

    @Autowired
    CustomizedResponseEntityExceptionHandler customException;

    @GetMapping("/Login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginRequest request) throws CredentialException {

        //Build the internal DTO
        RequestDTO internalDTO = new RequestDTO() ;

        /*
        internalDTO.setUserName(request.getUserName());
        internalDTO.setPassword(request.getPassword());
        */

        modelMapper.map(request, internalDTO);


        //received ResponseDTO to reflect to user
        return userService.login(internalDTO) ;
    }

    @PostMapping("/SignUp")
    public ResponseEntity<ResponseDTO> signupUser(@RequestBody SignUpRequest newUser) throws BadRequestException {

        //No DTO as request came not in the entity(think of a kind of DTO- AS if we parse in DTO again need parse in Entity.
        UserEntity user = new UserEntity() ;
        /*user.setName(newUser.getUserName());
        user.setUserId(newUser.getUserId());
        user.setEmailId(newUser.getEmailId());
        user.setPassword(newUser.getPassword());
        */
        modelMapper.map(newUser, user);
        return userService.AddUser(user);

    }
    @GetMapping("/public")
    public void publicData(){
        System.out.println("public");
    }

    @GetMapping("/private")
    public void privateData(){
        System.out.println("Private");
    }

    @GetMapping("/getAllUsers")
    public List<ResponseDTO> getUsers(){
        return userService.getAllUsers() ;
    }

    @GetMapping("/getByUserId/{UserId}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable String UserId) {

        //Build request DTO
        RequestDTO internalDTO = new RequestDTO() ;

        internalDTO.setUserId(UserId);

        return userService.getUserById(internalDTO) ;
    }
}
