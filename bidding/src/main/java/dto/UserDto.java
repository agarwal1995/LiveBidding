package dto;

public class UserDto {
	private Long id;
	private String name;
	private String password;
	private String bidIds;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBidIds() {
		return bidIds;
	}
	public void setBidIds(String bidIds) {
		this.bidIds = bidIds;
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
	@Override
	public String toString() {
		return "UserDto [name=" + name + ", password=" + password + "]";
	}
	
}