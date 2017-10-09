package service.sms;

import Ice.Communicator;
import Ice.Current;
import Ice.Logger;
import Ice.ObjectAdapter;
import IceBox.Service;

import com.hp.tel.ice.message._SMSServiceDisp;

public class SMSServiceImpl extends _SMSServiceDisp implements Service{
	
	private static final long serialVersionUID = -6601040849124649868L;
	
	private ObjectAdapter _adapter;
	private Logger logger;
	
	@Override
	public void sendSMS(String msg, Current __current) {
		System.out.println("send msg:" + msg);
	}

	@Override
	public void start(String name, Communicator ic, String[] args) {
		logger = ic.getLogger().cloneWithPrefix(name);
		logger.print("service name is:" + name);
		_adapter = ic.createObjectAdapter(name);
		Ice.Object object = this;
		_adapter.add(object, ic.stringToIdentity(name));
		_adapter.activate();
		System.out.println(name + " started");
	}

	@Override
	public void stop() {
		logger.print(this._adapter.getName() +" stoped");
		_adapter.destroy();
	}

}
