package ie.tcd.paintsplat.painsplatapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.tcd.paintsplat.painsplatapp.model.GameSession;
import ie.tcd.paintsplat.painsplatapp.repository.GameSessionRepository;
@Service
@Transactional
public class GameSessionService {
    @Autowired
    private GameSessionRepository gameSessionRepository;
    public List<GameSession> listAllgameSession() {
        return gameSessionRepository.findAll();
    }

    public void savegameSession(GameSession gameSession) {
    	gameSessionRepository.save(gameSession);
    }

    public GameSession getGameSession(String gamesessionid) {
        return gameSessionRepository.findById(gamesessionid).get();
    }

    public void deleteGameSession(String id) {
    	gameSessionRepository.deleteById(id);
    }
}
