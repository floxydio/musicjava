
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

import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

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
		setTitle("Order Transaksi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 382);
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
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setForeground(Color.WHITE);
		lblJumlah.setBounds(52, 215, 61, 16);
		contentPane.add(lblJumlah);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(52, 243, 244, 26);
		contentPane.add(txtQuantity);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setForeground(Color.DARK_GRAY);
		comboBox.setBounds(52, 124, 244, 27);
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
		btnOrder.setBounds(99, 294, 232, 44);
		contentPane.add(btnOrder);
		
		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setForeground(Color.WHITE);
		lblTanggal.setBounds(6, 141, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		JDateChooser txtDate = new JDateChooser();
		txtDate.setBounds(52, 181, 244, 26);
		contentPane.add(txtDate);
		
		JLabel lblNewLabel_2 = new JLabel("Tanggal");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(52, 153, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		JButton btnBackToMenu = new JButton("Back To Menu");
		btnBackToMenu.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnBackToMenu.setBounds(6, 6, 96, 29);
		contentPane.add(btnBackToMenu);
		
		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame frame = new MenuFrame();
				frame.setVisible(true);
				setVisible(false);
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
				String sql = String.format("INSERT INTO transaction (nama_customer,nama_product,tanggal,quantity) VALUES ('%s','%s','%s','%d')",txtNamaCustomer.getText(),comboBox.getSelectedItem(),txtDate.getDate(),Integer.parseInt(txtQuantity.getText()));
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
