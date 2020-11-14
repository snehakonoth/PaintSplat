package ie.tcd.paintsplat.painsplatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.tcd.paintsplat.painsplatapp.model.User;
import ie.tcd.paintsplat.painsplatapp.model.UserSession;

public interface UserSessionRepository extends JpaRepository<UserSession, String> {
}
