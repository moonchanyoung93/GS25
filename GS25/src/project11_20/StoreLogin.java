package project11_20;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class StoreLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tfId;

	private Map<String, String> map;
	private JPasswordField pwd;
	private JButton btnResult;
	private JButton btnNewButton;
	private Image img ;
	private JLabel lblImage;


	private DefaultTableModel model;
	private Vector<String> col;
	private StoreJoin sj;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoreLogin frame = new StoreLogin();
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
	public StoreLogin() {

		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("아이디");
		lblId.setBounds(207, 38, 57, 15);
		contentPane.add(lblId);

		tfId = new JTextField();
		tfId.setBounds(295, 35, 144, 21);
		contentPane.add(tfId);
		tfId.setColumns(10);

		JLabel lblPassword = new JLabel("비밀번호");
		lblPassword.setBounds(207, 85, 57, 15);
		contentPane.add(lblPassword);


		map=new HashMap<>();
		map.put("오산", "osan1234");
		map.put("안산", "ansan1234");
		map.put("강동", "kangdong1234");
		map.put("강남", "kangnam1234");
		map.put("공주", "kongju1234");
		map.put("일산", "ilsan1234");
		

		
		JButton btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=tfId.getText();
				String pw=String.valueOf(pwd.getPassword());
				String strId=map.get(id);
				if(strId !=null && strId.equals(pw)) {
					btnResult.setForeground(Color.blue);
					btnResult.setText(id+"지점장님 입장하시기 바랍니다.");

				}else {
					btnResult.setForeground(Color.red);
					btnResult.setText("아이디 또는 비밀번호가 일치하지 않습니다.");
				}

			}
		});
		btnLogin.setBounds(450, 26, 97, 90);
		contentPane.add(btnLogin);

		pwd = new JPasswordField();
		pwd.setBounds(295, 82, 144, 21);
		contentPane.add(pwd);

		btnResult = new JButton("");
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id=tfId.getText();
				String pw=String.valueOf(pwd.getPassword());
				String strId=map.get(id);
				if(strId !=null && strId.equals(pw)){
					StoreList slist=new StoreList(StoreLogin.this);
					slist.setVisible(true);
					slist.setLocation(300, 300);
					dispose();
				}else {
					JOptionPane.showMessageDialog(StoreLogin.this, "입장하실 수 없습니다.");
				}
			}
		});
		btnResult.setBounds(202, 140, 345, 23);
		contentPane.add(btnResult);

		btnNewButton = new JButton("종료");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(450, 184, 97, 23);
		contentPane.add(btnNewButton);

		lblImage = new JLabel(new ImageIcon("C:\\Users\\mr02-06\\Desktop\\project\\gss.jpg"));
		lblImage.setBounds(12, 10, 167, 197);
		contentPane.add(lblImage);
	}


}
