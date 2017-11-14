package com.ksu.grad.service.convertors;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ksu.grad.enumerations.DigestType;

public class StringConverter 
{
	public static String ToDigest(String strToConvert, DigestType digestAlgorithm){
		try{
			MessageDigest digest = MessageDigest.getInstance(digestAlgorithm.getDescription());
			byte[] hash = digest.digest(strToConvert.getBytes(StandardCharsets.UTF_8));
			StringBuilder outputHash = new StringBuilder();
            for (int i: hash) {
            	outputHash.append(Integer.toHexString(0XFF & i));
            }
            return outputHash.toString();

		} catch (NoSuchAlgorithmException ex){
			throw new RuntimeException(ex);
		}
	}
}
