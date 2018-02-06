package ky.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.com.tass.API.bankZW.BankApi;
import cn.com.tass.Tools.Form;

public class EncryptGyHsm032  {
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	private final String ModelName = "EncryptGyHsm032";


	private String ip;
	private int port;

	public List<String> GenKEK(int iKEKLen){
		List<String> listRes = new ArrayList<String>();

		try {
			// 连接加密机程序
			BankApi bk = new BankApi();

			bk.connHSM(ip, port);

			Map<String, Object> ret = new HashMap<String, Object>();
			String zmk = "";
			String tmk = "";
			String chkVal = "";
			
			int rc = bk.Tass_Generate_key(0, 0, "", "000", "X", ret);
			
			if (rc == 0) {
				zmk = (String) ret.get("skeyByZmk");// 
				tmk = (String) ret.get("skeyByLmk");// 存储 主密钥
				chkVal = (String) ret.get("sCkv");
				
				listRes.add(0, tmk);
				
				zmk = Tass_DecData(bk,tmk);
				
				listRes.add(1, zmk);
				
				listRes.add(2, chkVal);

			} else {
				System.out.println(rc);
			}
			System.out.println("zmk :" + zmk);
			System.out.println("lmk :" + tmk);
			System.out.println("kcv :" + chkVal);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listRes;
	}
	
	
	
	/***
	 * 解密数据 0A96814C3764EBB816977171AB528874
	 */
	private String Tass_DecData(BankApi bk,String hexData){
		int rc = 0;
		int keyType = 2;   //解密密钥类型
		int EncDecMode = 0;
		int keyIndex = 0;  //索引，为0使用密文
		String key = "X65E5E955CEDECC6E65E5E955CEDECC6E";  //密钥密文
		String PAD = "05";   //填充模式
		int dataLen = 16;    //数据长度
		byte[] data = Form.hexStringToByte(hexData);  //要加密的数据
		String IV = "";    
		int operateFlag = 2;   //2为解密
		Map<String,Object> ret3 = new HashMap<String, Object>();
		
		try{
			if(bk == null){
				bk = new BankApi();

				bk.connHSM(ip, port);
			}
			
			rc = bk.Tass_EncAndDecData(keyType, EncDecMode, keyIndex, key, PAD, data.length, data, IV, operateFlag, ret3);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if(rc != 0){
			System.out.println("返回码：" + rc);
			
			
		}
		System.out.println("解密后的结果："+Form.byteToHexString((byte[])ret3.get("outData")));
		
		return Form.byteToHexString((byte[])ret3.get("outData"));
	}
	
	
	
	/**
	 * 转加密kek
	 * @param keyIndex
	 * @param key
	 * @param sCiperZMK_kek
	 * @return
	 */
	public List<String> AcceptKek(int keyIndex, String key,
			String sCiperZMK_kek )
	{
		List<String> listRes = new ArrayList<String>();
		try {
			// 连接加密机程序
			BankApi bk = new BankApi();
			bk.connHSM(ip, port);

			List<String> result = AcceptKey(bk, keyIndex, key, sCiperZMK_kek,
					"000", "X");
			listRes.add(0, result.get(0));
			listRes.add(1, result.get(1));

			return listRes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRes;
	}
	
	
	
	
	/**
	 * 主要是转加密 key（zmk=》lmk）
	 * mackey (keytype=001,keyFlag=X)
	 * pinkey (keytype=008,keyFlag=Z)
	 * @param keyIndex
	 * @param sCiperZMK
	 * @param keytype 
	 * @param keyFlag
	 * @return
	 * @throws JPreException
	 */
	public List<String> AcceptKey(BankApi bk,int keyIndex,String key,String sCiperZMK,String keytype, String keyFlag	){
		// TODO Auto-generated method stub
		List<String> listRes = new ArrayList<String>();
		try {

			// 连接加密机程序
			if(bk == null){
				bk = new BankApi();
				bk.connHSM(ip, port);
			}

			Map<String, Object> ret = new HashMap<String, Object>();
			String keyLmk = "";
			String keyLmkChkVal = "";
	
			int rc1 = bk.Tass_Accept_key(keyIndex, key, sCiperZMK, keytype, keyFlag, ret);
			if (rc1 == 0) {
				keyLmk = (String) ret.get("skeyByLmk");// 存储  
				
				
				keyLmkChkVal = (String) ret.get("sCkv");

				listRes.add(0, keyLmk);
				listRes.add(1, keyLmkChkVal);
			} else {
				System.out.println(rc1);
			}
		
			return listRes;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRes;
	}
	
	//合成pms_business_pos表的 kek 字段（mother_key--pms_channel_info表的mother_key）
	public String composeKeK(String mother_key,String key){
	EncryptGyHsm032 hsm = new EncryptGyHsm032();
	    //加密机ip和端口
		hsm.setIp("172.16.1.20");
		hsm.setPort(8018);
		String newkey=null;
		
		try {
			String key_zmk = "X"+key;
			List<String> result = hsm.AcceptKek(0, mother_key,  key_zmk);
			String kekLmk = result.get(0);
			String kekcheck=result.get(1);
			newkey=kekLmk+","+kekcheck;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newkey;
	}
	public static void main(String[] args) {
		String[] str={
				"CD60632079FB3126CF9511C1644B459F",
				"4E36E20696B39D21B3B843FAA8824AAA",
				"C2148DA67F0D9EBA45789A5847BDC924",
				"034CAC1ABE885EAC1B3DA90F7B87DA66",
				"DCEB9B2B8E5482DC1A450E60FE1378B1",
				"EC1AF0BBAF7EFB6D96F5064D2C06084C",
				"3E94AD638DA9FBE74B244381EFCDB145",
				"2735497712F553252566D00833259A55",
				"0A60D5D0306766788A1B23B806A52053",
				"003EEC99C92F7F515C9E53FF78FFFF8D",
				"F020C7B24328F1945DEF186CB27F63E3",
				"66BB12E865A21537D25DF842240B817B"};
		EncryptGyHsm032 egh=new EncryptGyHsm032();
		System.out.println(egh.composeKeK("XFC21FB30DFB13C99515AC6DCD1E8516A", "1DDE84667315C3153B0B81D06A42B1E6"));
	}
}
