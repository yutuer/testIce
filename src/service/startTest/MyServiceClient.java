package service.startTest;

import Ice.ObjectPrx;

import com.my.demo.demo.MyservicePrx;
import com.my.demo.demo.MyservicePrxHelper;

public class MyServiceClient {
	
	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			ic = Ice.Util.initialize(args);
			ObjectPrx base = ic.stringToProxy("Locator/MyService1:default -p 10002");
			MyservicePrx myservicePrx = MyservicePrxHelper.checkedCast(base);
			String ret = myservicePrx.hellow();
			System.out.println(ret);
		} catch (Exception e) {
			e.printStackTrace();
			status = 1;
		}finally{
			if(ic != null){
				ic.destroy();
			}
			System.exit(status);
		}
	}
}
