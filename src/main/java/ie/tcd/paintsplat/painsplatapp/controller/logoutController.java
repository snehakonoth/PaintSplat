package ie.tcd.paintsplat.painsplatapp.controller;

import ie.tcd.paintsplat.painsplatapp.model.UserSession;
import ie.tcd.paintsplat.painsplatapp.service.UserService;
import ie.tcd.paintsplat.painsplatapp.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logout")
public class logoutController {
    @Autowired
    UserSessionService userSessionService;

    @Autowired
    UserService userService;
    @PostMapping("/{userSessionId}")
    public void logout(@PathVariable String userSessionId) {
        userSessionService.deleteUserSession(userSessionId);
    }
}
