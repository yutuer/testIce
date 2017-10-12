package service.sms;

import org.apache.log4j.Logger;

import Ice.Communicator;
import Ice.Current;
import Ice.ObjectAdapter;
import IceBox.Service;

import com.hp.tel.ice.book.Message;
import com.hp.tel.ice.book.OnlineBookPrx;
import com.hp.tel.ice.book.OnlineBookPrxHelper;
import com.hp.tel.ice.message._SMSServiceDisp;

public class SMSServiceImpl extends _SMSServiceDisp implements Service{
	
	private static final long serialVersionUID = -6601040849124649868L;
	private Logger logger = Logger.getLogger(SMSServiceImpl.class);
	
	private ObjectAdapter _adapter;
	
	@Override
	public void sendSMS(String msg, Current __current) {
		if(msg.startsWith("book")){
			try {
				Ice.ObjectPrx base = _adapter.getCommunicator().stringToProxy("OnlineBook");
				OnlineBookPrx onlienBookPrx = OnlineBookPrxHelper.checkedCast(base);
				
				Message bookMsg = new Message();
				bookMsg.name = "Mr Wang";
				bookMsg.type = 3;
				bookMsg.price = 99.99;
				bookMsg.valid = true;
				bookMsg.content = "abcdefg";
				
				Message bookRet = onlienBookPrx.bookTick(bookMsg);
				logger.info("ret content:" + bookRet.content);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public void start(String name, Communicator ic, String[] args) {
		logger.info("service name is:" + name);
		_adapter = ic.createObjectAdapter(name);
		Ice.Object object = this;
		_adapter.add(object, ic.stringToIdentity(name));
		_adapter.activate();
		System.out.println(name + " started");
	}

	@Override
	public void stop() {
		logger.info(this._adapter.getName() +" stoped");
		_adapter.destroy();
	}

}
