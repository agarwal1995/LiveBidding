package facade;

import java.util.List;

import dto.UserDto;

public interface UserFacade {
	public List<UserDto> getUsersData();
	public void createUsers(UserDto userData);
	public boolean validUser(UserDto user);
	public boolean deleteUser(UserDto user);
	public UserDto getUser(Long id);

} 
