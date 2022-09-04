

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class MenuFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame frame = new MenuFrame();
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
	public MenuFrame() {
		setResizable(false);
		setTitle("Choose Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 378);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Customer");
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBounds(93, 75, 244, 47);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    CustomerFrame field  = new CustomerFrame();
			    field.setVisible(true);
				
				
			}
		});
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setForeground(Color.DARK_GRAY);
		btnOrder.setBounds(93, 135, 244, 47);
		contentPane.add(btnOrder);
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderFrame frame = new OrderFrame();
				
				frame.setVisible(true);;
				setVisible(false);
				
				
				
			}
		});

		
		JButton btnStock = new JButton("Transaksi Barang");
		btnStock.setForeground(Color.DARK_GRAY);
		btnStock.setBounds(93, 194, 244, 47);
		contentPane.add(btnStock);
		btnStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ProductFrame frame = new ProductFrame();
				frame.setVisible(true);
				setVisible(false);
				
				
			}
		});
		
		JButton btnTransaction = new JButton("Transcation");
		btnTransaction.setForeground(Color.DARK_GRAY);
		btnTransaction.setBounds(92, 261, 244, 47);
		contentPane.add(btnTransaction);
		
		JLabel lblNewLabel = new JLabel("Pilih Menu");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(150, 25, 125, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("/Users/macbook/eclipse-workspace/bedulproject/src/acoustic-guitar-pngrepo-com.png"));
		lblNewLabel_1.setBounds(254, 152, 341, 219);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("HARI-HARI Musik");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBackground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(19, 320, 165, 16);
		contentPane.add(lblNewLabel_2);
		btnTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionFrame frame = new TransactionFrame();
				frame.setVisible(true);
				setVisible(false);
				
				
				
			}
		});
	}
}
