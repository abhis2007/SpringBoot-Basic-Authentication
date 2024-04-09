package com.example.Spring.Boot.Basic.Authentication.Service;

import com.example.Spring.Boot.Basic.Authentication.DTOs.RequestDTO;
import com.example.Spring.Boot.Basic.Authentication.DTOs.ResponseDTO;
import com.example.Spring.Boot.Basic.Authentication.Entity.UserEntity;
//import com.example.Spring.Boot.Basic.Authentication.Exceptions.UserNotFoundException;
import com.example.Spring.Boot.Basic.Authentication.Exceptions.UserNotFoundException;
import com.example.Spring.Boot.Basic.Authentication.Repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository ;
    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Autowired
    private ModelMapper modelMapper ;

    public ResponseEntity<ResponseDTO> AddUser(UserEntity user) throws BadRequestException {

        if( user.getUserId() == null || user.getEmailId() == null || user.getPassword() == null || user.getName() == null ){
            throw new BadRequestException("Input/s is/are missing") ;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity savedUser = userRepository.save(user) ;

        //Map to the response DTO
        ResponseDTO responseDTO = new ResponseDTO() ;

        /*responseDTO.setEmailId(savedUser.getEmailId());
        responseDTO.setUserName(savedUser.getName());
        responseDTO.setUserId(savedUser.getUserId());*/

        modelMapper.map(savedUser, responseDTO);

        return ResponseEntity.ok(responseDTO) ;
    }

    public ResponseEntity<ResponseDTO> getUserById(RequestDTO request){

        String userId = request.getUserId() ;
        if( !userRepository.existsById(userId) ) {
            throw new UserNotFoundException("User: " + userId + " Not Found") ;
        }

        UserEntity user = userRepository.findById(userId).get() ;

        ResponseDTO userResponse = new ResponseDTO() ;
        /*userResponse.setUserName(user.getName());
        userResponse.setUserId(user.getUserId());
        userResponse.setEmailId(user.getEmailId());
        userResponse.setPassword(user.getPassword());*/

        modelMapper.map(user, userResponse);

        return ResponseEntity.ok(userResponse) ;
    }
    public List<ResponseDTO> getAllUsers(){

        List<UserEntity> users = new ArrayList<>() ;
        userRepository.findAll().forEach(users::add);

        List<ResponseDTO> userDTO = new ArrayList<>() ;
        for(UserEntity user : users ){
            ResponseDTO res = new ResponseDTO() ;
            res.setUserId(user.getUserId());
            res.setUserName(user.getName());
            res.setEmailId(user.getEmailId());

            userDTO.add(res) ;
        }
        return userDTO ;
    }

    public ResponseEntity<ResponseDTO> login(RequestDTO internalDTO) {
        String Password = internalDTO.getPassword() ;

        if( !userRepository.existsById(internalDTO.getUserName()) ) {
            throw new UserNotFoundException("User: " + internalDTO.getUserName() + " Not Found") ;
        }

        //Map it to the response DTO
        ResponseDTO responseDTO = new ResponseDTO() ;

        UserEntity user = userRepository.findById(internalDTO.getUserName()).get() ;
        if( !passwordEncoder.matches(Password, user.getPassword()) ){
            throw new UserNotFoundException("User: " + internalDTO.getUserName() + " Not Found") ;
        }
        /*responseDTO.setUserId(user.getUserId());
        responseDTO.setUserName(user.getName());
        responseDTO.setEmailId(user.getEmailId());*/
        modelMapper.map(user, responseDTO);

        return ResponseEntity.ok(responseDTO);
    }

}


