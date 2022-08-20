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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Customer");
		btnNewButton.setBounds(28, 114, 117, 29);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			    CustomerFrame field  = new CustomerFrame();
			    field.setVisible(true);
			    setVisible(false);
				
				
			}
		});
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(237, 114, 117, 29);
		contentPane.add(btnOrder);
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderFrame frame = new OrderFrame();
				
				frame.setVisible(true);;
				setVisible(false);
				
				
				
			}
		});
		
		JButton btnStock = new JButton("Check Stok");
		btnStock.setBounds(28, 160, 117, 29);
		contentPane.add(btnStock);
		btnStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ProductFrame frame = new ProductFrame();
				frame.setVisible(true);
				setVisible(false);
				
				
			}
		});
		
		JButton btnTransaction = new JButton("Transcation");
		btnTransaction.setBounds(237, 160, 117, 29);
		contentPane.add(btnTransaction);
		btnTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionFrame frame = new TransactionFrame();
				frame.setVisible(true);
				setVisible(false);
				
				
				
			}
		});
	}
}
