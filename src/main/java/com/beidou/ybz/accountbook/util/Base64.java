
package com.beidou.ybz.accountbook.util;

import java.io.IOException;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;


@SuppressWarnings("restriction")
public class Base64 {

	public static String encode(byte[] encodeBytes) {
		return new BASE64Encoder().encode(encodeBytes);
	}

	public static byte[] decode(String s) {
		byte[] result = null;
		if (s == null) {
			return result;
		}

		BASE64Decoder base64decoder = new BASE64Decoder();
		try {
			result = base64decoder.decodeBuffer(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	} 
}