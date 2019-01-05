package facade;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.google.inject.Inject;
import com.google.inject.Provider;

import dto.CarDto;
import models.Car;

public class CarFacadeImpl implements CarFacade{

	@Inject
	Provider<EntityManager> entityManagerProvider;
	
	@Override
	@Transactional
	public CarDto getCarDetails(Long id) {
		
		EntityManager em = entityManagerProvider.get();
		Car car = new Car();
		
		//car = em.createQuery("Select x from Car where ")
		return null;
	}
}
