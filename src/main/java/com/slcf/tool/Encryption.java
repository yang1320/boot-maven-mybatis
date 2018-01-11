package com.slcf.tool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;


public class Encryption {
	

	public static String encryption(String pwd) throws NoSuchAlgorithmException{
		BASE64Encoder be=new BASE64Encoder();
		MessageDigest md=MessageDigest.getInstance("MD5");
		pwd=be.encode(md.digest(pwd.getBytes()));
		return pwd;
	}
	
	
}
