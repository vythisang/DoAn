package doAn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.print.DocFlavor.STRING;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class GUI extends JFrame implements ActionListener {

	private JButton btnOption, btnSave;
	public JTextField txtLink;
	private String[] arrOption = { "Email", "Số điện thoại", "Ảnh" };
	private DefaultTableModel tableModel;
	private JComboBox<String> cboOption;
	private JTable table;
	private Matcher matcher;

	public GUI() {

		JPanel p = new JPanel();
		p.setLayout(null);
		add(p);

		JLabel lblImage = new JLabel();
		lblImage.setIcon(new ImageIcon(
				new ImageIcon("imageTitle.jpg").getImage().getScaledInstance(900, 150, Image.SCALE_DEFAULT)));
		lblImage.setBounds(0, 0, 900, 150);
		p.add(lblImage);

		JLabel lblLink = new JLabel("Địa chỉ website:");
		lblLink.setBounds(20, 160, 100, 50);

		txtLink = new JTextField();
		txtLink.setBounds(140, 170, 700, 30);

		btnOption = new JButton("Truy xuất");
		btnOption.setBounds(20, 220, 90, 30);

		cboOption = new JComboBox<>(arrOption);
		cboOption.setBounds(140, 220, 200, 30);

		btnSave = new JButton("Lưu");
		btnSave.setBounds(370, 220, 90, 30);

		String[] heards = { "Số thứ tự", "Địa chỉ Email", "Ngày lấy dữ liệu" };
		tableModel = new DefaultTableModel(heards, 0);
		table = new JTable(tableModel);
		JScrollPane jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBorder(BorderFactory.createTitledBorder("Dữ liệu được truy xuất"));
		jsp.setBounds(140, 270, 700, 250);

		p.add(lblLink);
		p.add(txtLink);
		p.add(btnOption);
		p.add(cboOption);
		p.add(btnSave);
		p.add(jsp);

		setTitle("Truy xuất dữ liệu website");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		btnOption.addActionListener(this);
		btnSave.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Object o = e.getSource();
			Processing pro = new Processing(txtLink);
			if (o.equals(btnOption)) {
				clearDataTable();
				alterColumnHeadEmail();
				if (cboOption.getSelectedItem() == "Email") {

					updateDateTable(pro.getEmail());

				} else if (cboOption.getSelectedItem() == "Số điện thoại") {
					alterColumnHeadPhone();

					updateDateTable(pro.getPhone());

				} else if (cboOption.getSelectedItem() == "Ảnh") {
					updateImagesOnTable(pro.getImageIcons());
					
					
					

				}

			} else if (o.equals(btnSave)) {
				if (cboOption.getSelectedItem() == "Email") {
					pro.saveData(pro.getEmail());
				
				}else if(cboOption.getSelectedItem() == "Số điện thoại"){
					pro.saveData(pro.getPhone());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void alterColumnHeadPhone() {
		JTableHeader header = table.getTableHeader();
		TableColumnModel colMod = header.getColumnModel();
		TableColumn tabCol = colMod.getColumn(1);
		tabCol.setHeaderValue("Số điện thoại");
		header.repaint();
	}

	private void alterColumnHeadEmail() {
		JTableHeader header = table.getTableHeader();
		TableColumnModel colMod = header.getColumnModel();
		TableColumn tabCol = colMod.getColumn(1);
		tabCol.setHeaderValue("Địa chỉ Email");
		header.repaint();
	}

	private void clearDataTable() {
		int row = tableModel.getRowCount();
		for (int i = row - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
	}

	private void updateDateTable(List<DataExtract> list) {
		List<DataExtract> listEmail = new ArrayList<>();
		listEmail = list;

		for (DataExtract e : listEmail) {

			String[] rowData = { "                                 " + e.getStt(), e.getDataExtract(),
					"                " + e.getDate() };
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);

	}
	
	private void updateImagesOnTable(List<Images> listImages){
		
		for(Images images:listImages){
			String[] rowData = { "                                 " + images.getStt(), images.getImageIcon()+"090",
					"                " + images.getDate() };
			tableModel.addRow(rowData);
		}
		
		table.setModel(tableModel);
		
		
	}
	

}
