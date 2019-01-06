package controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import dto.BiddingDto;
import dto.UserDto;
import exception.BiddingException;
import exception.CarException;
import facade.BiddingFacade;
import facade.BiddingFacadeImpl;
import facade.UserFacade;
import models.Car;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import ninja.params.Param;

@Singleton
public class BidController {
	static int maxPrice = 0;
	
	final static Logger log = LoggerFactory.getLogger(BiddingFacadeImpl.class);

	@Inject
	BiddingFacade biddingFacade;
	@Inject
	UserFacade userFacade;
	
	public Result getMaxBid(@PathParam("id")Long id,@Param("price")Long price) {
		
		try {
			Long maxPrice = biddingFacade.maxBid(id,price);
			return Results.ok().json().render(maxPrice);
		}catch(Exception e) {
			log.info(e.getMessage());
			return Results.badRequest().json().render(e.getMessage());
		}
	}
}
