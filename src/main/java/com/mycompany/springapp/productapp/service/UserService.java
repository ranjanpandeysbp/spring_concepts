package com.mycompany.springapp.productapp.service;

import com.mycompany.springapp.productapp.exception.BusinessException;
import com.mycompany.springapp.productapp.model.UserModel;
import com.mycompany.springapp.productapp.repository.UserCrudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserCrudRepository ucr;
    
    public Long login(UserModel userModel)
    {
        Optional<UserModel> optionalUserModel = ucr.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());
        if(optionalUserModel.isPresent())
        {
            LOGGER.info("Login successful");//info() is used to provide information
            //info, warn is enabled in Test and Acceptance Env, debug in Dev Env,
            return optionalUserModel.get().getId();
        }
        return 0L;
    }
    public UserModel register(UserModel userModel) throws BusinessException {
        //Logic for finding whether the user with this phone number exists or not
        String phoneNumber = userModel.getPhoneNumber();
        Optional<UserModel> optUserPhoneNumber = ucr.findByPhoneNumber(phoneNumber);
        if(optUserPhoneNumber.isPresent())
        {
            BusinessException be = new BusinessException("Auth_001","User with this phone number already exists. Please try with another phone number");
            LOGGER.error("User already exists");
            //error logs are enabled in the acceptance and production environment
            throw be;
        }
        else
        {
            userModel = ucr.save(userModel);
        }
        return userModel;
    }
}
