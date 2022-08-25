
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		setBounds(100, 100, 450, 561);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnNewButton = new JButton("Add Product");
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBounds(279, 216, 148, 35);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 310, 416, 188);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNama.setText((String) table.getValueAt(table.getSelectedRow(),1));
				
				
				txtHarga.setText((String) table.getValueAt(table.getSelectedRow(), 2));

				txtKodeBarang.setText((String) table.getValueAt(table.getSelectedRow(), 3));

				txtJumlah.setText((String) table.getValueAt(table.getSelectedRow(), 4));			
			}
		});
		table.setColumnSelectionAllowed(true);
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		
		JButton btnShowData = new JButton("Show Data");
		btnShowData.setForeground(Color.DARK_GRAY);
		btnShowData.setBounds(279, 263, 148, 35);
		contentPane.add(btnShowData);
		
		JLabel lblNewLabel = new JLabel("Nama Product");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(17, 60, 109, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblHarga = new JLabel("Harga");
		lblHarga.setForeground(Color.WHITE);
		lblHarga.setBounds(17, 126, 109, 16);
		contentPane.add(lblHarga);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setForeground(Color.WHITE);
		lblJumlah.setBounds(256, 60, 109, 16);
		contentPane.add(lblJumlah);
		
		JLabel lblKodeProduct = new JLabel("Kode Product");
		lblKodeProduct.setForeground(Color.WHITE);
		lblKodeProduct.setBounds(256, 126, 109, 16);
		contentPane.add(lblKodeProduct);
		
		txtJumlah = new JTextField();
		txtJumlah.setBounds(255, 88, 178, 26);
		contentPane.add(txtJumlah);
		txtJumlah.setColumns(10);
		
		txtHarga = new JTextField();
		txtHarga.setColumns(10);
		txtHarga.setBounds(17, 154, 178, 26);
		contentPane.add(txtHarga);
		
		txtNama = new JTextField();
		txtNama.setColumns(10);
		txtNama.setBounds(17, 88, 178, 26);
		contentPane.add(txtNama);
		
		txtKodeBarang = new JTextField();
		txtKodeBarang.setColumns(10);
		txtKodeBarang.setBounds(256, 154, 178, 26);
		contentPane.add(txtKodeBarang);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(Color.DARK_GRAY);
		btnUpdate.setBounds(17, 219, 148, 35);
		contentPane.add(btnUpdate);
		
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
					Statement stmt= Con.createStatement();
					String sql = String.format("UPDATE product SET nama_product = '%s', harga = '%s', kode_barang = '%s', quantity = '%s' WHERE id = '%s'",txtNama.getText(),txtHarga.getText(),txtKodeBarang.getText(),txtJumlah.getText(),table.getValueAt(table.getSelectedRow(), 0));
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
		btnClear.setBounds(17, 266, 148, 35);
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
		btnGenerate.setBounds(165, 504, 117, 29);
		contentPane.add(btnGenerate);
		
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
					PdfPTable pdfTable = new PdfPTable(5);
					pdfTable.addCell("ID");
					pdfTable.addCell("Nama Produk");
					pdfTable.addCell("Harga");
					pdfTable.addCell("Kode Barang");
					pdfTable.addCell("Jumlah");
					for (int i = 0; i < table.getRowCount(); i++) {
						pdfTable.addCell(table.getValueAt(i,0).toString());
						pdfTable.addCell(table.getValueAt(i,1).toString());
						pdfTable.addCell(table.getValueAt(i,2).toString());
						pdfTable.addCell(table.getValueAt(i,3).toString());
						pdfTable.addCell(table.getValueAt(i,4).toString());
					}
					
		
					doc.add(image1);
					doc.add(pdfTable);
					doc.add(para);
					doc.add(paraAdmin);
					doc.close();
					JOptionPane.showMessageDialog(null,"Data berhasil di Export ke PDF ","Pesan",JOptionPane.INFORMATION_MESSAGE);
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
					String sql = "SELECT * FROM product";
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
				String sql = String.format("INSERT INTO product (nama_product,harga,kode_barang,quantity) VALUES ('%s','%d','%s','%d')",txtNama.getText(),Integer.parseInt(txtHarga.getText()),txtKodeBarang.getText(), Integer.parseInt(txtJumlah.getText()));
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
