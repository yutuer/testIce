package service;

import Ice.Current;

import com.my.demo.demo._MyserviceDisp;

public class MyServiceImpl extends _MyserviceDisp{

	private static final long serialVersionUID = 3169602192267730741L;

	@Override
	public String hellow(Current __current) {
		return "Hello world";
	}

}