package doAn;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.text.html.parser.Parser;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

public class T {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		/*URL imageURL = new URL("http://congnghe247.net/wp-content/uploads/2017/05/điện-thoại-nào-chụp-hình-đẹp-nhất-hiện-nay-650x300.jpg");
		BufferedImage img = ImageIO.read(imageURL);
		File file = new File("D:\\image\\pickture2.jpg");
		ImageIO.write(img, "jpg", file);*/
		
		File dir = new File("D:\\"+"FOLDER");
		dir.mkdir();
	
	}

}
