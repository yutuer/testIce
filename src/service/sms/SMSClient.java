package service.sms;

import com.hp.tel.ice.message.SMSServicePrx;
import com.hp.tel.ice.message.SMSServicePrxHelper;

import Ice.ObjectPrx;

public class SMSClient {
	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		
		try {
			ic = Ice.Util.initialize();
			ObjectPrx stringToProxy = ic.stringToProxy("SMSService:default -p 20002");
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
