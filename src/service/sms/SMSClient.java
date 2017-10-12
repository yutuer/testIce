package service.sms;

import com.hp.tel.ice.message.SMSServicePrx;
import com.hp.tel.ice.message.SMSServicePrxHelper;

import Ice.ObjectPrx;

public class SMSClient {
	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		
		try {
			String[] params = new String[]{"--Ice.Default.Locator=IceGrid/Locator:tcp -h localhost -p 4061"};
			ic = Ice.Util.initialize(params);
			
			ObjectPrx stringToProxy = ic.stringToProxy("SMSService");
			SMSServicePrx smsServicePrx = SMSServicePrxHelper.checkedCast(stringToProxy);
			smsServicePrx.sendSMS("book aaa");
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
