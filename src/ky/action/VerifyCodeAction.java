package ky.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import ky.entity.TSysUser;

@ParentPackage("tiles-default")
@Namespace("/verifycode")
public class VerifyCodeAction extends BaseAction<TSysUser> {
	
	 public Color getRandColor(int fc, int bc)
	   {
	     Random random = new Random();
	     if (fc > 255)
	       fc = 255;
	     if (bc > 255)
	       bc = 255;
	     int r = fc + random.nextInt(bc - fc);
	     int g = fc + random.nextInt(bc - fc);
	     int b = fc + random.nextInt(bc - fc);
	     return new Color(r, g, b);
	   }
	 
	 @Action(value="verifyCode",results={@Result(name="verifyCode",location="/login.jsp")})
	 public String verifyCode(){
	     int width = 60; int height = 20;
	     BufferedImage image = new BufferedImage(width, height, 
	       1);
	 
	     Graphics g = image.getGraphics();
	 
	     Random random = new Random();
	 
	     g.setColor(getRandColor(200, 250));
	     g.fillRect(0, 0, width, height);
	 
	     g.setFont(new Font("Times New Roman", 0, 18));
	 
	     g.setColor(getRandColor(160, 200));
	     for (int i = 0; i < 155; i++) {
	       int x = random.nextInt(width);
	       int y = random.nextInt(height);
	       int xl = random.nextInt(12);
	       int yl = random.nextInt(12);
	       g.drawLine(x, y, x + xl, y + yl);
	     }
	 
	     String codeList = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijklmnpqrstuvwxyz123456789";
	 
	     String sRand = "";
	 
	     for (int i = 0; i < 4; i++) {
	       int a = random.nextInt(codeList.length() - 1);
	       String rand = codeList.substring(a, a + 1);
	       sRand = sRand + rand;
	 
	       g.setColor(
	         new Color(20 + random.nextInt(110), 20 + random
	         .nextInt(110), 20 + random.nextInt(110)));
	       g.drawString(rand, 13 * i + 6, 16);
	     }
	 
	     this.session.setAttribute("verifycode", sRand);
	 
	     this.response.setHeader("Pragma", "no-cache");
	     this.response.setHeader("Cache-Control", "no-cache");
	     this.response.setDateHeader("Expires", 0L);
	     this.response.setContentType("image/jpeg");
	 
	     g.dispose();
	     try
	     {
	       ImageIO.write(image, "JPEG", this.response.getOutputStream());
	     } catch (Exception e) {
	       e.printStackTrace();
	     }
	     return null;
	   }
}
