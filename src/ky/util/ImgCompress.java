package ky.util;

import java.io.*;  
import java.util.Date;  
import java.awt.*;  
import java.awt.image.*;  

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;  
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import com.sun.image.codec.jpeg.*;  
/** 
 * 图片压缩处理 
 * @author 崔素强 
 */  
public class ImgCompress {  
    private Image img;  
    private int width;  
    private int height;  
    private File targetFile;  
    
    
    @SuppressWarnings("deprecation")  
    public static void main(String[] args) throws Exception {  
        System.out.println("开始：" + new Date().toLocaleString());  
        
        String srcFile = "D:\\456.jpg";
        String destFile = "D:\\4561.jpg";
//        ImgCompress imgCom = new ImgCompress(srcFile,destFile);  
//        imgCom.resizeFix(800, 800);  
        
        File src = new File(srcFile);
        File desc = new File(destFile);
        compressPic(src,desc);
        System.out.println("结束：" + new Date().toLocaleString());  
    }  
    /** 
     * 构造函数 
     */  
    public ImgCompress(String fileName,String targetFileName) throws IOException {  
        File file = new File(fileName);// 读入文件  
        img = ImageIO.read(file);      // 构造Image对象  
        width = img.getWidth(null);    // 得到源图宽  
        height = img.getHeight(null);  // 得到源图长  
        
        this.targetFile = new File(targetFileName);
    }  
    
    /** 
     * 构造函数 
     */  
    public ImgCompress(File file,File targetFile) throws IOException {  
        img = ImageIO.read(file);      // 构造Image对象  
        width = img.getWidth(null);    // 得到源图宽  
        height = img.getHeight(null);  // 得到源图长  
        
        this.targetFile = targetFile;
    }  
    
    /** 
     * 按照宽度还是高度进行压缩 
     * @param w int 最大宽度 
     * @param h int 最大高度 
     */  
    public void resizeFix(int w, int h) throws IOException {  
        if (width / height > w / h) {  
            resizeByWidth(w);  
        } else {  
            resizeByHeight(h);  
        }  
    }  
    /** 
     * 以宽度为基准，等比例放缩图片 
     * @param w int 新宽度 
     */  
    public void resizeByWidth(int w) throws IOException {  
        int h = (int) (height * w / width);  
        resize(w, h);  
    }  
    /** 
     * 以高度为基准，等比例缩放图片 
     * @param h int 新高度 
     */  
    public void resizeByHeight(int h) throws IOException {  
        int w = (int) (width * h / height);  
        resize(w, h);  
    }  
    /** 
     * 强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     */  
    public void resize(int w, int h) throws IOException {  
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
        //File destFile = new File("C:\\temp\\456.jpg");  
        FileOutputStream out = new FileOutputStream(targetFile); // 输出到文件流  
        // 可以正常实现bmp、png、gif转jpg  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        encoder.encode(image); // JPEG编码  
        out.close();  
    }
    
    public static void compressPic(File srcFile, File descFile) throws IOException {
    	compressPic(srcFile,descFile,800,800);
    }
    
    public static void compressPic(File srcFile, File descFile,int targetW,int targetH) throws IOException {
    	ImgCompress imgCom = new ImgCompress(srcFile,descFile);  
        imgCom.resizeFix(targetW, targetH);  
    }
    
    
    
//    public static boolean compressPic(String srcFilePath, String descFilePath)  
//    {  
//        File file = null;  
//        BufferedImage src = null;  
//        FileOutputStream out = null;  
//        ImageWriter imgWrier;  
//        ImageWriteParam imgWriteParams;  
//  
//        // 指定写图片的方式为 jpg  
//        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();  
//        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);  
//        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT  
//        imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);  
//        // 这里指定压缩的程度，参数qality是取值0~1范围内，  
//        imgWriteParams.setCompressionQuality((float)0.1);  
//        imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);  
//        ColorModel colorModel = ColorModel.getRGBdefault();  
//        // 指定压缩时使用的色彩模式  
//        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel  
//                .createCompatibleSampleModel(16, 16)));  
//  
//        try  
//        {  
//             
//                file = new File(srcFilePath);  
//                src = ImageIO.read(file);  
//                out = new FileOutputStream(descFilePath);  
//  
//                imgWrier.reset();  
//                // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造  
//                imgWrier.setOutput(ImageIO.createImageOutputStream(out));  
//                // 调用write方法，就可以向输入流写图片  
//                imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);  
//                out.flush();  
//                out.close();  
//              
//        }  
//        catch(Exception e)  
//        {  
//            e.printStackTrace();  
//            return false;  
//        }  
//        return true;  
//    }  
//    
//    public static boolean compressPic(File srcFile, File descFile)  
//    {  
//        File file = srcFile;  
//        BufferedImage src = null;  
//        FileOutputStream out = null;  
//        ImageWriter imgWrier;  
//        ImageWriteParam imgWriteParams;  
//  
//        // 指定写图片的方式为 jpg  
//        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();  
//        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);  
//        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT  
//        imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);  
//        // 这里指定压缩的程度，参数qality是取值0~1范围内，  
//        imgWriteParams.setCompressionQuality((float)0.1);  
//        imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);  
//        ColorModel colorModel = ColorModel.getRGBdefault();  
//        // 指定压缩时使用的色彩模式  
//        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel  
//                .createCompatibleSampleModel(16, 16)));  
//  
//        try  
//        {  
//                src = ImageIO.read(file);  
//                out = new FileOutputStream(descFile);  
//  
//                imgWrier.reset();  
//                // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造  
//                imgWrier.setOutput(ImageIO.createImageOutputStream(out));  
//                // 调用write方法，就可以向输入流写图片  
//                imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);  
//                out.flush();  
//                out.close();  
//            
//        }  
//        catch(Exception e)  
//        {  
//            e.printStackTrace();  
//            return false;  
//        }  
//        return true;  
//    }  
//    
//    public static byte[] compressPic(byte[] data)  
//    {  
//    	ByteArrayInputStream is = new ByteArrayInputStream(data);  
//    	  
//    	BufferedImage src = null;  
//    	ByteArrayOutputStream out = null;  
//    	ImageWriter imgWrier;  
//    	ImageWriteParam imgWriteParams;  
//    	  
//    	// 指定写图片的方式为 jpg  
//    	imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();  
//    	imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);  
//    	// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT  
//    	imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);  
//    	// 这里指定压缩的程度，参数qality是取值0~1范围内，  
//    	imgWriteParams.setCompressionQuality((float)0.1/data.length);  
//    	                   
//    	imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);  
//    	ColorModel colorModel = ColorModel.getRGBdefault();  
//    	// 指定压缩时使用的色彩模式  
//    	imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel, colorModel  
//    	        .createCompatibleSampleModel(16, 16)));  
//    	  
//    	try  
//    	{  
//    	    src = ImageIO.read(is);  
//    	    out = new ByteArrayOutputStream(data.length);  
//    	  
//    	    imgWrier.reset();  
//    	    // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何 OutputStream构造  
//    	    imgWrier.setOutput(ImageIO.createImageOutputStream(out));  
//    	    // 调用write方法，就可以向输入流写图片  
//    	    imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);  
//    	      
//    	    out.flush();  
//    	    out.close();  
//    	    is.close();  
//    	    data = out.toByteArray();  
//    	}  
//    	catch(Exception e)  
//    	{  
//    	    e.printStackTrace();  
//    	}
//    	return data;
//    }  
    
    
}