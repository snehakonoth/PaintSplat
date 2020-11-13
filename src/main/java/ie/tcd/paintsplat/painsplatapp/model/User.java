package ie.tcd.paintsplat.painsplatapp.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    private String userid;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String repassword;
    private Date datecreated;
    private Date passwordexpiration;
    private boolean emailverify;
    private Date dateofbirth;    
    private String country;
    public User() {
    }

    
    public User(String userid, String firstName, String lastName, String username, String email, String password,
			String repassword, Date datecreated, Date passwordexpiration, boolean emailverify, Date dateofbirth,
			String country) {
		super();
		this.userid = userid;
		this.firstname = firstName;
		this.lastname = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.repassword = repassword;
		this.datecreated = datecreated;
		this.passwordexpiration = passwordexpiration;
		this.emailverify = emailverify;
		this.dateofbirth = dateofbirth;
		this.country = country;
	}


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstName) {
		this.firstname = firstName;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastName) {
		this.lastname = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRepassword() {
		return repassword;
	}


	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}


	public Date getDatecreated() {
		return datecreated;
	}


	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}


	public Date getPasswordexpiration() {
		return passwordexpiration;
	}


	public void setPasswordexpiration(Date passwordexpiration) {
		this.passwordexpiration = passwordexpiration;
	}


	public boolean isEmailverify() {
		return emailverify;
	}


	public void setEmailverify(boolean emailverify) {
		this.emailverify = emailverify;
	}


	public Date getDateofbirth() {
		return dateofbirth;
	}


	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


}
