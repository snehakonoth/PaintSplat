package ie.tcd.paintsplat.painsplatapp.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gamesession")
public class GameSession {
    private String gameid;
    private Date startts;
    private Date endts;
    private String winnerid;
    
    public GameSession() {
    }

	public GameSession(String gameid, Date startts, Date endts, String winnerid) {
		super();
		this.gameid = gameid;
		this.startts = startts;
		this.endts = endts;
		this.winnerid = winnerid;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public Date getStartts() {
		return startts;
	}

	public void setStartts(Date startts) {
		this.startts = startts;
	}

	public Date getEndts() {
		return endts;
	}

	public void setEndts(Date endts) {
		this.endts = endts;
	}

	public String getWinnerid() {
		return winnerid;
	}

	public void setWinnerid(String winnerid) {
		this.winnerid = winnerid;
	}

    
    
}
