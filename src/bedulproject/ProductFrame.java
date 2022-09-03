
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import java.awt.Font;

public class ProductFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtJumlah;
	private JTextField txtHarga;
	private JTextField txtNama;
	private JTextField txtKodeBarang;
	private JTextField txtCustomer;
	private JTextField txtMerek;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductFrame frame = new ProductFrame();
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
	public ProductFrame() {
		setResizable(false);
		setTitle("Product ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 620);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnNewButton = new JButton("Pesan");
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBounds(408, 273, 148, 35);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 364, 529, 188);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNama.setText((String) table.getValueAt(table.getSelectedRow(),1));
				txtCustomer.setText((String) table.getValueAt(table.getSelectedRow(), 2));

				txtJumlah.setText((String) table.getValueAt(table.getSelectedRow(), 3));
				txtMerek.setText((String) table.getValueAt(table.getSelectedRow(), 4));

	
			}
		});
		table.setColumnSelectionAllowed(true);
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		
		JButton btnShowData = new JButton("Show Data");
		btnShowData.setForeground(Color.DARK_GRAY);
		btnShowData.setBounds(408, 320, 148, 35);
		contentPane.add(btnShowData);
		
		JLabel lblNewLabel = new JLabel("Nama Product");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(17, 60, 109, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblHarga = new JLabel("Harga");
		lblHarga.setForeground(Color.WHITE);
		lblHarga.setBounds(17, 196, 109, 16);
		contentPane.add(lblHarga);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setForeground(Color.WHITE);
		lblJumlah.setBounds(336, 60, 109, 16);
		contentPane.add(lblJumlah);
		
		JLabel lblKodeProduct = new JLabel("Kode Product");
		lblKodeProduct.setForeground(Color.WHITE);
		lblKodeProduct.setBounds(336, 130, 109, 16);
		contentPane.add(lblKodeProduct);
		
		txtJumlah = new JTextField();
		txtJumlah.setBounds(336, 88, 178, 26);
		contentPane.add(txtJumlah);
		txtJumlah.setColumns(10);
		
		txtHarga = new JTextField();
		txtHarga.setColumns(10);
		txtHarga.setBounds(17, 224, 178, 26);
		contentPane.add(txtHarga);
		
		txtNama = new JTextField();
		txtNama.setColumns(10);
		txtNama.setBounds(17, 88, 178, 26);
		contentPane.add(txtNama);
		
		txtKodeBarang = new JTextField();
		txtKodeBarang.setColumns(10);
		txtKodeBarang.setBounds(336, 158, 178, 26);
		contentPane.add(txtKodeBarang);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.DARK_GRAY);
		btnUpdate.setBounds(17, 273, 148, 35);
		contentPane.add(btnUpdate);
		
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
					Statement stmt= Con.createStatement();
					String sql = String.format("UPDATE pesanan SET nama_product = '%s',nama_customer = '%s', quantity = '%s',merek = '%s' WHERE id = '%s'",txtNama.getText(),txtCustomer.getText(),txtJumlah.getText(),txtMerek.getText(),table.getValueAt(table.getSelectedRow(), 0));
					System.out.println(sql);
				stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null,"Sucessfully Update");
					table.setModel(new DefaultTableModel());
					txtNama.setText("");
					txtHarga.setText("");
					txtKodeBarang.setText("");;
					txtJumlah.setText("");
					
					stmt.close();
					Con.close();
				}catch(Exception e1) {System.out.println(e1);}
				
			}
		});
		
		
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(Color.DARK_GRAY);
		btnClear.setBounds(17, 320, 148, 35);
		contentPane.add(btnClear);
		
		JButton btnBackMenu = new JButton("Back To Menu");
		btnBackMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnBackMenu.setBounds(6, 6, 91, 29);
		contentPane.add(btnBackMenu);
		
		btnBackMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame frame = new MenuFrame();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		

		JLabel lblNewLabel_4 = new JLabel("HARI-HARI Music");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(202, 3, 163, 26);
		contentPane.add(lblNewLabel_4);
		
		JButton btnGenerate = new JButton("Generate PDF");
		btnGenerate.setForeground(Color.BLACK);
		btnGenerate.setBackground(Color.BLACK);
		btnGenerate.setBounds(226, 556, 117, 36);
		contentPane.add(btnGenerate);
		
		txtCustomer = new JTextField();
		txtCustomer.setColumns(10);
		txtCustomer.setBounds(17, 158, 178, 26);
		contentPane.add(txtCustomer);
		
		JLabel lblNewLabel_1 = new JLabel("Nama Customer");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(17, 130, 117, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblMerek = new JLabel("Merek");
		lblMerek.setForeground(Color.WHITE);
		lblMerek.setBounds(336, 196, 109, 16);
		contentPane.add(lblMerek);
		
		txtMerek = new JTextField();
		txtMerek.setColumns(10);
		txtMerek.setBounds(336, 224, 178, 26);
		contentPane.add(txtMerek);
		
		JButton btrnGenerateCustomer = new JButton("Generate Customer");
		btrnGenerateCustomer.setBounds(214, 276, 148, 35);
		contentPane.add(btrnGenerateCustomer);
		
		
		btrnGenerateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					
					Image image1 = Image.getInstance("/Users/macbook/Downloads/kopsurat.png");
					image1.setAlignment(Element.ALIGN_CENTER);
					Paragraph para = new Paragraph("Depok, 26 Agustus 2022");
					
					Paragraph paraAdmin = new Paragraph("Admin");
					para.setAlignment(Element.ALIGN_RIGHT);
					para.setSpacingAfter(50);
					para.setSpacingBefore(10);
					paraAdmin.setAlignment(Element.ALIGN_RIGHT);
					Document doc=new Document();
					Paragraph paraText = new Paragraph("HARI-HARI MUSIK");
					paraText.setAlignment(Element.ALIGN_CENTER);
					PdfWriter.getInstance(doc, new FileOutputStream("DataCustomerRequest.pdf"));
					doc.open();
					PdfPTable pdfTable = new PdfPTable(5);
					pdfTable.addCell("Nama Barang");
					pdfTable.addCell("Nama Customer");
					pdfTable.addCell("Jumlah");
					pdfTable.addCell("Merek");
					pdfTable.addCell("Status");
						pdfTable.addCell(txtNama.getText());
						pdfTable.addCell(txtCustomer.getText());
						pdfTable.addCell(txtJumlah.getText());
						pdfTable.addCell(txtMerek.getText());
						pdfTable.addCell("Menunggu");
					
				
					doc.add(image1);
					doc.add(pdfTable);
					doc.add(para);
					doc.add(paraAdmin);
					doc.close();
					
					Desktop.getDesktop().open(new File("DataCustomerRequest.pdf"));

//					JOptionPane.showMessageDialog(null,"Data berhasil di Export ke PDF ","Pesan",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/Gambar/bukuPesan.png"));
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
			
		});
		
		JButton btnGenerateGudang = new JButton("Generate Gudang");
		btnGenerateGudang.setBounds(214, 320, 148, 35);
		contentPane.add(btnGenerateGudang);
		
		
		btnGenerateGudang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					
					Image image1 = Image.getInstance("/Users/macbook/Downloads/kopsurat.png");
					image1.setAlignment(Element.ALIGN_CENTER);
					Paragraph para = new Paragraph("Depok, 26 Agustus 2022");
					
					Paragraph paraAdmin = new Paragraph("Admin");
					para.setAlignment(Element.ALIGN_RIGHT);
					para.setSpacingAfter(50);
					para.setSpacingBefore(10);
					paraAdmin.setAlignment(Element.ALIGN_RIGHT);
					Document doc=new Document();
					Paragraph paraText = new Paragraph("HARI-HARI MUSIK");
					paraText.setAlignment(Element.ALIGN_CENTER);
					PdfWriter.getInstance(doc, new FileOutputStream("DataGudangRequest.pdf"));
					doc.open();
					PdfPTable pdfTable = new PdfPTable(5);
					pdfTable.addCell("Nama Barang");
					pdfTable.addCell("Nama Customer");
					pdfTable.addCell("Jumlah");
					pdfTable.addCell("Merek");
					pdfTable.addCell("Status");
						pdfTable.addCell(txtNama.getText());
						pdfTable.addCell(txtCustomer.getText());
						pdfTable.addCell(txtJumlah.getText());
						pdfTable.addCell(txtMerek.getText());
						pdfTable.addCell("Diterima");
					
				
					doc.add(image1);
					doc.add(pdfTable);
					doc.add(para);
					doc.add(paraAdmin);
					doc.close();
					
					Desktop.getDesktop().open(new File("DataGudangRequest.pdf"));

//					JOptionPane.showMessageDialog(null,"Data berhasil di Export ke PDF ","Pesan",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/Gambar/bukuPesan.png"));
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
			
		});
		
		btnClear.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				txtNama.setText("");
				txtHarga.setText("");
				txtKodeBarang.setText("");
				txtJumlah.setText("");
			}
			
		});
		
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					
					Image image1 = Image.getInstance("/Users/macbook/eclipse-workspace/musicjava/src/kopsurat.png");
					image1.setAlignment(Element.ALIGN_CENTER);
					Paragraph para = new Paragraph("Depok, 26 Agustus 2022");
//					String jumlahCount =  String.format("%d", Integer.parseInt(tblTransaction.getValueAt(0,4).toString()) * Integer.parseInt(hargaProduct));
					
//					System.out.println(jumlahCount);
					Paragraph paraAdmin = new Paragraph("Admin");
					para.setAlignment(Element.ALIGN_RIGHT);
					para.setSpacingAfter(50);
					para.setSpacingBefore(10);
					paraAdmin.setAlignment(Element.ALIGN_RIGHT);
					Document doc=new Document();
					Paragraph paraText = new Paragraph("HARI-HARI MUSIK");
					paraText.setAlignment(Element.ALIGN_CENTER);
					PdfWriter.getInstance(doc, new FileOutputStream("DataProduk.pdf"));
					doc.open();
					PdfPTable pdfTable = new PdfPTable(6);
					pdfTable.addCell("ID");
					pdfTable.addCell("Nama Produk");
					pdfTable.addCell("Harga");
					pdfTable.addCell("Kode Barang");
					pdfTable.addCell("Jumlah");
					pdfTable.addCell("Status");
					for (int i = 0; i < table.getRowCount(); i++) {
						pdfTable.addCell(table.getValueAt(i,0).toString());
						pdfTable.addCell(table.getValueAt(i,1).toString());
						pdfTable.addCell(table.getValueAt(i,2).toString());
						pdfTable.addCell(table.getValueAt(i,3).toString());
						pdfTable.addCell(table.getValueAt(i,4).toString());
					}
					pdfTable.addCell("Diterima");
					
		
					doc.add(image1);
					doc.add(pdfTable);
					doc.add(para);
					doc.add(paraAdmin);
					doc.close();
					JOptionPane.showMessageDialog(null,"Data berhasil di Export ke PDF ","Pesan",JOptionPane.INFORMATION_MESSAGE);
					Desktop.getDesktop().open(new File("DataProduk.pdf"));

				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
			
		});
		
		btnShowData.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
					Statement stmt= Con.createStatement();
					String sql = "SELECT * FROM pesanan";
					ResultSet rs = stmt.executeQuery(sql);
					java.sql.ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					
					int cols = rsmd.getColumnCount();
					String[] colName =  new String[cols];
					
					for (int i = 0; i <cols;i++) {
						colName[i] = rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colName);
						String id,namaProduct,harga,kodeBarang,quantity;
						
						while(rs.next()) {
							id = rs.getString(1);
							namaProduct = rs.getString(2);
							harga = rs.getString(3);
							kodeBarang = rs.getString(4);
							quantity = rs.getString(5);
							String[] row = {id,namaProduct,harga,kodeBarang,quantity};
							model.addRow(row);
						}
					}			    
					stmt.close();
					Con.close();
				}catch(Exception e1) {System.out.println(e1);}
				
			}
			
		});

		
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
					Statement stmt= Con.createStatement();
				String sql = String.format("INSERT INTO pesanan (nama_product,nama_customer,quantity, merek) VALUES ('%s','%s','%d','%s')",txtNama.getText(),txtCustomer.getText(), Integer.parseInt(txtJumlah.getText()),txtMerek.getText());
					System.out.println(sql);
										stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null,"Sucessfully Add");
					
					stmt.close();
					Con.close();
				}catch(Exception e1) {System.out.println(e1);}
				
			}
			
			
		});
		
	}
}
