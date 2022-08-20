package bedulproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddProduct extends JFrame {

	private JPanel contentPane;
	private JTextField txtNama;
	private JTextField txtHarga;
	private JTextField txtKodeBarang;
	private JTextField txtJumlah;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProduct frame = new AddProduct();
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
	public AddProduct() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nama Product");
		lblNewLabel.setBounds(10, 37, 275, 16);
		contentPane.add(lblNewLabel);
		
		txtNama = new JTextField();
		txtNama.setBounds(10, 65, 185, 26);
		contentPane.add(txtNama);
		txtNama.setColumns(10);
		
		JLabel lblHarga = new JLabel("Harga");
		lblHarga.setBounds(233, 37, 81, 16);
		contentPane.add(lblHarga);
		
		txtHarga = new JTextField();
		txtHarga.setColumns(10);
		txtHarga.setBounds(233, 65, 185, 26);
		contentPane.add(txtHarga);
		
		JLabel lblKodeProduct = new JLabel("Kode Product");
		lblKodeProduct.setBounds(233, 103, 275, 16);
		contentPane.add(lblKodeProduct);
		
		txtKodeBarang = new JTextField();
		txtKodeBarang.setColumns(10);
		txtKodeBarang.setBounds(233, 125, 192, 26);
		contentPane.add(txtKodeBarang);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setBounds(10, 103, 122, 16);
		contentPane.add(lblJumlah);
		
		txtJumlah = new JTextField();
		txtJumlah.setColumns(10);
		txtJumlah.setBounds(10, 125, 185, 26);
		contentPane.add(txtJumlah);
		
		JButton btnNewButton = new JButton("Save Product");
		btnNewButton.setBounds(150, 193, 117, 29);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
					Statement stmt= Con.createStatement();
				String sql = String.format("INSERT INTO product (nama_product,harga,kode_barang,quantity) VALUES ('%s','%d','%s','%d')",txtNama.getText(),Integer.parseInt(txtHarga.getText()),txtKodeBarang.getText(), Integer.parseInt(txtJumlah.getText()));
					System.out.println(sql);
										stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null,"Sucessfully Add");
					ProductFrame frame = new ProductFrame();
					
					frame.setVisible(true);
					setVisible(false);
					
					stmt.close();
					Con.close();
				}catch(Exception e1) {System.out.println(e1);}
			}
		});
}}
