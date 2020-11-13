package ie.tcd.paintsplat.painsplatapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.tcd.paintsplat.painsplatapp.model.User;
import ie.tcd.paintsplat.painsplatapp.repository.UserRepository;
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUser(String id) {
        return userRepository.findById(id).get();
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
