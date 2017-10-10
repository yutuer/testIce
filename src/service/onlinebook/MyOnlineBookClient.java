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
			Ice.ObjectPrx base = ic.stringToProxy("OnlineBook:default -p 20001");
			OnlineBookPrx proxy = OnlineBookPrxHelper.checkedCast(base);
			if (proxy != null) {
				Message bookMsg = new Message();
				bookMsg.name = "Mr Wang";
				bookMsg.type = 3;
				bookMsg.price = 9.99;
				bookMsg.valid = true;
				bookMsg.content = "aaaa";
				System.out.println(bookMsg);
				
				Message ret = proxy.bookTick(bookMsg);
				
				System.out.println(ret);
				System.out.println(ret.content);
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
