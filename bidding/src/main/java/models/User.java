package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user")
public class User{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	private String name;
	private String password;
	
	private String bidsIds;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	
	public String getBidsIds() {
		return bidsIds;
	}
	public void setBidsIds(String bidsIds) {
		if(bidsIds==null) {
			bidsIds="";
		}
		this.bidsIds = bidsIds;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}