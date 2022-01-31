package com.rds.vaapibbw.service.user;

import com.rds.vaapibbw.entity.BbwUser;
import com.rds.vaapibbw.error.UserExistException;
import com.rds.vaapibbw.error.UserNotFoundException;
import com.rds.vaapibbw.model.user.UserRequest;
import com.rds.vaapibbw.model.user.UserResponse;
import com.rds.vaapibbw.repository.UserRepository;
import com.rds.vaapibbw.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private ValidationUtil validationUtil;

    @Autowired
    public UserServiceImpl(
            ValidationUtil validationUtil,
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        validationUtil.validate(userRequest);
        BbwUser user = new BbwUser();
        UserResponse userResponse = get(userRequest.getClient_id());

        if (!userResponse.getClientId().isEmpty()){
            throw new UserExistException();
        }

        user.setAccountName(userRequest.getAccount_name());
        user.setClientId(userRequest.getClient_id());
        userResponse.setAccountName(user.getAccountName());
        userResponse.setClientId(user.getClientId());
        userRepository.save(user);

        return userResponse;
    }

    @Override
    public UserResponse get(String clientId) {
        Optional<BbwUser> user = userRepository.findById(clientId);
        if (!user.isPresent()){
            throw new UserNotFoundException();
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setAccountName(user.get().getAccountName());
        userResponse.setClientId(user.get().getClientId());
        return userResponse;
    }
}
