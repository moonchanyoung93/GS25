package project11_20;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class StoreList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfCompany;
	private JTextField tfLocation;
	private JTextField tfPrice;
	private JTextField tfAmount;
	private JTextField tfSearch;
	
	private StoreDAO dao;
	private StoreDTO dto;
	private Vector<String> col;
	private DefaultTableModel model;
	private StoreLogin sl;
	private ImageIcon img;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StoreList frame = new StoreList();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public StoreList(StoreLogin sl) {
		this();
		this.sl=sl;
	}

	/**
	 * Create the frame.
	 */
	public StoreList() {
		setTitle("GS25 재고");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 646);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		dao=new StoreDAO();
		col=new Vector<String>();
		col.add("제품번호");
		col.add("제품명");
		col.add("제조회사");
		col.add("지점");
		col.add("단가");
		col.add("수량");
		col.add("금액");
		list();

		

		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 467, 223);
		contentPane.add(panel);
		panel.setLayout(null);


		JLabel lblNewLabel = new JLabel("제품번호");
		lblNewLabel.setBounds(12, 10, 57, 15);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("제품명");
		lblNewLabel_1.setBounds(12, 38, 57, 15);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("제조 회사");
		lblNewLabel_2.setBounds(12, 71, 57, 15);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("지점");
		lblNewLabel_3.setBounds(12, 99, 57, 15);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("단가");
		lblNewLabel_4.setBounds(12, 127, 57, 15);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("수량");
		lblNewLabel_5.setBounds(12, 155, 57, 15);
		panel.add(lblNewLabel_5);

		tfNo = new JTextField();
		tfNo.setBounds(97, 7, 116, 21);
		panel.add(tfNo);
		tfNo.setColumns(10);

		tfName = new JTextField();
		tfName.setBounds(97, 35, 116, 21);
		panel.add(tfName);
		tfName.setColumns(10);

		tfCompany = new JTextField();
		tfCompany.setBounds(97, 68, 116, 21);
		panel.add(tfCompany);
		tfCompany.setColumns(10);

		tfLocation = new JTextField();
		tfLocation.setBounds(97, 96, 116, 21);
		panel.add(tfLocation);
		tfLocation.setColumns(10);

		tfPrice = new JTextField();
		tfPrice.setBounds(97, 124, 116, 21);
		panel.add(tfPrice);
		tfPrice.setColumns(10);

		tfAmount = new JTextField();
		tfAmount.setBounds(97, 152, 116, 21);
		panel.add(tfAmount);
		tfAmount.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("제품명");
		lblNewLabel_6.setFont(new Font("굴림", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(12, 183, 116, 30);
		panel.add(lblNewLabel_6);

		tfSearch = new JTextField();
		tfSearch.setFont(new Font("굴림", Font.PLAIN, 18));
		tfSearch.setBounds(97, 183, 116, 28);
		panel.add(tfSearch);
		tfSearch.setColumns(10);

		JButton btnSearch = new JButton("찾기");
		btnSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
			}
		});
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		btnSearch.setBounds(239, 183, 166, 29);
		panel.add(btnSearch);

		JButton btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result=dao.insertStore(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(StoreList.this, "저장되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnSave.setBounds(278, 6, 127, 47);
		panel.add(btnSave);

		JButton btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input();
				int result=dao.updateStore(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(StoreList.this, "수정되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnUpdate.setBounds(278, 67, 127, 47);
		panel.add(btnUpdate);

		JButton btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tfName.getText();
				int result=0;
				int response=JOptionPane.showConfirmDialog(StoreList.this, "삭제하시겠습니까?");
				if(response==JOptionPane.YES_OPTION) {
					result=dao.deleteStore(dto);
					
				}
				if(result==1) {
					JOptionPane.showMessageDialog(StoreList.this, "삭제되었습니다.");
					list();
					table.setModel(model);
					clear();
				}
			}
		});
		btnDelete.setBounds(278, 123, 127, 47);
		panel.add(btnDelete);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 243, 467, 325);
		contentPane.add(scrollPane);

		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx= table.getSelectedRow();
				tfNo.setEditable(false);
				tfNo.setText(table.getValueAt(idx, 0)+"");
				tfName.setText(table.getValueAt(idx, 1)+"");
				tfCompany.setText(table.getValueAt(idx, 2)+"");
				tfLocation.setText(table.getValueAt(idx, 3)+"");
				tfPrice.setText(table.getValueAt(idx, 4)+"");
				tfAmount.setText(table.getValueAt(idx, 5)+"");
			}
		});
		scrollPane.setViewportView(table);

		JButton btnExit = new JButton("나가기");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(295, 575, 184, 23);
		contentPane.add(btnExit);
	}

	public void list() {
		model=new DefaultTableModel(dao.listStore(),col) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

	}

	public void search() {
		String name=tfSearch.getText();
		model=new DefaultTableModel(dao.searchStore(name),col);
		table.setModel(model);
	}
	
	public void input() {
		String no=tfNo.getText();
		String name = tfName.getText();
		String company= tfCompany.getText();
		String location= tfLocation.getText();
		int price=Integer.parseInt(tfPrice.getText());
		int amount=Integer.parseInt(tfAmount.getText());
		dto=new StoreDTO(no, name, company, location, price, amount);
	}
	
	public void clear() {
		tfNo.setText("");
		tfName.setText("");
		tfCompany.setText("");
		tfLocation.setText("");
		tfPrice.setText("");
		tfAmount.setText("");
		tfName.requestFocus();
		tfNo.setEditable(true);
	}
}

