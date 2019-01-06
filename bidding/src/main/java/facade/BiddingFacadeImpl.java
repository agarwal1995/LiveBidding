package facade;

import java.util.ArrayList;
import java.util.List;

import java.lang.*;
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
import ninja.cache.NinjaCache;	

public class BiddingFacadeImpl implements BiddingFacade{
	@Inject
	Provider<EntityManager> entityManagerProvider;
	@Inject 
	NinjaCache ninjaCache;
	
	final static Logger log = LoggerFactory.getLogger(BiddingFacadeImpl.class);

	@Override
	@Transactional
	public void checkBiddings() {
		log.info("hgdsfD");
		List<BiddingDto> posts = ninjaCache.get("posts", List.class);
	    if(posts == null) {
	    	log.info("ghsrdhnbfds");
	    	List<BiddingDto> biddingDto;
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
	        ninjaCache.set("posts", biddingDto, "30mn");
			}
	    }
	}
	
	@Override
	@Transactional
	public Long maxBid(Long id,Long price)throws BiddingException {
		log.info("hghrsnfrer");
		try {
			Long cT =  System.currentTimeMillis()/1000;
			EntityManager em = entityManagerProvider.get();
			Bidding bidding = new Bidding();
			bidding = em.find(Bidding.class,id);
			if(bidding.getEndTime()<cT) {
				throw new BiddingException("Bidding Is Closed");
			} 
			if(bidding.getStartTime()>cT) {
				throw new BiddingException("Bidding Is Yet To Open");
			}
			if(price>bidding.getPrice()) {
				bidding.setPrice(price);
			}
			return bidding.getPrice();
		}catch(Exception e) {
			throw new BiddingException(e.getMessage());
		}
	}
	
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
