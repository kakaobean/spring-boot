package com.example.demo;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;


public class javaTest {
	public static void main(String[] args) {
		String str = "test";
		byte[] dd = str.getBytes(Charsets.UTF_8);
		
		String str256 = org.apache.commons.codec.digest.DigestUtils.sha256Hex(str);
		String str512 = org.apache.commons.codec.digest.DigestUtils.sha512Hex(str);
		System.out.println(str.getBytes(Charsets.UTF_8));
		System.out.println(StringUtils.getBytesUtf8(str));
		System.out.println(str256.length());
		System.out.println(str512.length());
		
		try{
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(str.getBytes("utf8"));
			String toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
			System.out.println(toReturn.length());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
