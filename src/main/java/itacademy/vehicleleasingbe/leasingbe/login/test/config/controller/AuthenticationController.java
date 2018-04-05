package itacademy.vehicleleasingbe.leasingbe.login.test.config.controller;


import itacademy.vehicleleasingbe.leasingbe.login.test.config.JwtTokenUtil;
import itacademy.vehicleleasingbe.leasingbe.login.test.config.model.AuthToken;
import itacademy.vehicleleasingbe.leasingbe.login.test.config.model.LoginUser;
import itacademy.vehicleleasingbe.leasingbe.login.test.config.model.User;

import itacademy.vehicleleasingbe.leasingbe.login.test.config.service.UserServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserServiceTest userServiceTest;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userServiceTest.findOne(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new AuthToken(token));
    }

}
