package facade;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import dto.UserDto;
import models.User;

public class UserFacadeImpl implements UserFacade {
	@Inject
	Provider<EntityManager> entityManagerProvider;
	
	@Override
	@Transactional
	public List<dto.UserDto> getUsersData() {
		List<UserDto> lo=new ArrayList<>();
		EntityManager em=entityManagerProvider.get();
		List<User> list=em.createQuery("from User",User.class).getResultList();
		for(User u:list) {
			UserDto t=new UserDto();
			t.setId(u.getId());
			t.setName(u.getName());
			t.setPassword(u.getPassword());
			t.setBidIds(u.getBidsIds());
			lo.add(t);
		}
		return lo;
	}
	
	@Override
	@Transactional
	public UserDto getUser(Long id) {
		UserDto userDto = new UserDto();
		EntityManager em=entityManagerProvider.get();
		User user=em.createQuery("Select x from User x where x.id = :id",User.class)
					.setParameter("id",id)
					.getSingleResult();
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setPassword(user.getPassword());
			userDto.setBidIds(user.getBidsIds());
		
		return userDto;
	}
	
	@Override
	@Transactional
	public void createUsers(UserDto userData) {
		EntityManager em=entityManagerProvider.get();
		User user=new User();
		user.setName(userData.getName());
		user.setPassword(userData.getPassword());
		em.persist(user);
	}
	
	@Override
	@Transactional
	public boolean validUser(UserDto user) {
		System.out.println(user);
		EntityManager em=entityManagerProvider.get();
		User u=new User();
		try {
				u=em.createQuery("Select x from User x where x.name= :name and x.password= :password",User.class)
					.setParameter("name",user.getName())
					.setParameter("password",user.getPassword())
					.getSingleResult();
		} catch(Exception e) {
			System.out.println(e);
		}		
		if(u==null)
			return false;
		return true;
	}

	
	@Override
	@Transactional
	public boolean deleteUser(UserDto user) {
		EntityManager em=entityManagerProvider.get();
		try {
			User u=em.createQuery("Select x from User x where x.name= :name and x.password= :password",User.class)
					.setParameter("name",user.getName())
					.setParameter("password",user.getPassword())
					.getSingleResult();
			System.out.println(u.getName());
			em.remove(u);
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return true;
	}
	
	
}
