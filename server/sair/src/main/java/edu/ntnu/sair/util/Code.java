package edu.ntnu.sair.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.List;

public class Code {
	private final static String extraCode = "&*^%@%$^#&";
	private final static char[] hexDigits = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	private final static int[] argPosition = { 14, 1, 16, 9, 19, 30, 28, 23 };

	protected static String generateSid(List<String> strs) {
		String code = new String();
		for (String s : strs) {
			code += s;
		}
		code += extraCode;
		String sid = "";
		String code_result = encryptMD5(code);
		for (int i : argPosition) {
			sid += code_result.charAt(i);
		}
		return sid;
	}

	public static String encrypt(String input) {
		return encryptBASE64(encryptAES128(input));
	}

	public static String decrypt(String input) {
		return decryptAES128(decryptBASE64(input));
	}


	public static String encryptMD5(String input) {
		try {
			// Construct MessageDigest with MD5 type
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Change the type of content from String to Byte
			md.update(input.getBytes("UTF-8"));
			// Encrypt
			byte[] resultByteArray = md.digest();
			// Change the type of result from Byte to Hex
			char[] resultCharArray = new char[resultByteArray.length * 2];
			int index = 0;
			for (byte b : resultByteArray) {
				int v = b & 0xFF;
				resultCharArray[index++] = hexDigits[v >>> 4];
				resultCharArray[index++] = hexDigits[v & 0x0F];
			}
			return String.valueOf(resultCharArray);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected static byte[] encryptAES128(String input) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(
					Constant.AES_KEY.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			byte[] resultByteArray = cipher.doFinal(input.getBytes("UTF-8"));
			return resultByteArray;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected static String decryptAES128(byte[] input) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(
					Constant.AES_KEY.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
			byte[] resultByteArray = cipher.doFinal(input);
			return new String(resultByteArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected static String encryptBASE64(byte[] input) {
		try {
			return String.valueOf(Base64Coder.encode(input));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected static byte[] decryptBASE64(String str) {
		try {
			return Base64Coder.decode(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
