package ntu.ist.identity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Identity extends HttpServlet{
	public Identity(){
		super();
	}
	@Override
	public void destroy(){
		super.destroy();
	}
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
			response.setContentType("text/html;charset=utf-8");
			int width=78;
			int height=20;
			//创建对象
			BufferedImage bim=new BufferedImage(68,20,BufferedImage.TYPE_INT_BGR);
			//获取绘图笔对象g
			Graphics g=bim.getGraphics();
			Random rm=new Random();
			g.setColor(new Color(rm.nextInt(100),205,rm.nextInt(100)));
			g.fillRect(0, 0, width, height);
			StringBuffer sbf=new StringBuffer();
			//输出数字
			for(int i=0;i<4;i++){
				g.setColor(Color.black);
				g.setFont(new Font("华文隶书",Font.BOLD|Font.ITALIC,22));
				int n=rm.nextInt(10);
				sbf.append(n);
				g.drawString(""+n, (i*15)+5, 18);
				
			}
			//生成验证码保存到session中
			HttpSession session=request.getSession(true);
			session.setAttribute("piccode",sbf);
			//禁止缓存
			response.setHeader("Prama", "no-cache");
			response.setHeader("Coche-Control", "no-cache");
			response.setDateHeader("Expires",0);
			response.setContentType("image/jpeg");
			//将bim图片以"JPG"格式返回到浏览器
			ImageIO.write(bim,"JPG",response.getOutputStream());
			response.getOutputStream().close();
			
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
			doGet(request,response);
	}
	@Override
	public void init() throws ServletException{
		
	}
}
