package doAn;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class Processing {

	private Connecting connect;
	private URLConnection urlCon;
	private Matcher matcher;

	public Processing(JTextField txtAddressWebsite) {

		connect = new Connecting(txtAddressWebsite);
		urlCon = connect.getInstance().getConnect();
	}

	private String getDateCurrent() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return df.format(date);
	}

	public List<DataExtract> getEmail() throws IOException {

		List<DataExtract> listEmail = new ArrayList<DataExtract>();
		InputStream is = urlCon.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String data = "";
		int i = 0;
		while ((data = br.readLine()) != null) {
			matcher = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(data);
			while (matcher.find()) {
				i++;
				DataExtract email = new DataExtract(i, matcher.group(), getDateCurrent());

				listEmail.add(email);
			}
		}
		return listEmail;
	}

	public void saveData(List<DataExtract> list) {
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				String pathFile = fc.getSelectedFile().getAbsolutePath();
				PrintWriter pw = new PrintWriter(new FileOutputStream(pathFile + ".txt"), true);
				for (DataExtract e : list) {
					pw.println(e.getStt() + ";" + e.getDataExtract() + ";" + e.getDate());
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public List<DataExtract> getPhone() throws IOException {

		List<DataExtract> listPhone = new ArrayList<DataExtract>();
		InputStream is = urlCon.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String data = "";

		int i = 0;
		while ((data = br.readLine()) != null) {
			matcher = Pattern.compile("(\\+84|0)\\d{9,10}").matcher(data);
			while (matcher.find()) {
				i++;
				DataExtract phone = new DataExtract(i, matcher.group(), getDateCurrent());
				listPhone.add(phone);
			}

		}
		return listPhone;
	}

	public String getUrlImage() throws IOException {

		InputStream is = urlCon.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String data = "";
		String phone = "";
		while ((data = br.readLine()) != null) {
			matcher = Pattern.compile("(?m)(?s)<img\\s+(.*)src\\s*=\\s*\"([^\"]+)\"(.*)").matcher(data);
			while (matcher.find()) {
				phone += matcher.group() + "\n";
			}

		}
		return phone;
	}
}
