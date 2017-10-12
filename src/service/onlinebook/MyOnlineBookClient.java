package service.onlinebook;

import com.hp.tel.ice.book.Message;
import com.hp.tel.ice.book.OnlineBookPrx;
import com.hp.tel.ice.book.OnlineBookPrxHelper;

public class MyOnlineBookClient {

	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;

		try {
			String[] params = new String[]{"--Ice.Default.Locator=IceGrid/Locator:tcp -h localhost -p 4061"};
			ic = Ice.Util.initialize(params);
			
			Ice.ObjectPrx base = ic.stringToProxy("OnlineBook");
				Message bookMsg = new Message();
				bookMsg.name = "Mr Wang";
				bookMsg.type = 3;
				bookMsg.price = 9.99;
				bookMsg.valid = true;
				bookMsg.content = "aaaa";
				System.out.println(bookMsg);
				
				int count = 1;
				long start = System.currentTimeMillis();
				OnlineBookPrx proxy = OnlineBookPrxHelper.checkedCast(base);
				if (proxy != null) {
					for(int i=0;i<count;i++){
						Message ret = proxy.bookTick(bookMsg);
						Thread.sleep(1000L);
					}
				}
				long used = System.currentTimeMillis() - start;	
				System.out.println("tps " + count * 1000 / used);
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
