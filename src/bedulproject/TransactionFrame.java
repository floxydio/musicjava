
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class TransactionFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblTransaction;
	private JButton btnLoadTransaction;
	private String hargaProduct;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransactionFrame frame = new TransactionFrame();
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
	public TransactionFrame() {
		setTitle("Data Transaksi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 356);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel lblNewLabel_4 = new JLabel("HARI-HARI Music");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblNewLabel_4.setBounds(202, 3, 163, 26);
		contentPane.add(lblNewLabel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 46, 438, 219);
		contentPane.add(scrollPane);
		
		tblTransaction = new JTable();
		tblTransaction.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(tblTransaction);
		
		btnLoadTransaction = new JButton("Load Transaction");
		btnLoadTransaction.setForeground(Color.DARK_GRAY);
		btnLoadTransaction.setBounds(153, 285, 145, 29);
		contentPane.add(btnLoadTransaction);
		
		JButton btnGenerate = new JButton("Generate PDF");
		btnGenerate.setBounds(16, 285, 117, 29);
		contentPane.add(btnGenerate);
		
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
		
		
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					
					Image image1 = Image.getInstance("/Users/macbook/Downloads/kopsurat.png");
					image1.setAlignment(Element.ALIGN_CENTER);
					Paragraph para = new Paragraph("Depok, 26 Agustus 2022");
					String jumlahCount =  String.format("%d", Integer.parseInt(tblTransaction.getValueAt(0,4).toString()) * Integer.parseInt(hargaProduct));
					
					System.out.println(jumlahCount);
					Paragraph paraAdmin = new Paragraph("Admin");
					para.setAlignment(Element.ALIGN_RIGHT);
					para.setSpacingAfter(50);
					para.setSpacingBefore(10);
					paraAdmin.setAlignment(Element.ALIGN_RIGHT);
					Document doc=new Document();
					Paragraph paraText = new Paragraph("HARI-HARI MUSIK");
					paraText.setAlignment(Element.ALIGN_CENTER);
					PdfWriter.getInstance(doc, new FileOutputStream("DataTransaksi.pdf"));
					doc.open();
					PdfPTable pdfTable = new PdfPTable(4);
					pdfTable.addCell("ID Pelanggan");
					pdfTable.addCell("Nama Barang");
					pdfTable.addCell("Jumlah");
					pdfTable.addCell("Tanggal");
					pdfTable.addCell("00"+tblTransaction.getValueAt(0,0).toString());
					pdfTable.addCell(tblTransaction.getValueAt(0,2).toString());
					pdfTable.addCell(jumlahCount);
					pdfTable.addCell(tblTransaction.getValueAt(0,3).toString());
					doc.add(image1);
					doc.add(pdfTable);
					doc.add(para);
					doc.add(paraAdmin);
					doc.close();
//					JOptionPane.showMessageDialog(null,"Data berhasil di Export ke PDF ","Pesan",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("src/Gambar/bukuPesan.png"));
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
		
		btnLoadTransaction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
					Statement stmt= Con.createStatement();
					String sql = "SELECT * FROM product";
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()) {
						hargaProduct = rs.getString("harga");
					}
					
								    
					stmt.close();
					Con.close();
				}catch(Exception e1) {System.out.println(e1);}
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
					Statement stmt= Con.createStatement();
					String sql = "SELECT * FROM `transaction` WHERE id =(SELECT max(id) FROM transaction)";
					ResultSet rs = stmt.executeQuery(sql);
					java.sql.ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) tblTransaction.getModel();
					
					int cols = rsmd.getColumnCount(); 
					String[] colName =  new String[cols];
					
					for (int i = 0; i <cols;i++) {
						colName[i] = rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colName);
						String id,namaCustomer,namaProduct,quantity,tanggal;
						String total;
					
						
						while(rs.next()) {
							id = rs.getString(1);
							namaCustomer = rs.getString(2);
							namaProduct = rs.getString(3);
							quantity = rs.getString(4);
							tanggal = rs.getString(5);
							total = String.format("%d", Integer.parseInt(rs.getString(5)) * Integer.parseInt(hargaProduct));
//							System.out.println(rs.getString(5));
							String[] row = {id,namaCustomer,namaProduct,quantity,tanggal,total};
							model.addRow(row);
						}
					}			    
					stmt.close();
					Con.close();
				}catch(Exception e1) {System.out.println(e1);}
				
			}
		});

	}

	protected Object getObject(JTable tblTransaction2, int a, int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
