package service.onlinebook;

import org.apache.log4j.Logger;

import Ice.Communicator;
import Ice.Current;
import Ice.ObjectAdapter;
import IceBox.Service;

import com.hp.tel.ice.book.Message;
import com.hp.tel.ice.book._OnlineBookDisp;

public class OnlineBookImpl extends _OnlineBookDisp implements Service{
	
	private static final long serialVersionUID = -4396060325904207624L;
	private Logger logger = Logger.getLogger(OnlineBookImpl.class);
	
	private ObjectAdapter _adapter;
	
	@Override
	public Message bookTick(Message msg, Current __current) {
		logger.info("bookTick call:" + msg.content);
		msg.content = "cccc";
		return msg;
	}

	@Override
	public void start(String name, Communicator ic, String[] args) {
		logger.info("service name is:" + name);
		_adapter = ic.createObjectAdapter(name);
		Ice.Object object = this;
		_adapter.add(object, ic.stringToIdentity(name));
		_adapter.activate();
		logger.info(name + " started");
	}

	@Override
	public void stop() {
		logger.info(this._adapter.getName() +" stoped");
		_adapter.destroy();
	}

}
