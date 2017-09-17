package doAn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.print.DocFlavor.STRING;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GUI extends JFrame implements ActionListener {

	private JButton btnOption, btnSave;
	public JTextField txtLink;
	private String[] arrOption = { "Email", "Số điện thoại               " };
	private JComboBox<String> cboOption;
	private JTextArea areaDuLieu;
	private Matcher matcher;

	public GUI() {
		// TODO Auto-generated constructor stub

		JPanel p1 = new JPanel();
		JLabel lblLink = new JLabel("Địa chỉ website:");
		p1.add(lblLink);
		p1.add(txtLink = new JTextField(30));
		p1.setBackground(Color.white);
		add(p1, BorderLayout.NORTH);

		JPanel p2 = new JPanel();
		p2.add(btnOption = new JButton("Truy xuất"));
		p2.add(cboOption = new JComboBox<String>(arrOption));
		p2.setBackground(Color.white);
		p2.add(btnSave = new JButton("Save"));
		add(p2, BorderLayout.CENTER);

		JPanel p3 = new JPanel(new FlowLayout());
		areaDuLieu = new JTextArea(10, 35);
		Border border = BorderFactory.createLineBorder(Color.gray);
		Border borderDuLieu = BorderFactory.createTitledBorder(border, "Dữ liệu truy xuất");
		p3.setBorder(borderDuLieu);
		p3.add(areaDuLieu);
		p3.setBackground(Color.white);
		add(p3, BorderLayout.SOUTH);

		setTitle("Truy xuất dữ liệu website");
		setSize(450, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		btnOption.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();
		Processing pro = new Processing(txtLink);
		if (o.equals(btnOption)) {
			if (cboOption.getSelectedItem() == "Email") {
				System.out.println("Danh sách email của website là:");
				try {
					System.out.println(pro.getEmail());
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if(cboOption.getSelectedItem()=="Số điện thoại               "){
				System.out.println("Danh sách numberPhone của website là:");
				try {
					System.out.println(pro.getPhone());
					System.out.println("::");
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		}

	}

}
