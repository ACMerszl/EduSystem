package trial;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTable;
import java.util.Date;
import java.util.Vector;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
public class StuUI extends JFrame {
	private static String Sdeparment, Sno, Sclass, Sname, Saverage, Sdate;
	private static DBManager dbManager;
	private static Connection connection;
	private static Statement stmt;
	private static ResultSet resultSet;
	private DefaultTableModel model;//表格模型
    private JTable table;//表格
	
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
		
		
		JLabel lblNewLabel = new JLabel("\u534E\u5317\u6C34\u5229\u6C34\u7535\u5927\u5B66\u6210\u7EE9\u660E\u7EC6(\u6709\u6548)");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(333, 97, 355, 28);
		getContentPane().add(lblNewLabel);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = (String)df.format(new Date());
		
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
		
		JPanel panel = new JPanel();
		panel.setBounds(25, 135, 907, 53);
		getContentPane().add(panel);
		panel.setLayout(null);
		setVisible(true);
		
		JButton button = new JButton("\u68C0\u7D22");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector columnNames, rowData;
				columnNames = new Vector();
				columnNames.add("序号");
				columnNames.add("课程号");
				columnNames.add("课程名");
				columnNames.add("学分");
				columnNames.add("绩点");
				columnNames.add("学分绩点");
				String op = (String)comboBox.getSelectedItem();
				rowData = new Vector();
				
				String sql = "select Score.CidNum, Score.Cname, Cscore, Sscore " + 
						"from Student, Course, Score " + 
						"where Student.Sno = Score.Sno and Course.CidNum = Score.CidNum and Score.Sno = '" +
						ID + "' and Cteachtime = '" + op +"'";
				//System.out.println(sql);
				dbManager = new DBManager();
				connection = dbManager.getConnection();
				try {
					stmt = connection.createStatement();
					resultSet = stmt.executeQuery(sql);
					double studyScore = 0, multySum = 0, multy = 0;
					
					int index = 1;
					while (resultSet.next()) {
						Vector tmp = new Vector();
						double tmp1 = resultSet.getDouble(3), tmp2 = resultSet.getDouble(4);
						tmp.add(String.valueOf(index));
						tmp.add(resultSet.getString(1));
						tmp.add(resultSet.getString(2));
						tmp.add(resultSet.getString(3));
						tmp.add(resultSet.getString(4));
						multy = tmp1 * tmp2;
						
						String format = new DecimalFormat("#.00").format(multy);
//						System.out.println(format);
						tmp.add(format);
						
						studyScore += tmp1;
						multySum += multy;
						index++;
						rowData.add(tmp);
//						System.out.println(studyScore + "**" + multy);
					}
					
//					System.out.println(multySum);
					double ans = multySum / studyScore;		
					
					String format = new DecimalFormat("#.00").format(ans);
//					System.out.println(format);
					Saverage = format;
//					System.out.println(Saverage);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					dbManager.releaseConnection();
				}
				JLabel lblNewLabel_1 = new JLabel("\u9662(\u7CFB)/\u90E8\uFF1A");
				lblNewLabel_1.setBounds(30, 10, 99, 15);
				panel.add(lblNewLabel_1);
				lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
				
				JLabel lblNewLabel_2 = new JLabel("\u5B66     \u53F7\uFF1A");
				lblNewLabel_2.setBounds(30, 35, 99, 15);
				panel.add(lblNewLabel_2);
				lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
				
				JLabel lblNewLabel_3 = new JLabel("\u884C\u653F\u73ED\u7EA7\uFF1A");
				lblNewLabel_3.setBounds(373, 10, 72, 15);
				panel.add(lblNewLabel_3);
				lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
				
				JLabel lblNewLabel_4 = new JLabel("\u59D3    \u540D\uFF1A");
				lblNewLabel_4.setBounds(373, 35, 100, 15);
				panel.add(lblNewLabel_4);
				lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 14));
				
				JLabel lblNewLabel_5 = new JLabel("\u5E73\u5747\u5B66\u5206\u7EE9\u70B9\uFF1A");
				lblNewLabel_5.setBounds(695, 10, 110, 15);
				panel.add(lblNewLabel_5);
				lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 14));
				
				JLabel lblNewLabel_6 = new JLabel("\u6253\u5370\u65F6\u95F4\uFF1A");
				lblNewLabel_6.setBounds(695, 28, 72, 29);
				panel.add(lblNewLabel_6);
				lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 14));
				
				JLabel lblNewLabel_12 = new JLabel(date);
				lblNewLabel_12.setBounds(762, 35, 107, 15);
				panel.add(lblNewLabel_12);
				lblNewLabel_12.setFont(new Font("宋体", Font.PLAIN, 14));
				
				JLabel lblNewLabel_10 = new JLabel(Sname);
				lblNewLabel_10.setBounds(444, 35, 97, 15);
				panel.add(lblNewLabel_10);
				lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 14));
				
				JLabel lblNewLabel_9 = new JLabel(Sclass);
				lblNewLabel_9.setBounds(444, 10, 97, 15);
				panel.add(lblNewLabel_9);
				lblNewLabel_9.setFont(new Font("宋体", Font.PLAIN, 14));
				
				JLabel lblNewLabel_8 = new JLabel(Sno);
				lblNewLabel_8.setBounds(103, 35, 128, 15);
				panel.add(lblNewLabel_8);
				lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 14));
				
				JLabel lblNewLabel_7 = new JLabel(Sdeparment);
				lblNewLabel_7.setBounds(103, 10, 128, 15);
				panel.add(lblNewLabel_7);
				lblNewLabel_7.setFont(new Font("宋体", Font.PLAIN, 14));
				
				JLabel lblNewLabel_11 = new JLabel(Saverage);
				lblNewLabel_11.setBounds(788, 10, 81, 15);
				panel.add(lblNewLabel_11);
				lblNewLabel_11.setFont(new Font("宋体", Font.PLAIN, 14));
				
				panel.setVisible(true);
			        model = new DefaultTableModel(rowData, columnNames);//设置模型
			        table = new JTable(model);//引用模型，或table.setModel(model);

//			        getContentPane().add(sc, BorderLayout.CENTER);
			        //table.setRowHeight(30);
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(47, 196, 891, 344);
				getContentPane().add(scrollPane, BorderLayout.CENTER);
			}
		});
		
		
		
		
		
		panel.setVisible(false);
		
		button.setFont(new Font("宋体", Font.PLAIN, 14));
		button.setBounds(660, 49, 97, 23);
		getContentPane().add(button);
	}
	
	private JPanel contentPane;
	
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
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbManager.releaseConnection();
		}
		
	}
	
	public static void main(String args[]) {
		new StuUI("201720207");
	}
}