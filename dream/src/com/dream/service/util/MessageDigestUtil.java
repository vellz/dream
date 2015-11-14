package com.dream.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 消息签名工具类
 * 
 * @author zhaowei
 */
public class MessageDigestUtil {

	// public static String hashToMD5Hex(String sourceStr) {
	// // TODO Auto-generated method stub
	// String signStr="";
	// try {
	// byte[] bytes = sourceStr.getBytes("UTF-8");
	// MessageDigest md5 = MessageDigest.getInstance("MD5");
	// md5.update(bytes);
	// byte[] md5Byte = md5.digest();
	// if(md5Byte != null){
	// signStr = HexBin.encode(md5Byte);
	// }
	// } catch (NoSuchAlgorithmException e) {
	// e.printStackTrace();
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	// return signStr;
	// }

	/**
	 * 转换为32位的小写字母字符串
	 * 
	 * @param bts
	 * @return
	 */
	public static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	/**
	 * 生成消息摘要
	 * 
	 * @param buffer
	 * @param key
	 * @return
	 */
	public static String getMessageDigest(byte[] buffer, String key) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(key);
			digest.update(buffer);
			return bytes2Hex(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getMD5(byte[] buffer) {
		return getMessageDigest(buffer, "MD5");
	}

	public static String getMD5(String str) {
		try {
			return getMD5(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public static String getSHA1(byte[] buffer) {
		return getMessageDigest(buffer, "SHA-1");
	}

	public static String getSHA1(String str) {
		try {
			return getSHA1(str.getBytes("ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSHA256(byte[] buffer) {
		return getMessageDigest(buffer, "SHA-256");
	}

	public static String getSHA256(String str) {
		try {
			return getSHA256(str.getBytes("ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 文件生成md5
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileMD5(File file) {
		InputStream is;
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			is = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int readNum = 0;
			while ((readNum = is.read(buffer)) > 0) {
				digest.update(buffer, 0, readNum);
			}
			is.close();
			return bytes2Hex(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * MD5 加密
	 */
	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {

		}
		return resultString;
	}

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static void main(String[] args) {
		String m = "123456";
		// String a=getFileMD5(new File("D://magazine.apk"));
		// FileUtil.readFile("", "");
		// String m=a+"magazine.apk"+2473332;
		// System.out.println(m);
		System.out.println(getMD5(m));
		// System.out.println(DigestUtils.md5Hex("123456"));
		// String a = "2",b = null;
		// System.out.println(a);
	}
}
