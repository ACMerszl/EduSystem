package trial;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTable;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class StuUI extends JFrame {
	private static String Sdeparment, Sno, Sclass, Sname, Saverage, Sdate;
	private static DBManager dbManager;
	private static Connection connection;
	private static Statement stmt;
	private static ResultSet resultSet;
	
	
	
	public StuUI(String ID) {
		getInfo(ID);
		setSize(1000, 600);
		setLocation(300,100); // 屏幕位置
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u67E5\u770B\u6210\u7EE9");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(419, 10, 145, 22);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5B66\u5E74\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(177, 53, 58, 15);
		getContentPane().add(label_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"2018-2019\u5B66\u5E74", "2017-2018\u5B66\u5E74"}));
		comboBox.setBounds(225, 49, 128, 23);
		getContentPane().add(comboBox);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u6210\u7EE9\u6709\u6548");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(359, 49, 127, 23);
		getContentPane().add(rdbtnNewRadioButton);
		
		JButton button = new JButton("\u68C0\u7D22");
		button.setFont(new Font("宋体", Font.PLAIN, 14));
		button.setBounds(660, 49, 97, 23);
		getContentPane().add(button);
		
		JLabel lblNewLabel = new JLabel("\u534E\u5317\u6C34\u5229\u6C34\u7535\u5927\u5B66\u6210\u7EE9\u660E\u7EC6(\u6709\u6548)");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(326, 82, 355, 28);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u9662(\u7CFB)/\u90E8\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(53, 133, 99, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5B66     \u53F7\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(53, 158, 99, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u884C\u653F\u73ED\u7EA7\uFF1A");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(414, 133, 72, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u59D3    \u540D\uFF1A");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(414, 158, 100, 15);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u5E73\u5747\u5B66\u5206\u7EE9\u70B9\uFF1A");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(724, 133, 110, 15);
		/*
		 * 平均学分绩点先鸽了。。。
		 */
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\u6253\u5370\u65F6\u95F4\uFF1A");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(724, 158, 72, 15);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = (String)df.format(new Date());// new Date()为获取当前系统时间
		getContentPane().add(lblNewLabel_6);
		
		table = new JTable();
		table.setBounds(32, 201, 919, 332);
		getContentPane().add(table);
		
		JLabel lblNewLabel_7 = new JLabel(Sdeparment);
		lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(132, 133, 128, 15);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel(Sno);
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(132, 158, 128, 15);
		getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel(Sclass);
		lblNewLabel_9.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(487, 133, 97, 15);
		getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel(Sname);
		lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_10.setBounds(487, 158, 97, 15);
		getContentPane().add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("***");
		lblNewLabel_11.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_11.setBounds(825, 133, 81, 15);
		getContentPane().add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel(date);
		lblNewLabel_12.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_12.setBounds(794, 158, 107, 15);
		getContentPane().add(lblNewLabel_12);
		
		JButton btnNewButton = new JButton("\u6CE8\u9500");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainUI();
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton.setBounds(767, 49, 97, 23);
		getContentPane().add(btnNewButton);
		
		
		setVisible(true);
	}
	private JPanel contentPane;
	private JTable table;
	
	static public void getInfo(String ID) {
		String sql = "SELECT * FROM Student";
		dbManager = new DBManager();
		connection = dbManager.getConnection();
		try {
			stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				if (resultSet.getString(1).equals(ID)) {
					Sdeparment = resultSet.getString(7);
					Sno = resultSet.getString(1);
					Sclass = resultSet.getString(5);
					Sname = resultSet.getString(2);
				}
			} 
			
			dbManager.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//	public static void main(String args[]) {
//		new StuUI("201720207");
//	}
}