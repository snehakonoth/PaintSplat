package ie.tcd.paintsplat.painsplatapp.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.tcd.paintsplat.painsplatapp.model.UserSession;
import ie.tcd.paintsplat.painsplatapp.service.UserSessionService;

@RestController
@RequestMapping("/usersession")
public class UserSessionController {
    @Autowired
    UserSessionService userSessionService;

    @GetMapping("")
    public List<UserSession> list() {
        return userSessionService.listAllUserSession();
    }

    @GetMapping("/{usersessionid}")
    public ResponseEntity<UserSession> get(@PathVariable String usersessionid) {
        try {
            UserSession usersession = userSessionService.getUserSession(usersessionid);
            return new ResponseEntity<UserSession>(usersession, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<UserSession>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public void add(@RequestBody UserSession usersession) {
        userSessionService.saveUserSession(usersession);
    }
    @PutMapping("/{usersessionid}")
    public ResponseEntity<?> update(@RequestBody UserSession usersession, @PathVariable String usersessionid) {
        try {
            UserSession existUser = userSessionService.getUserSession(usersessionid);
            usersession.setUsersessionid(usersessionid);
            userSessionService.saveUserSession(usersession);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{usersessionid}")
    public void delete(@PathVariable String usersessionid) {

        userSessionService.deleteUserSession(usersessionid);
    }
}
