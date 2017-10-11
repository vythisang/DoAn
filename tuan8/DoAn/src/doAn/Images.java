package doAn;

import javax.swing.ImageIcon;

public class Images {

	private int stt;
	private ImageIcon imageIcon;
	private String date;
	
	
	
	public Images(int stt, ImageIcon imageIcon, String date) {
		super();
		this.stt = stt;
		this.imageIcon = imageIcon;
		this.date = date;
	}



	public int getStt() {
		return stt;
	}



	public void setStt(int stt) {
		this.stt = stt;
	}



	public ImageIcon getImageIcon() {
		return imageIcon;
	}



	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public Images() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
