package com.rds.vaapibbw.service.user;

import com.rds.vaapibbw.model.user.UserRequest;
import com.rds.vaapibbw.model.user.UserResponse;

public interface UserService {

    UserResponse save(UserRequest userRequest);
    UserResponse get(String clientId);
}
