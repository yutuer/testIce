package service.onlinebook;

import Ice.Communicator;
import Ice.Current;
import Ice.ObjectAdapter;
import IceBox.Service;

import com.hp.tel.ice.book.Message;
import com.hp.tel.ice.book._OnlineBookDisp;

public class OnlineBookImpl extends _OnlineBookDisp implements Service{
	
	private static final long serialVersionUID = -4396060325904207624L;
	
	private ObjectAdapter _adapter;
	
	@Override
	public Message bookTick(Message msg, Current __current) {
//		throw new RuntimeException();
		System.out.println("bookTick call:" + msg.content);
		return msg;
	}

	@Override
	public void start(String name, Communicator ic, String[] args) {
		System.out.println("service name is:" + name);
		_adapter = ic.createObjectAdapter(name);
		Ice.Object object = this;
		_adapter.add(object, ic.stringToIdentity(name));
		_adapter.activate();
		System.out.println(name + " started");
	}

	@Override
	public void stop() {
		System.out.println(this._adapter.getName() +" stoped");
		_adapter.destroy();
	}

}
