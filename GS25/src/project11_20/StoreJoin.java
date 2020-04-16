package project11_20;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StoreJoin extends JFrame {

	private JPanel contentPane;
	private JTextField tfLocation;
	private JTextField textField_2;
	private JTextField textField_3;
	
	private Image img ;
	private JLabel lbllogo;
	private JPasswordField tfPwd;
	private StoreLogin sl;
	private Map<String, String> map2;
	private JButton btnSave;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StoreJoin frame = new StoreJoin();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public StoreJoin(StoreLogin sl) {
		this();
		this.sl=sl;
		
	}

	/**
	 * Create the frame.
	 */
	public StoreJoin() {
		setTitle("지점 등록");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 403, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLocation = new JLabel("지점명 ");
		lblLocation.setBounds(12, 101, 57, 15);
		contentPane.add(lblLocation);
		
		tfLocation = new JTextField();
		tfLocation.setBounds(99, 98, 116, 21);
		contentPane.add(tfLocation);
		tfLocation.setColumns(10);
		
		JLabel lblpassword = new JLabel("비밀 번호");
		lblpassword.setBounds(12, 157, 57, 15);
		contentPane.add(lblpassword);
		
		JLabel lblNewLabel_2 = new JLabel("*비밀번호는 '1234'로 통일");
		lblNewLabel_2.setBounds(225, 157, 157, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblPhone = new JLabel("연락처");
		lblPhone.setBounds(12, 211, 57, 15);
		contentPane.add(lblPhone);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"010", "011", "019", "031", "041", "02", "043"}));
		comboBox.setBounds(60, 208, 59, 21);
		contentPane.add(comboBox);
		
		textField_2 = new JTextField();
		textField_2.setBounds(135, 208, 89, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(248, 208, 89, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("-");
		lblNewLabel_3.setBounds(124, 211, 23, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel label_1 = new JLabel("-");
		label_1.setBounds(231, 211, 15, 15);
		contentPane.add(label_1);
	
		map2=new HashMap<>();
		
		btnSave = new JButton("등록");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id=tfLocation.getText();
				String pw=String.valueOf(tfPwd.getPassword());
				String strId=map2.get(id);
				map2.put(strId,pw);
				}
		});
		btnSave.setBounds(48, 264, 97, 23);
		contentPane.add(btnSave);
		
		JButton btnNewButton_1 = new JButton("종료");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(204, 264, 97, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lbllogo = new JLabel(new ImageIcon("C:\\Users\\mr02-06\\Desktop\\project\\gs25.jpg"));
		lbllogo.setBounds(12, 0, 363, 91);
		contentPane.add(lbllogo);
		
		tfPwd = new JPasswordField();
		tfPwd.setBounds(99, 154, 116, 21);
		contentPane.add(tfPwd);
	}
}
