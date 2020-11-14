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

import ie.tcd.paintsplat.painsplatapp.model.GameSession;
import ie.tcd.paintsplat.painsplatapp.model.User;
import ie.tcd.paintsplat.painsplatapp.service.GameSessionService;

@RestController
@RequestMapping("/gamesession")
public class GameSessionController {
    @Autowired
    GameSessionService gameSessionService;

    @GetMapping("")
    public List<GameSession> list() {
        return gameSessionService.listAllgameSession();
    }

    @GetMapping("/{gameid}")
    public ResponseEntity<GameSession> get(@PathVariable String gameid) {
        try {
            GameSession gamesession = gameSessionService.getGameSession(gameid);
            return new ResponseEntity<GameSession>(gamesession, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<GameSession>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public void add(@RequestBody GameSession gamesession) {
        gameSessionService.savegameSession(gamesession);
    }
    @PutMapping("/{gameid}")
    public ResponseEntity<?> update(@RequestBody GameSession gamesession, @PathVariable String gameid) {
        try {
            GameSession existgamesession = gameSessionService.getGameSession(gameid);
            gamesession.setGameid(gameid);
            gameSessionService.savegameSession(gamesession);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{gameid}")
    public void delete(@PathVariable String gameid) {

        gameSessionService.deleteGameSession(gameid);
    }
}
