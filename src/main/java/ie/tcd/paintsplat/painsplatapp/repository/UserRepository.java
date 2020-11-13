package ie.tcd.paintsplat.painsplatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.tcd.paintsplat.painsplatapp.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
