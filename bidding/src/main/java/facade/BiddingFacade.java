package facade;

import java.util.List;

import dto.BiddingDto;
import exception.BiddingException;
import exception.CarException;
import models.Car;

public interface BiddingFacade {
List<BiddingDto> fetchAllBidding()throws BiddingException;

Car getCarDetails(Long id)throws CarException;

Long maxBid(Long id,Long price)throws Exception;

void checkBiddings();
} 
