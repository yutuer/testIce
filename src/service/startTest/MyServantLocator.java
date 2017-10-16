package service.startTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Ice.Current;
import Ice.LocalObjectHolder;
import Ice.Object;
import Ice.ServantLocator;
import Ice.UserException;

public class MyServantLocator implements ServantLocator{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Object locate(Current curr, LocalObjectHolder cookie) throws UserException {
		cookie.value = new String("inOut");
		logger.info("locate curr id:" + curr.id);
		return new MyServiceImpl();
	}

	@Override
	public void finished(Current curr, Object servant, java.lang.Object cookie) throws UserException {
		logger.info("finished cookie:" + cookie);
		logger.info("finished servant:" + servant);
	}

	@Override
	public void deactivate(String category) {
		logger.info("deactivate");
	}

}
