package facade;

import dto.CarDto;

public interface CarFacade {
	
	CarDto getCarDetails(Long id);
}
