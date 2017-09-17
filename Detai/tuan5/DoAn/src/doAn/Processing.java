package doAn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

public class Processing {

	private Connecting connect;
	private URLConnection urlCon;
	private Matcher matcher;

	public Processing(JTextField txtAddressWebsite) {

		connect = new Connecting(txtAddressWebsite);
		urlCon = connect.getInstance().getConnect();
	}

	public String getEmail() throws IOException {

		InputStream is = urlCon.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String data = "";
		String email = "";
		while ((data = br.readLine()) != null) {
			matcher = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(data);
			while (matcher.find()) {
				email += matcher.group() + "\n";
			}

		}
		return email;
	}
	
	public String getPhone() throws IOException {

		InputStream is = urlCon.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String data = "";
		String phone = "";
		while ((data = br.readLine()) != null) {
			matcher = Pattern.compile("(\\+84|0)\\d{9,10}").matcher(data);
			while (matcher.find()) {
				phone += matcher.group() + "\n";
			}

		}
		return phone;
	}

}
