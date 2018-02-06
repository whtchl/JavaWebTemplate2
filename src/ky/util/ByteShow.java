/*
 * Created on 2005-9-5
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ky.util;

/**
 * @author IBM USER
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ByteShow {
	public static String show(byte[] b)
	{
		char[] ch = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		
					java.io.StringWriter stream = new java.io.StringWriter();
			
					stream.write("[");
					int len = b.length;
					for(int i = 0; i < len ; i++)
					{
						byte c = (byte)((b[i] & 0xf0) >> 4);
						stream.write(ch[c]);
						c = (byte)(b[i] & 0x0f);
						stream.write(ch[c]);
						stream.write(" ");	    
					}
					stream.write("]");
					return stream.toString();
	}
	
	public static String show(byte[] b,int maxLen)
		{
			char[] ch = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		
			java.io.StringWriter stream = new java.io.StringWriter();
			
			stream.write("[");
			int len = b.length > maxLen?maxLen:b.length;
			for(int i = 0; i < len ; i++)
			{
				byte c = (byte)((b[i] & 0xf0) >> 4);
				stream.write(ch[c]);
				c = (byte)(b[i] & 0x0f);
				stream.write(ch[c]);
				stream.write(" ");	    
			}
			if(b.length > maxLen)
				stream.write("...]");
			else 
			stream.write("]");
			return stream.toString();
		}
	
	/*
	 * 
	 public static void main(String[] args) {
		AscBcd a = new AscBcd();
		String s = new String("12345678");
		byte[] b = s.getBytes();
		//byte[] c = a.(b);
		s=ByteShow.show(b,8);
		System.out.print(s);
		s=ByteShow.show(c,4);
		System.out.print(s);
	}
	*/
}


