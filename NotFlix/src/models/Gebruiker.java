package models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
public class Gebruiker {
	
	private String achternaam;
	private String tussenvoegsel;
	private String voornaam;
	private String nickname;
	private String wachtwoord;
	
	public Gebruiker(String achternaam, String tussenvoegsel, String voornaam, String nickname, String wachtwoord){
		this.achternaam = achternaam;
		this.tussenvoegsel = tussenvoegsel;
		this.voornaam = voornaam;
		this.nickname = nickname;
		this.wachtwoord = wachtwoord;
	}
	
	public Gebruiker(){
		
	}
	
	public String getAchternaam(){
		return achternaam;
	}
	
	public void setAchternaam(String achternaam){
		this.achternaam = achternaam;
	}
	
	public String getTussenvoegsel(){
		return tussenvoegsel;
	}
	
	public void setTussenvoegsel(String tussenvoegsel){
		this.tussenvoegsel = tussenvoegsel;
	}
	
	public String getVoornaam(){
		return voornaam;
	}
	
	public void setVoornaam(String voornaam){
		this.voornaam = voornaam;
	}
	
	public String getNickname(){
		return nickname;
	}
	
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	
	@XmlTransient
	public String getWachtwoord(){
		return wachtwoord;
	}
	
	public void setWachtwoord(String wachtwoord){
		this.wachtwoord = wachtwoord;
	}
	
}
