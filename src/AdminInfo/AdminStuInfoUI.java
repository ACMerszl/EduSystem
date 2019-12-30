package AdminInfo;


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

public class AdminStuInfoUI extends JFrame implements ActionListener {
	
	private static String sno, cid, name, score, reScore;
 
	private static ResultSet resultSet;
	private InfoModel model;
	private JTable table;// 表格
	private JComboBox comboBox;

	public AdminStuInfoUI() {
		setSize(1000, 600); // 窗体大小
		setLocation(300, 100);
		getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("\u589E\u52A0");
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("add");
		btnNewButton.setBounds(10, 184, 111, 46);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 14));
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u5220\u9664");
		btnNewButton_1.setBounds(10, 316, 111, 46);
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setActionCommand("del");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 14));
		getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\u4FEE\u6539");
		btnNewButton_2.setBounds(10, 437, 111, 46);
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

		model = new InfoModel();
		String sql = "select * from Student";
		model.queryStu(sql, null);
		table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(167, 94, 809, 459);
		getContentPane().add(scrollPane);
		
		JButton btnNewButton_5 = new JButton("\u4E3B\u754C\u9762");
		btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_5.addActionListener(this);
		btnNewButton_5.setActionCommand("back");
		btnNewButton_5.setBounds(10, 10, 141, 62);
		getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_3 = new JButton("\u67E5\u8BE2");
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_3.addActionListener(this);
		btnNewButton_3.setActionCommand("select");
		btnNewButton_3.setBounds(663, 31, 111, 25);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("\u5168\u90E8\u663E\u793A");
		btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_4.addActionListener(this);
		btnNewButton_4.setActionCommand("selectAll");
		btnNewButton_4.setBounds(810, 31, 111, 25);
		getContentPane().add(btnNewButton_4);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u6309\u5B66\u53F7\u67E5\u8BE2", "\u6309\u73ED\u7EA7\u67E5\u8BE2", "\u6309\u4E13\u4E1A\u67E5\u8BE2", "\u6309\u9662\u7CFB\u67E5\u8BE2"}));
		comboBox.setBounds(247, 32, 121, 23);
		getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(413, 33, 156, 21);
		getContentPane().add(textField);
		textField.setColumns(10);

		scrollPane.setVisible(true);

		setVisible(true);
	}

	private JPanel contentPane;
	private JTextField textField;

//	public static void main(String[] args) {
//		new AdminStuInfoUI();
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("add")) {
			new AdminInfoAdd(this, "By-szl", true);
			String sql = "select * from Student";
			jtableUpdate(sql, null);
		} else if (e.getActionCommand().equals("del")) {
			int rowNum = this.table.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}

			String Id = (String) model.getValueAt(rowNum, 0);
			String sql = "delete from Student where Sno = ?";
			String[] paras = {Id};
			InfoModel tmp = new InfoModel();
			tmp.cudStu(sql, paras);

			sql = "select * from Student";
			jtableUpdate(sql, null);
		}  else if (e.getActionCommand().equals("updata")) {
			int rowNum = this.table.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			new AdminInfoUpdata(this, "By-szl", true, model, rowNum);
			String sql = "select * from Student";
			jtableUpdate(sql, null);
		} else if (e.getActionCommand().equals("selectAll")) {
			String sql = "select * from Student";
			jtableUpdate(sql, null);
		} else if (e.getActionCommand().equals("back")) {
			new AdminUI();
			setVisible(false);
		} else if (e.getActionCommand().equals("select")) {
			String op = (String)comboBox.getSelectedItem();
			String sql = null;
			String key = textField.getText();
			if (key.length() != 0) {
				if (op.equals("按学号查询")) {
					sql = "select * from Student where Sno = ?";
				} else if (op.equals("按班级查询")) {
					sql = "select * from Student where Sclass = ?";
				} else if (op.equals("按专业查询")) {
					sql = "select * from Student where Smajor = ?";
				} else {
					sql = "select * from Student where Sdeparment = ?";
				}
			} else JOptionPane.showMessageDialog(null, "不能为空！", "消息",JOptionPane.WARNING_MESSAGE); 
			
			if (sql.length() != 0 && key.length() != 0) {
				String []paras = {key};
				jtableUpdate(sql, paras);
			}
		}
	}

	public void jtableUpdate(String sql, String[] paras) {
		// ......创建模型
		model = new InfoModel();
		model.queryStu(sql, paras);
		// ......更新显示
		table.setModel(model);
	}
}
