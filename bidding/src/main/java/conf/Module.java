

package conf;

import com.google.inject.AbstractModule;
import facade.BiddingFacade;
import facade.BiddingFacadeImpl;
import facade.UserFacade;
import facade.UserFacadeImpl;

import com.google.inject.Singleton;
import ninja.utils.NinjaProperties;

@Singleton
public class Module extends AbstractModule {
    
	private final NinjaProperties ninjaProperties;
    public Module(NinjaProperties ninjaProperties) {
    	this.ninjaProperties=ninjaProperties;
    }
    protected void configure() {
        
        bind(BiddingFacade.class).to(BiddingFacadeImpl.class);
        bind(UserFacade.class).to(UserFacadeImpl.class);
        
    }
}
