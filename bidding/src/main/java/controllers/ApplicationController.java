/**
 * Copyright (C) 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

@Singleton
public class ApplicationController {
	final static Logger log = LoggerFactory.getLogger(BiddingFacadeImpl.class);

	@Inject
	BiddingFacade biddingFacade;
	@Inject
	UserFacade userFacade;

	public Result createUser(UserDto user) {
		userFacade.createUsers(user);
		return Results.ok().json().render("user");
	}

	public Result getUser(@PathParam("id") Long id) {
		UserDto user = userFacade.getUser(id);
		return Results.ok().json().render(user);
	}
    public Result GetUsers() {
    	List<UserDto> list=userFacade.getUsersData();
    	return Results.ok().json().render(list);
    }

	public Result getAllBidding() {
		List<BiddingDto> biddingDto;
		try {
			biddingDto = biddingFacade.fetchAllBidding();
		} catch(BiddingException e) {
			log.info(e.getMessage());
			return Results.badRequest().json().render("Some Error Occured : Please Try Again After Sometime");
		}
		return Results.ok().json().render(biddingDto);
	}

	public Result getCarDetail(@PathParam("id") Long id) {
		Car car;
		try {
			car = biddingFacade.getCarDetails(id);
		} catch(CarException e) {
			log.info(e.getMessage());
			return Results.badRequest().json().render("Some Error Occured , Please Try Again After Sometime");
		}
		return Results.ok().json().render(car);
	}

	public Result index() {
		return Results.html();
	}

    public Result home() {

        return Results.html();

    }
}
