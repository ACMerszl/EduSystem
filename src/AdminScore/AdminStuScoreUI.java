package AdminScore;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

import DBManager.SqlTool;
import UI.AdminUI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Vector;

public class AdminStuScoreUI extends JFrame implements ActionListener {
//	private static String sno, cid, name, score, reScore;
//	private static Statement stmt;
//	private static ResultSet resultSet;
	private ScoreModel model;
	private JTable table;// 表格
	private JTextField textField;
	private JComboBox comboBox;
	
	public AdminStuScoreUI() {
		setTitle("By-szl");
		setSize(1000, 600); // 窗体大小
		setLocation(300, 100);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("\u589E\u52A0");
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("add");

		btnNewButton.setBounds(10, 183, 118, 46);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 14));
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u5220\u9664");
		btnNewButton_1.setBounds(10, 291, 118, 46);
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setActionCommand("del");

		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 14));
		getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\u4FEE\u6539");
		btnNewButton_2.setBounds(10, 390, 118, 46);
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setActionCommand("updata");
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 14));
		getContentPane().add(btnNewButton_2);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 82, 986, 2);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(150, 82, 1, 481);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		getContentPane().add(separator_1);

		model = new ScoreModel();
		String sql = "select * from Score";
		model.queryStu(sql, null);
		table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(161, 94, 815, 459);
		getContentPane().add(scrollPane);
		
		JButton btnNewButton_4 = new JButton("\u4E3B\u754C\u9762");
		btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_4.addActionListener(this);
		btnNewButton_4.setActionCommand("back");
		btnNewButton_4.setBounds(10, 10, 140, 62);
		getContentPane().add(btnNewButton_4);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 14));
		textField.setBounds(351, 33, 141, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_6 = new JButton("\u67E5\u8BE2");
		btnNewButton_6.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_6.addActionListener(this);
		btnNewButton_6.setActionCommand("select");
		btnNewButton_6.setBounds(522, 30, 140, 26);
		getContentPane().add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("\u663E\u793A\u5168\u90E8");
		btnNewButton_7.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_7.addActionListener(this);
		btnNewButton_7.setActionCommand("selectAll");
		btnNewButton_7.setBounds(705, 30, 166, 26);
		getContentPane().add(btnNewButton_7);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u6309\u5B66\u53F7\u67E5\u8BE2", "\u6309\u8BFE\u7A0B\u53F7\u67E5\u8BE2"}));
		comboBox.setBounds(214, 32, 112, 23);
		getContentPane().add(comboBox);
		
		scrollPane.setVisible(true);
		
		
		setVisible(true);
	}

	private JPanel contentPane;

//	public static void main(String[] args) {
//		new AdminStuScoreUI();
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("add")) {
			new AdminScoreAdd(this, "By-szl", true);
			String sql = "select * from Score";
			jtableUpdate(sql, null);
		} else if (e.getActionCommand().equals("del")) {
			int rowNum = this.table.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}

			String Id = (String) model.getValueAt(rowNum, 0);
			String Cid = (String) model.getValueAt(rowNum, 1);
			String sql = "delete from Score where Sno = ? and CidNum = ?";
			String[] paras = {Id, Cid};
			ScoreModel tmp = new ScoreModel();
			tmp.cudStu(sql, paras);

			sql = "select * from Score";
			jtableUpdate(sql, null);
		} else if (e.getActionCommand().equals("updata")) {
			int rowNum = this.table.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			new AdminScoreUpdata(this, "By-szl", true, model, rowNum);
			String sql = "select * from Score";
			jtableUpdate(sql, null);
		} else if (e.getActionCommand().equals("select")) {
			String op = (String)comboBox.getSelectedItem();
			String name = textField.getText().trim();
			String sql = null;
			if (name.length() != 0) {
				// ========姓名输入有效时，执行查询
				// ......定义参数
				if (op.equals("按学号查询")) {
					sql = "select * from Score where Sno = ?";
				} else sql = "select * from Score Where CidNum = ?";
				String[] paras = {name};
				// ......更新模型
				jtableUpdate(sql, paras);
			} else {		
				JOptionPane.showMessageDialog(null, "不能为空！", "消息",JOptionPane.WARNING_MESSAGE); 
			}
		} else if (e.getActionCommand().equals("selectAll")) {
			String sql = "select * from Score"; 
		    jtableUpdate(sql, null); 
		} else if (e.getActionCommand().equals("back")) {		
			new AdminUI();
			setVisible(false);
		}
	}

	public void jtableUpdate(String sql, String[] paras) {
		// ......创建模型
		model = new ScoreModel();
		model.queryStu(sql, paras);
		// ......更新显示
		table.setModel(model);
	}
}
