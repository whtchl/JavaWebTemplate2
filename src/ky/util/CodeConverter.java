package ky.util;

import java.io.IOException;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CodeConverter {
	private static final char[] a = "0123456789ABCDEF".toCharArray();

	public static String bytesToHexString(byte[] paramArrayOfByte) {
		StringBuffer localStringBuffer = new StringBuffer(
				paramArrayOfByte.length);
		for (int i = 0; i < paramArrayOfByte.length; i++) {
			String str = Integer.toHexString(0xFF & paramArrayOfByte[i]);
			if (str.length() < 2)
				localStringBuffer.append(0);
			localStringBuffer.append(str.toUpperCase());
		}
		return localStringBuffer.toString();
	}

	public static byte[] str2Hex(byte[] paramArrayOfByte) {
		String str = new String(paramArrayOfByte);
		for (int i = 0; i < str.length(); i++)
			paramArrayOfByte[i] = (byte) str.charAt(i);
		return paramArrayOfByte;
	}

	/**
	 * @param paramInt 转换出来的 byte[]  长度为4 （高地位存在问题）
	 * @return
	 */
	public static byte[] int2bytes(int paramInt) {
		byte[] arrayOfByte = new byte[4];
		arrayOfByte[0] = (byte) (0xFF & paramInt);
		arrayOfByte[1] = (byte) ((0xFF00 & paramInt) >> 8);
		arrayOfByte[2] = (byte) ((0xFF0000 & paramInt) >> 16);
		arrayOfByte[3] = (byte) ((0xFF000000 & paramInt) >> 24);
		return arrayOfByte;
	}
	
	/**
	 * 数字 到 byte[]（如果数字integer的长度大于needLength,将出错 ）
	 * @param integer	转换数字（负数处理未测试）
	 * @param needLength	制定byte长度
	 * @return
	 */
	public static byte[] intToByteArray(final int integer,int needLength) {
		int byteNum = (40 - Integer.numberOfLeadingZeros(integer < 0 ? ~integer
				: integer)) / 8; //长度计算存在一定问题
		
		byte[] byteArray = new byte[needLength];

		for (int n = 0; n < byteNum; n++)
			byteArray[(byteArray.length-1) - n] = (byte) (integer >>> (n * 8));

		return (byteArray);
	}
	
	/**
	 * @param paramArrayOfByte 任意长度 （目前负数处理未测试）
	 * @return
	 */
	public static int byteArrayToInt(byte[] paramArrayOfByte) {
		 int value= 0;
	       for (int i = 0; i < paramArrayOfByte.length; i++) {
	           int shift= (paramArrayOfByte.length - 1 - i) * 8;
	           value +=(paramArrayOfByte[i + 0] & 0x000000FF) << shift;
	       }
	       return value;
	}
	
	/**
	 * 
	 * @param paramArrayOfByte  只能长度为4
	 * @return
	 */
	public static int bytes2int(byte[] paramArrayOfByte) {
		return (((paramArrayOfByte[0] & 0xFF) << 8 | paramArrayOfByte[1] & 0xFF) << 8 | paramArrayOfByte[2] & 0xFF) << 8
				| paramArrayOfByte[3] & 0xFF;
	}
	
	
	
	
	
	public static char byte2char(byte paramByte) {
		return (char) paramByte;
	}

	public static String bytes2BinaryStr(byte[] paramArrayOfByte) {
		StringBuffer localStringBuffer1 = new StringBuffer(
				paramArrayOfByte.length);
		String str;
		for (int i = 0; i < paramArrayOfByte.length; i++) {
			str = Integer.toHexString(0xFF & paramArrayOfByte[i]);
			if (str.length() < 2)
				localStringBuffer1.append(0);
			localStringBuffer1.append(str.toUpperCase());
		}
		StringBuffer localStringBuffer2 = new StringBuffer(localStringBuffer1
				.length() * 4);
		for (int j = 0; j < localStringBuffer1.length(); j++) {
			str = Integer.toBinaryString(Integer.parseInt(localStringBuffer1
					.charAt(j)
					+ "", 16));
			int k = str.length();
			if (k < 2)
				localStringBuffer2.append("000");
			else if (k < 3)
				localStringBuffer2.append("00");
			else if (k < 4)
				localStringBuffer2.append("0");
			localStringBuffer2.append(str);
		}
		return localStringBuffer2.toString();
	}

	public static String bcd2DecString(byte[] paramArrayOfByte) {
		StringBuffer localStringBuffer = new StringBuffer(
				paramArrayOfByte.length * 2);
		for (int i = 0; i < paramArrayOfByte.length; i++) {
			localStringBuffer
					.append((byte) ((paramArrayOfByte[i] & 0xF0) >>> 4));
			localStringBuffer.append((byte) (paramArrayOfByte[i] & 0xF));
		}
		return localStringBuffer.toString().substring(0, 1).equalsIgnoreCase(
				"0") ? localStringBuffer.toString().substring(1)
				: localStringBuffer.toString();
	}

	public static byte[] decString2BCD(String paramString) {
		int i = paramString.length();
		int j = i % 2;
		if (j != 0) {
			paramString = "0" + paramString;
			i = paramString.length();
		}
		byte[] arrayOfByte1 = new byte[i];
		if (i >= 2)
			i /= 2;
		byte[] arrayOfByte2 = new byte[i];
		arrayOfByte1 = paramString.getBytes();
		for (int n = 0; n < paramString.length() / 2; n++) {
			int k;
			if ((arrayOfByte1[(2 * n)] >= 48) && (arrayOfByte1[(2 * n)] <= 57))
				k = arrayOfByte1[(2 * n)] - 48;
			else if ((arrayOfByte1[(2 * n)] >= 97)
					&& (arrayOfByte1[(2 * n)] <= 122))
				k = arrayOfByte1[(2 * n)] - 97 + 10;
			else
				k = arrayOfByte1[(2 * n)] - 65 + 10;
			int m;
			if ((arrayOfByte1[(2 * n + 1)] >= 48)
					&& (arrayOfByte1[(2 * n + 1)] <= 57))
				m = arrayOfByte1[(2 * n + 1)] - 48;
			else if ((arrayOfByte1[(2 * n + 1)] >= 97)
					&& (arrayOfByte1[(2 * n + 1)] <= 122))
				m = arrayOfByte1[(2 * n + 1)] - 97 + 10;
			else
				m = arrayOfByte1[(2 * n + 1)] - 65 + 10;
			int i1 = (k << 4) + m;
			arrayOfByte2[n] = (byte) i1;
		}
		return arrayOfByte2;
	}

	public static String bcd2ASCII(byte[] paramArrayOfByte) {
		StringBuffer localStringBuffer = new StringBuffer(
				paramArrayOfByte.length * 2);
		for (int i = 0; i < paramArrayOfByte.length; i++) {
			int j = (paramArrayOfByte[i] & 0xF0) >>> 4;
			int k = paramArrayOfByte[i] & 0xF;
			localStringBuffer.append(a[j]).append(a[k]);
		}
		return localStringBuffer.toString();
	}

	public static byte twoASCII2Byte(byte paramByte1, byte paramByte2) {
		int i = Byte.decode("0x" + new String(new byte[] { paramByte1 }))
				.byteValue();
		i = (byte) (i << 4);
		int j = Byte.decode("0x" + new String(new byte[] { paramByte2 }))
				.byteValue();
		return (byte) (i ^ j);
	}

	public static String bytes2Base64(byte[] paramArrayOfByte) {
		BASE64Encoder localBASE64Encoder = new BASE64Encoder();
		return localBASE64Encoder.encode(paramArrayOfByte);
	}

	public static byte[] base642Bytes(String paramString) {
		BASE64Decoder localBASE64Decoder = new BASE64Decoder();
		try {
			return localBASE64Decoder.decodeBuffer(paramString);
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
		}
		return null;
	}

	public static String str2DecASCIIStr(String paramString) {
		StringBuffer localStringBuffer = new StringBuffer();
		for (int i = 0; i < paramString.length(); i++) {
			int j = paramString.charAt(i);
			localStringBuffer.append(j);
		}
		return new String(localStringBuffer);
	}

	public static String str2HexASCIIStr(String paramString) {
		StringBuffer localStringBuffer = new StringBuffer();
		for (int i = 0; i < paramString.length(); i++) {
			int j = paramString.charAt(i);
			localStringBuffer.append(Integer.toHexString(j));
		}
		return new String(localStringBuffer);
	}

	public static String str2BinaryASCIIStr(String paramString) {
		StringBuffer localStringBuffer = new StringBuffer();
		for (int i = 0; i < paramString.length(); i++) {
			int j = paramString.charAt(i);
			localStringBuffer.append(Integer.toBinaryString(j));
		}
		return new String(localStringBuffer);
	}

	public static String dec2Hex(int paramInt) {
		String str = Integer.toHexString(paramInt);
		if (str.length() < 2)
			str = "0" + str;
		return str;
	}

	public static String dec2Hex4Bit(int paramInt) {
		String str = Integer.toHexString(paramInt);
		int i = str.length();
		if (i == 0)
			str = "0000";
		else if (i == 1)
			str = "000" + str;
		else if (i == 2)
			str = "00" + str;
		else if (i == 3)
			str = "0" + str;
		return str;
	}

	public static String dec2Hex4App(int paramInt) {
		String str = Integer.toHexString(paramInt);
		int i = str.length();
		if (i < 2)
			str = "00" + str;
		else if (i < 3)
			str = "0" + str;
		return str;
	}

	public static int hex2Dec(String paramString) {
		return Integer.parseInt(paramString, 16);
	}

	public static String BinaryStr2HexStr(String paramString) {
		for (int i = paramString.length(); i % 8 != 0; i = paramString.length())
			paramString = "0" + paramString;
		int j = paramString.length() / 8;
		StringBuffer localStringBuffer = new StringBuffer(j * 2);
		for (int k = 0; k < j; k++) {
			int m = k * 8;
			String str = Integer.toHexString(Integer.parseInt(paramString
					.substring(m, m + 8), 2));
			if (str.length() < 2)
				localStringBuffer.append("0").append(str);
			else
				localStringBuffer.append(str);
		}
		return localStringBuffer.toString();
	}

	public static int BinaryStr2Dec(String paramString) {
		return Integer.parseInt(paramString, 2);
	}

	public static String HexStr2BinaryStr(String paramString) {
		int i = paramString.length();
		if (i % 2 != 0) {
			paramString = "0" + paramString;
			i = paramString.length();
		}
		int j = i / 2;
		StringBuffer localStringBuffer = new StringBuffer(j * 8);
		for (int k = 0; k < j; k++) {
			int m = k * 2;
			String str = Integer.toBinaryString(Integer.parseInt(paramString
					.substring(m, m + 2), 16));
			int n = str.length();
			if (n < 8)
				for (int i1 = 0; i1 < 8 - n; i1++)
					localStringBuffer.append("0");
			localStringBuffer.append(str);
		}
		return localStringBuffer.toString();
	}

	private static byte a(String paramString1, String paramString2) {
		int i = Byte.decode("0x" + paramString1).byteValue();
		i = (byte) (i << 4);
		int j = Byte.decode("0x" + paramString2).byteValue();
		return (byte) (i | j);
	}

	public static byte[] hexStringToByte(String paramString) {
		int i = 0;
		int j = 0;
		int k = paramString.length() / 2;
		byte[] arrayOfByte = new byte[k];
		for (int m = 0; m < k; m++) {
			i = m * 2 + 1;
			j = i + 1;
			arrayOfByte[m] = a(paramString.substring(m * 2, i), paramString
					.substring(i, j));
		}
		return arrayOfByte;
	}

	public static String bytes2DecString(byte[] paramArrayOfByte) {
		StringBuffer localStringBuffer = new StringBuffer(
				paramArrayOfByte.length);
		localStringBuffer.append(new String(paramArrayOfByte));
		return localStringBuffer.toString();
	}
	
	public static String strXor(String srcStringA, String srcStringB) //hexString
	{
		if(srcStringA == null || srcStringA.equals("") ||
				srcStringB == null || srcStringB.equals(""))
		{
			return null;
		}
		
		byte[] aByte = CodeConverter.hexStringToByte(srcStringA);
		byte[] bByte = CodeConverter.hexStringToByte(srcStringB);
		
		int len = aByte.length;
		
		if(len != bByte.length)
		{
			return null;
		}
		
		byte[] retByte = new byte[len];
		
		for(int i = 0; i < len; i++)
		{
			retByte[i] = (byte) ((int)aByte[i] ^ (int)bByte[i]);
		}
		
		return CodeConverter.bytesToHexString(retByte);
	}
	
	//ȡ��
	public static String dataNegative(String srcData)
	{
		if(srcData == null || srcData.equals(""))
		{
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < srcData.length(); i++)
		{
			buffer.append("F");
		}
		
		String mode = new String(buffer);
		return strXor(srcData, mode);
	}
	
	//TLV
	public static byte[] toTLVHexByte(String head, String len, byte[] value)
	{
		byte[] bTag = hexByte2String(head);
		byte[] bLen = hexByte2String(len);
		
		byte[] data = new byte[bTag.length + bLen.length + value.length];
		
		System.arraycopy(bTag, 0, data, 0, bTag.length);
		System.arraycopy(bLen, 0, data, bTag.length, bLen.length);
		System.arraycopy(value, 0, data, bTag.length + bLen.length, value.length);
		
		return data;
	}
	public static byte[] toTLVHexByte(String head, String len, int lenByte, byte[] value)
	{
		byte[] bTag = hexByte2String(head);
		byte[] bLen = hexByte2String(len, 2);
		
		byte[] data = new byte[bTag.length + bLen.length + value.length];
		
		System.arraycopy(bTag, 0, data, 0, bTag.length);
		System.arraycopy(bLen, 0, data, bTag.length, bLen.length);
		System.arraycopy(value, 0, data, bTag.length + bLen.length, value.length);
		
		return data;
	}
	
	public static byte[] hexByte2String(String value)
	{
		return CodeConverter.hexStringToByte(value);
	}
	
	public static byte[] hexByte2String(String value, int len)
	{
		byte[] hex = hexByte2String(value);
		
		int hexLen = hex.length;
		
		int requestLen = len - hexLen;
		
		byte[] tmp = new byte[len];
		
		while(hexLen < len)
		{
			byte[] apand = new byte[]{0x00};
			System.arraycopy(apand, 0, tmp, 0, 1);
			
			hexLen++;
		}
		
		System.arraycopy(hex, 0, tmp, requestLen, hex.length);
		
		return tmp;
	}
	
	//asc to bcd(cn type)
	public static byte[] ascStr2BCD_CN(String data)
	{
		return CodeConverter.decString2BCD(data);
	}
	
	//asc (an type)
	public static byte[] ascStr2ASC_AN(String data)
	{
		return data.getBytes();
	}
	
	//asc to hex string to bcd(b type)
	public static byte[] ascStr2BCD_B(String data)
	{
		return CodeConverter.decString2BCD(new String(CodeConverter.str2Hex(data.getBytes())));
	}
	
	//hex string to bcd(b type)
	public static byte[] hexStr2BCD_B(String data)
	{
		return CodeConverter.decString2BCD(data);
	}
	
	public static void main(String[] args) {
		String aa = "77656978696e3a2f2f77787061792f62697a70617975726c3f70723d5356727a43686c";
		byte[] bb = hexStringToByte(aa);
		System.out.println(CodeConverter.bytes2DecString(bb));
	}
}