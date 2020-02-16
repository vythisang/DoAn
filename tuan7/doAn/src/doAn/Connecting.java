package doAn;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JTextField;

public class Connecting {

	private URL url = null;
	private URLConnection urlCon = null;
	private Connecting connecting = null;
	private JTextField txtAddressWebsite;

	public Connecting(JTextField txtAddressWebsite) {

		this.txtAddressWebsite = txtAddressWebsite;
		try {
			url = new URL(txtAddressWebsite.getText());
			urlCon = url.openConnection();

		} catch (IOException e) {

			System.out.println("Địa chỉ website không hợp lệ");
		}
	}

	public URLConnection getConnect() {
		return urlCon;
	}

	public Connecting getInstance() {
		if (connecting == null) {
			
			connecting = new Connecting(txtAddressWebsite);
		}
		return connecting;
	}
	
	public static void ABC(){
		System.out.println("Test");
		
	}
}
