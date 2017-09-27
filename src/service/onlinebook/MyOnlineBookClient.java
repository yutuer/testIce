package service.onlinebook;

import com.hp.tel.ice.book.Message;
import com.hp.tel.ice.book.OnlineBookPrx;
import com.hp.tel.ice.book.OnlineBookPrxHelper;

public class MyOnlineBookClient {

	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;

		try {
			ic = Ice.Util.initialize();
			Ice.ObjectPrx base = ic.stringToProxy("OnlineBook:default -p 10000");
			OnlineBookPrx proxy = OnlineBookPrxHelper.checkedCast(base);
			if (proxy != null) {
				Message msg = new Message();
				msg.name = "Mr Wang";
				msg.type = 3;
				msg.price = 9.99;
				msg.valid = true;
				msg.content = "aaaa";
				
				Message ret = proxy.bookTick(msg);
				System.out.println(ret);
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 1;
		} finally {
			if (ic != null) {
				ic.destroy();
			}
			System.exit(status);
		}
	}
}
