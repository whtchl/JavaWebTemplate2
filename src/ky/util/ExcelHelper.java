package ky.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * base on jxl.jar
 * @author changbo
 *
 */
public class ExcelHelper {
	
	public static final int READ = 0;
	public static final int WRITE = 1;
	
	private String fileName = null;
	private int operateModel;
	private int lineIndex;
	private Workbook wb = null;
	private Sheet sheet = null;
	private WritableWorkbook wwb = null;
	private WritableSheet ws = null;
	
	//default
	WritableCellFormat format = new WritableCellFormat();

	private ExcelHelper(String fileName, int operateModel){
		this.fileName = fileName;
		this.operateModel = operateModel;
	}
	
	public static ExcelHelper getExcelHelper(String fileName, int operateModel){
		return new ExcelHelper(fileName, operateModel);
	}
	
	/**
	 * start with 0
	 * @param sheetNum
	 * @return
	 */
	public ExcelHelper open(int sheetNum){
		try {
			File file = new File(fileName);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			if(operateModel == READ){
				wb = Workbook.getWorkbook(new FileInputStream(fileName));
				sheet = wb.getSheet(sheetNum);
			}else if(operateModel == WRITE){
				wwb = Workbook.createWorkbook(new FileOutputStream(fileName));
				if(wwb.getSheets().length == 0){
					ws = wwb.createSheet("1", 1);
				}else{
					ws = wwb.getSheet(sheetNum);
				}
				//
				format.setVerticalAlignment(VerticalAlignment.CENTRE);
				format.setAlignment(Alignment.LEFT);
				format.setWrap(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return this;
		}
		return this;
	}
	
	public boolean setColumnView(int[] columWide){
		if(columWide == null || columWide.length == 0){
			return false;
		}
		for(int i=0; i<columWide.length; i++){
			ws.setColumnView(i, columWide[i] <= 0 ? 1 : columWide[i]);
		}
		return true;
	}
	
	public Map<Integer, String> readLine(){
		return readLine(false);
	}
	
	public Map<Integer, String> readLine(boolean ignoreBlankRow){
		if(lineIndex >= sheet.getRows()){
			return null;
		}
		Map<Integer, String> map = getRows(lineIndex);
		lineIndex++;
		
		//if blank, get next row
		if(ignoreBlankRow && isEmptyData(map)){
			return readLine(ignoreBlankRow);
		}
		
		return map;
	}
	
	public boolean writeLine(String[] strs){
		if(lineIndex > 65535){
			System.out.println("[ExcelHelper]lineIndex="+lineIndex);
			return false;
		}
		
		if(!isEmptyData(strs)){
			Label label = null;
			for(int i=0;i<strs.length; i++){
				if(hasValue(strs[i])){
					label = new Label(i, lineIndex, getValue(strs[i]), format);
					try {
						ws.addCell(label);
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}
			}
		}
		lineIndex++;
		return true;
	}
	
	public boolean writeLine(Map<Integer, String> map){
		if(!isEmptyData(map)){
			Label label = null;
			for(Integer i : map.keySet()){
				if(hasValue(map.get(i))){
					label = new Label(i, lineIndex, getValue(map.get(i)), format);
					try {
						ws.addCell(label);
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
				}
			}
		}
		lineIndex++;
		return true;
	}
	
	public Map<Integer, String> getRows(int index){
		if(index >= 65535){
			System.out.println("[ExcelHelper]index="+index);
			return null;
		}
		Map<Integer, String> map = new HashMap<Integer, String>();
		Cell cell = null;
		for(int j=0; j<sheet.getColumns(); j++){
			cell = sheet.getCell(j,index);
			map.put(j, getValue(cell.getContents()));
		}
		return map;
	}

	private boolean isEmptyData(String[] strs){
		boolean isEmpty = true;
		if(strs != null && strs.length > 0){
			for(String i : strs){
				if(hasValue(i)){
					isEmpty = false;
				}
			}
		}
		return isEmpty;
	}
	private boolean isEmptyData(Map<Integer, String> map){
		boolean isEmpty = true;
		if(map != null && map.size() > 0){
			for(Integer i : map.keySet()){
				if(hasValue(map.get(i))){
					isEmpty = false;
				}
			}
		}
		return isEmpty;
	}
	
	public void close(){
		if(wb != null){
			wb.close();
		}
		if(wwb != null){
			try {
				wwb.write();
				wwb.close();
			} catch (Exception e) {
				
			}
		}
		
	}

	public static String getValue(String s){
		if(s == null){
			return "";
		}
		return s.trim();
	}
	public static boolean hasValue(String s){
		if(s != null && s.trim().length() > 0){
			return true;
		}else{
			return false;
		}
	}
}
