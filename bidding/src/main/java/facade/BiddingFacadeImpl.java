package facade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import dto.BiddingDto;
import exception.BiddingException;
import exception.CarException;
import models.Bidding;
import models.Car;

public class BiddingFacadeImpl implements BiddingFacade{
	@Inject
	Provider<EntityManager> entityManagerProvider;

	final static Logger log = LoggerFactory.getLogger(BiddingFacadeImpl.class);

	@Override
	@Transactional
	public List<BiddingDto> fetchAllBidding()throws BiddingException {
		log.info("Fetching All The Bindings");
		List<BiddingDto> biddingDto;
		try {
			EntityManager em = entityManagerProvider.get();
			List<Bidding> biddings = new ArrayList<>();
			biddings = em.createQuery("from Bidding",Bidding.class)
					.getResultList();
			biddingDto = new ArrayList<BiddingDto>();
			for(Bidding b : biddings) {
				BiddingDto biddingIndividual = new BiddingDto();
				biddingIndividual.setId(b.getBiddingid());
				biddingIndividual.setPrice(b.getPrice());
				biddingIndividual.setStartTime(b.getStartTime());
				biddingIndividual.setEndTime(b.getEndTime());
				biddingIndividual.setBidders(b.getBidders());
				biddingIndividual.setStatus(b.getStatus());
				biddingIndividual.setImageUrl(b.getImageUrl());
				biddingDto.add(biddingIndividual);
			}
		} catch(Exception e) {
			throw new BiddingException(e.getLocalizedMessage());
		}
		return biddingDto;
	}

	@Override
	@Transactional
	public Car getCarDetails(Long id) throws CarException{
		log.info("Fetching The Car Details");
		Car car;
		try {
			EntityManager em = entityManagerProvider.get();
			car = new Car();
			car = em.createQuery("Select x.car from Bidding x where x.bidding_id = :id",Car.class)
					.setParameter("id",id)
					.getSingleResult();
		} catch(Exception e) {
			throw new CarException(e.getMessage());
		}
		return car;
	}
}
