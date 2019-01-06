package conf;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import facade.BiddingFacade;
import facade.BiddingFacadeImpl;
import ninja.scheduler.Schedule;
import java.util.concurrent.TimeUnit;


@Singleton
public class ScheduledAction {
	@Inject
	private BiddingFacade biddingFacade;
	final static Logger log = LoggerFactory.getLogger(BiddingFacadeImpl.class);

	@Schedule(delay = 5, initialDelay = 1, timeUnit = TimeUnit.SECONDS)
	public void checkBidding(){
		log.info("jdsjhdjhdsjhdjhdjhdh0");
		biddingFacade.checkBiddings();
	}
}
