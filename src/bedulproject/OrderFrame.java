
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;

public class OrderFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNamaCustomer;
	private JTextField txtQuantity;
	private JTextField txtUang;
	String harga;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrame frame = new OrderFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderFrame() {
		setTitle("Order Transaksi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 411, 505);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel lblNewLabel_4 = new JLabel("HARI-HARI Musik");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(202, 3, 163, 26);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("Nama");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(52, 38, 61, 16);
		contentPane.add(lblNewLabel);
		
		txtNamaCustomer = new JTextField();
		txtNamaCustomer.setBounds(52, 66, 244, 26);
		contentPane.add(txtNamaCustomer);
		txtNamaCustomer.setColumns(10);
		
		JLabel lblJumlah = new JLabel("Jumlah Barang");
		lblJumlah.setForeground(Color.WHITE);
		lblJumlah.setBounds(52, 284, 96, 16);
		contentPane.add(lblJumlah);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(52, 312, 244, 26);
		contentPane.add(txtQuantity);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setBounds(52, 125, 244, 27);
		contentPane.add(comboBox);
		
		
		JLabel lblNewLabel_1 = new JLabel("Product");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(52, 104, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		JButton btnTriggerComboBox = new JButton("Show Data");
		btnTriggerComboBox.setBackground(Color.DARK_GRAY);
		btnTriggerComboBox.setForeground(Color.DARK_GRAY);
		btnTriggerComboBox.setBounds(293, 123, 117, 29);
		contentPane.add(btnTriggerComboBox);
		
		JButton btnOrder = new JButton("Order Sekarang");
		btnOrder.setForeground(Color.DARK_GRAY);
		btnOrder.setBounds(100, 416, 232, 44);
		contentPane.add(btnOrder);
		
		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setForeground(Color.WHITE);
		lblTanggal.setBounds(6, 141, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		JDateChooser txtDate = new JDateChooser();
		txtDate.setBounds(52, 237, 244, 26);
		contentPane.add(txtDate);
		
		JLabel lblNewLabel_2 = new JLabel("Tanggal");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(52, 210, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		JButton btnBackToMenu = new JButton("Back To Menu");
		btnBackToMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnBackToMenu.setBounds(6, 6, 96, 29);
		contentPane.add(btnBackToMenu);
		
		JLabel lblNewLabel_1_1 = new JLabel("Merek");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setBounds(52, 148, 61, 16);
		contentPane.add(lblNewLabel_1_1);
		
		JComboBox comboMerek = new JComboBox();
		comboMerek.setToolTipText("");
		comboMerek.setForeground(Color.DARK_GRAY);
		comboMerek.setBounds(52, 170, 244, 27);
		contentPane.add(comboMerek);
		
		JButton btnTriggerComboBox_1 = new JButton("Show Data");
		btnTriggerComboBox_1.setForeground(Color.DARK_GRAY);
		btnTriggerComboBox_1.setBackground(Color.DARK_GRAY);
		btnTriggerComboBox_1.setBounds(293, 167, 117, 29);
		contentPane.add(btnTriggerComboBox_1);
		
		JLabel lblJumlahUang = new JLabel("Jumlah Uang");
		lblJumlahUang.setForeground(Color.WHITE);
		lblJumlahUang.setBounds(52, 350, 96, 16);
		contentPane.add(lblJumlahUang);
		
		txtUang = new JTextField();
		txtUang.setColumns(10);
		txtUang.setBounds(52, 378, 244, 26);
		contentPane.add(txtUang);
			
		
		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame frame = new MenuFrame();
				frame.setVisible(true);
				setVisible(false);
			}
		});	
		
	btnTriggerComboBox_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
			Statement stmt= Con.createStatement();
			String sql = "SELECT * FROM Merek";
			ResultSet rs = stmt.executeQuery(sql);
			
			
				while(rs.next()) {
					comboMerek.addItem(rs.getString("Nama"));
					
				
				}
						    
			stmt.close();
			Con.close();
			btnTriggerComboBox_1.setVisible(false);
		}catch(Exception e1) {System.out.println(e1);}
		}
	});	
		
	btnTriggerComboBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
					Statement stmt= Con.createStatement();
					String sql = "SELECT * FROM product";
					ResultSet rs = stmt.executeQuery(sql);
					
					
						while(rs.next()) {
							comboBox.addItem(rs.getString("nama_product"));

							harga = rs.getString("harga");
						
						}
								    
					stmt.close();
					Con.close();
					btnTriggerComboBox.setVisible(false);
				}catch(Exception e1) {System.out.println(e1);}
			
			}
		});
	;
	btnOrder.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
		
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
				Statement stmt= Con.createStatement();
				String sql = String.format("INSERT INTO transaction (nama_customer,nama_product,tanggal,quantity,merek) VALUES ('%s','%s','%s','%d','%s')",txtNamaCustomer.getText(),comboBox.getSelectedItem(),txtDate.getDate(),Integer.parseInt(txtQuantity.getText()),comboMerek.getSelectedItem());
				System.out.println(sql);
									stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(null,"Sucessfully Order");				
				stmt.close();
				Con.close();
				
				try {
					Calendar cal = Calendar.getInstance();

					int month = cal.get(Calendar.MONTH) + 1;
					int day = cal.get(Calendar.DAY_OF_MONTH);
					int year = cal.get(Calendar.YEAR);
					
					String monthText = month == 8 ? "Agustus" : "September";
					
					
					Image image1 = Image.getInstance("/Users/macbook/Downloads/kopsurat.png");
					image1.setAlignment(Element.ALIGN_CENTER);
					Paragraph para = new Paragraph(String.format("Depok,%d %s %d", day,monthText, year));
					
					Paragraph paraAdmin = new Paragraph("Admin");
					int jumlahTotal = Integer.parseInt(txtQuantity.getText()) * Integer.parseInt(harga);
					para.setAlignment(Element.ALIGN_RIGHT);
					para.setSpacingAfter(50);
					para.setSpacingBefore(10);
					paraAdmin.setAlignment(Element.ALIGN_RIGHT);
					Document doc=new Document();
					Paragraph paraText = new Paragraph("HARI-HARI MUSIK");
					paraText.setAlignment(Element.ALIGN_CENTER);
					PdfWriter.getInstance(doc, new FileOutputStream("DataOrders.pdf"));
					doc.open();
					PdfPTable pdfTable = new PdfPTable(6);
					pdfTable.addCell("Nama Customer");
					pdfTable.addCell("Product");
					pdfTable.addCell("Tanggal");
					pdfTable.addCell("Jumlah Barang");
					pdfTable.addCell("Merek");
					pdfTable.addCell("Total");
					pdfTable.addCell(txtNamaCustomer.getText());
					pdfTable.addCell(comboBox.getSelectedItem().toString());
					pdfTable.addCell(txtDate.getDate().toString());
					pdfTable.addCell(txtQuantity.getText());
					pdfTable.addCell(comboMerek.getSelectedItem().toString());
					pdfTable.addCell(String.format("%d", jumlahTotal));
					doc.add(image1);
					doc.add(pdfTable);
					doc.add(para);
					doc.add(paraAdmin);
					doc.close();
					System.out.println("SUCCESS CREATE");

					Desktop.getDesktop().open(new File("DataOrders.pdf"));

					txtNamaCustomer.setText("");
					comboBox.setSelectedIndex(0);
					txtQuantity.setText("");
					
				} catch(Exception ex) {
					System.out.println(ex);
				}
				
				
			}catch(Exception e1) {System.out.println(e1);}
		
		}
	});

	
	}
}
