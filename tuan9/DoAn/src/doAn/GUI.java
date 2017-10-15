package doAn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView.TableRow;

public class GUI extends JFrame implements ActionListener {

	private JButton btnOption, btnSave, btnPrint;
	private JTextField txtLink, txtSearch;
	private String[] arrOption = { "Email", "Số điện thoại", "Ảnh" };
	private DefaultTableModel tableModel;
	private JComboBox<String> cboOption;
	private JTable table;
	private TableRowSorter<TableModel> sorter ;
	private JLabel lblTime,lblInfor;

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
		btnSave.setBounds(20, 270, 90, 30);

		btnPrint = new JButton("In dữ liệu");
		btnPrint.setBounds(20, 320, 90, 30);

		JLabel lblSearch = new JLabel();
		lblSearch.setIcon(
				new ImageIcon(new ImageIcon("search.jpg").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
		lblSearch.setBounds(400, 215, 35, 35);

		
		txtSearch = new JTextField();
		txtSearch.setBounds(440, 220, 400, 30);
		txtSearch.setUI(new HintTextFieldUI("Tìm kiếm", true));
		
		lblTime =  new JLabel();
		lblTime.setBounds(587, 520, 300, 30);

		lblInfor =  new JLabel();
		lblInfor.setBounds(770, 520, 100, 30);
			
		String[] header = { "Số thứ tự", "Dữ liệu" };
		tableModel = new DefaultTableModel(header, 0);
		table = new JTable(tableModel);
		table.getColumnModel().getColumn(1).setPreferredWidth(550);
		table.setAutoCreateRowSorter(true);
			
		
		JScrollPane jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		jsp.setBorder(BorderFactory.createTitledBorder("Dữ liệu được truy xuất"));
		jsp.setBounds(140, 270, 700, 250);
				
		
		p.add(lblLink);
		p.add(txtLink);
		p.add(btnOption);
		p.add(cboOption);
		p.add(btnSave);
		p.add(btnPrint);
		p.add(lblSearch);
		p.add(txtSearch);
		p.add(jsp);
		p.add(lblTime);
		p.add(lblInfor);
		
		setTitle("Truy xuất dữ liệu website");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setResizable(false);

		btnOption.addActionListener(this);
		btnSave.addActionListener(this);
		btnPrint.addActionListener(this);
		txtSearch.addActionListener(this);
		
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Object o = e.getSource();

			Processing pro = new Processing(txtLink);

			if (o.equals(btnOption)) {
				tableModel();
				clearDataTable();
				searchData();
				lblInfor.setText("");
				lblTime.setText("");
				if (cboOption.getSelectedItem() == "Email") {
					alterColumnHeadEmail();
					updateDataTable(pro.getEmail());
					lblTime.setText("Thời gian lấy dữ liệu :      "+getTime());
					

				} else if (cboOption.getSelectedItem() == "Số điện thoại") {
					alterColumnHeadPhone();
					updateDataTable(pro.getPhone());
					lblTime.setText("Thời gian lấy dữ liệu :      "+getTime());
					
					
				} else if (cboOption.getSelectedItem() == "Ảnh") {
					alterColumnHeadImage();
					updateImageOnTable(pro.getImageIcons());
					lblTime.setText("Thời gian lấy dữ liệu :      "+getTime());
				}

			} else if (o.equals(btnSave)) {
				lblTime.setText("");
				if (cboOption.getSelectedItem() == "Email") {
					pro.saveData(pro.getEmail());
					lblInfor.setText("Đã lưu xong");

				} else if (cboOption.getSelectedItem() == "Số điện thoại") {
					pro.saveData(pro.getPhone());
					lblInfor.setText("Đã lưu xong");
					
				}else if(cboOption.getSelectedItem()=="Ảnh"){
					pro.saveDataImages();
					lblInfor.setText("Đã lưu xong");
				}
			}
			else if(o.equals(btnPrint)){
				lblTime.setText("");
				lblInfor.setText("");
				printData();
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Website không tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	private String getTime() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return df.format(date);
	}
	
	private void printData(){
		try{
			MessageFormat header = new MessageFormat("Header page");
			MessageFormat footer = new MessageFormat("Footer page");
			table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void searchData(){
		sorter=new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(sorter);
		txtSearch.getDocument().addDocumentListener(
				new DocumentListener() {
					
					@Override
					public void removeUpdate(DocumentEvent e) {

						newFilter(txtSearch.getText());
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {

						newFilter(txtSearch.getText());
					}
					
					@Override
					public void changedUpdate(DocumentEvent e) {
						newFilter(txtSearch.getText());
					}
					
					public void newFilter(String s){
						if (s.length() == 1) {

							sorter.setRowFilter(null);
						} else {

							sorter.setRowFilter(RowFilter.regexFilter(s));
						}
					}
				});
		
	}
	
	
	
		
	private void tableModel() {
		String[] header = { "Số thứ tự", "Dữ liệu" };
		tableModel = new DefaultTableModel(header, 0);
		table.setModel(tableModel);
		table.getColumnModel().getColumn(1).setPreferredWidth(600);
		table.setRowHeight(50);
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

	private void alterColumnHeadImage() {
		JTableHeader header = table.getTableHeader();
		TableColumnModel colMod = header.getColumnModel();
		TableColumn tabCol = colMod.getColumn(1);
		tabCol.setHeaderValue("Ảnh");
		header.repaint();
	}

	private void clearDataTable() {
		int row = tableModel.getRowCount();
		for (int i = row - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
	}

	private void updateDataTable(List<DataExtract> list) {
		List<DataExtract> listEmail = new ArrayList<>();
		listEmail = list;

		for (DataExtract e : listEmail) {
			String[] rowData = { "           " + e.getStt(), e.getDataExtract() };
			tableModel.addRow(rowData);
		}
		table.setModel(tableModel);

	}

	private void updateImageOnTable(List<Images> listImages) {

		for (Images img : listImages) {
			JLabel lable = new JLabel();
			lable.setIcon(
					new ImageIcon(img.getImageIcon().getImage().getScaledInstance(600, 180, Image.SCALE_DEFAULT)));
			Object[] data = { "          " + img.getStt(), lable };
			tableModel.addRow(data);
		}

		table.setModel(tableModel);
		table.getColumn("Ảnh").setCellRenderer(new lableRender());

	}

	class lableRender implements TableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			TableColumn tc = table.getColumn("Ảnh");
			tc.setMaxWidth(600);
			tc.setMinWidth(600);
			table.setRowHeight(200);

			return (Component) value;
		}

	}

}
