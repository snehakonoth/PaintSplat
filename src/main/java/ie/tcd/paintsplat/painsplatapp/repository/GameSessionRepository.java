package ie.tcd.paintsplat.painsplatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.tcd.paintsplat.painsplatapp.model.GameSession;

public interface GameSessionRepository extends JpaRepository<GameSession, String> {
}
