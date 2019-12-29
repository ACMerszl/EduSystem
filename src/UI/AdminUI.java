package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import AdminInfo.AdminStuInfoUI;
import AdminScore.AdminStuScoreUI;

public class AdminUI extends JFrame implements ActionListener {
	public AdminUI() {
		setSize(1000, 600);  //窗体大小
		setLocation(300,100); // 屏幕位置
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("\u5B66\u751F\u4FE1\u606F\u7BA1\u7406");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 25));
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("Info");
		btnNewButton.setBounds(131, 205, 290, 115);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u5B66\u751F\u6210\u7EE9\u7BA1\u7406");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 25));
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setActionCommand("Score");
		btnNewButton_1.setBounds(603, 205, 290, 115);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("\u5DE5\u4F5C\u6109\u5FEB");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 40));
		lblNewLabel.setBounds(322, 64, 374, 65);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("\u6CE8\u9500");
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setActionCommand("back");
		btnNewButton_2.setBounds(879, 10, 97, 45);
		getContentPane().add(btnNewButton_2);
		
		setVisible(true);
	}
	private JPanel contentPane;

//	public static void main(String[] args) {
//		new AdminUI();
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Info")) {
			new AdminStuInfoUI();
			setVisible(false);
		} else if (e.getActionCommand().equals("Score")) {
			new AdminStuScoreUI();
			setVisible(false);
		} else if (e.getActionCommand().equals("back")) {
			new MainUI();
			setVisible(false);
		}
	}
}
