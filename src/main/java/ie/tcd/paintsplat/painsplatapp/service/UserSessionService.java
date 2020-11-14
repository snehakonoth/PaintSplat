package ie.tcd.paintsplat.painsplatapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.tcd.paintsplat.painsplatapp.model.User;
import ie.tcd.paintsplat.painsplatapp.model.UserSession;
import ie.tcd.paintsplat.painsplatapp.repository.UserSessionRepository;
@Service
@Transactional
public class UserSessionService {
    @Autowired
    private UserSessionRepository userSessionRepository;
    public List<UserSession> listAllUserSession() {
        return userSessionRepository.findAll();
    }

    public void saveUserSession(UserSession userSession) {
    	userSessionRepository.save(userSession);
    }

    public UserSession getUserSession(String usersessionid) {
        return userSessionRepository.findById(usersessionid).get();
    }

    public void deleteUserSession(String id) {
    	userSessionRepository.deleteById(id);
    }
}
