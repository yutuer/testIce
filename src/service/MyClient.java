package service;

import com.my.demo.demo.MyservicePrx;
import com.my.demo.demo.MyservicePrxHelper;

public class MyClient {

	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;

		try {
			ic = Ice.Util.initialize();
			Ice.ObjectPrx base = ic.stringToProxy("MyService:default -p 10001");
			MyservicePrx proxy = MyservicePrxHelper.checkedCast(base);
			if (proxy != null) {
				String ret = proxy.hellow();
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
