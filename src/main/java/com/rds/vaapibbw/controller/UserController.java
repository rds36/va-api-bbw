package com.rds.vaapibbw.controller;

import com.rds.vaapibbw.model.WebResponse;
import com.rds.vaapibbw.model.transaction.TransactionRequest;
import com.rds.vaapibbw.model.user.AccountNameResponse;
import com.rds.vaapibbw.model.user.UserRequest;
import com.rds.vaapibbw.model.user.UserResponse;
import com.rds.vaapibbw.service.transaction.TransactionService;
import com.rds.vaapibbw.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping(
            value = "/api/user/{clientId}",
            produces = "application/json"
    )
    public WebResponse<UserResponse> getUserByClientId(
            @PathVariable("clientId") String clientId
    ) {
        UserResponse user = userService.get(clientId);

        return new WebResponse<>(
                "000",
                "Success",
                user
        );
    }

    @PostMapping(
            value = "/api/user",
            produces = "application/json",
            consumes = "application/json"
    )
    public WebResponse<UserResponse> createUser(
            @RequestBody UserRequest userRequest
    ){
        UserResponse user = userService.save(userRequest);

        return new WebResponse<>(
                "000",
                "User created",
                user
        );
    }
}
