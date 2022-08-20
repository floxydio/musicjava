package bedulproject;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class OrderFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNamaCustomer;
	private JTextField txtQuantity;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nama");
		lblNewLabel.setBounds(52, 38, 61, 16);
		contentPane.add(lblNewLabel);
		
		txtNamaCustomer = new JTextField();
		txtNamaCustomer.setBounds(52, 66, 244, 26);
		contentPane.add(txtNamaCustomer);
		txtNamaCustomer.setColumns(10);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setBounds(52, 162, 61, 16);
		contentPane.add(lblJumlah);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(52, 190, 244, 26);
		contentPane.add(txtQuantity);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(52, 124, 244, 27);
		contentPane.add(comboBox);
		
		
		JLabel lblNewLabel_1 = new JLabel("Product");
		lblNewLabel_1.setBounds(52, 104, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		JButton btnTriggerComboBox = new JButton("Show Data");
		btnTriggerComboBox.setBounds(293, 123, 117, 29);
		contentPane.add(btnTriggerComboBox);
		
		JButton btnOrder = new JButton("Order Sekarang");
		btnOrder.setBounds(159, 228, 151, 29);
		contentPane.add(btnOrder);
		
		
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
				String sql = String.format("INSERT INTO transaction (nama_customer,nama_product,quantity) VALUES ('%s','%s','%d')",txtNamaCustomer.getText(),comboBox.getSelectedItem(),Integer.parseInt(txtQuantity.getText()));
				System.out.println(sql);
									stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(null,"Sucessfully Order");
				txtNamaCustomer.setText("");
				comboBox.setSelectedIndex(0);
				txtQuantity.setText("");
				
				stmt.close();
				Con.close();
			}catch(Exception e1) {System.out.println(e1);}
		
		}
	});
}
	
}