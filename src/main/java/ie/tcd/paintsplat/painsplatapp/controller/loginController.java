package ie.tcd.paintsplat.painsplatapp.controller;

import ie.tcd.paintsplat.painsplatapp.model.User;
import ie.tcd.paintsplat.painsplatapp.model.request.loginCredentials;
import ie.tcd.paintsplat.painsplatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class loginController {
    @Autowired
    UserService userService;
    @PostMapping("/{userid}")
    public ResponseEntity<?> login(@RequestBody loginCredentials userCred) {
        ResponseEntity responseEntity = null;

        String entered_password = userCred.getPassword();

        User existUser = userService.getUser(userCred.getUserId());

        String user_password = existUser.getPassword();

        if (null != entered_password && entered_password.equals(user_password)) {
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            responseEntity =  new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return responseEntity;
    }
}
