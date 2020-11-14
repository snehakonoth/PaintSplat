package ie.tcd.paintsplat.painsplatapp.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usersession")
public class UserSession {
    private String usersessionid;
    private Date starttimestamp;
    private String gameid;
    private int score;
    public UserSession() {
    }

    
   

	public UserSession(String usersessionid, Date starttimestamp, String gameid, int score) {
		super();
		this.usersessionid = usersessionid;
		this.starttimestamp = starttimestamp;
		this.gameid = gameid;
		this.score = score;
	}



	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getUsersessionid() {
		return usersessionid;
	}




	public void setUsersessionid(String usersessionid) {
		this.usersessionid = usersessionid;
	}




	public Date getStarttimestamp() {
		return starttimestamp;
	}




	public void setStarttimestamp(Date starttimestamp) {
		this.starttimestamp = starttimestamp;
	}




	public String getGameid() {
		return gameid;
	}




	public void setGameid(String gameid) {
		this.gameid = gameid;
	}




	public int getScore() {
		return score;
	}




	public void setScore(int score) {
		this.score = score;
	}

}
