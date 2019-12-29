package AdminInfo;

import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import DBManager.SqlTool;

public class InfoModel extends AbstractTableModel{
	
	private Vector columnNames; 
	private Vector rowDates; 

	public InfoModel() {
		String sql = "select * from Student";
		String []paras = {};
	}
	
	public boolean cudStu(String sql, String []paras) {
		return new SqlTool().cudExecute(sql, paras);
	}
	
	public void queryStu(String sql, String []paras) 
	  { 
	    SqlTool sqlTool = null; 
	    //========��ʼ��JTable��Ϣ 
	    columnNames = new Vector(); 
	    rowDates = new Vector(); 
	    columnNames.add("ѧ��"); 
	    columnNames.add("����"); 
	    columnNames.add("�Ա�"); 
	    columnNames.add("��������"); 
	    columnNames.add("�����༶"); 
	    columnNames.add("רҵ");
	    columnNames.add("Ժϵ");
	    columnNames.add("����");
	    columnNames.add("�ܱ�����");
	    columnNames.add("�ܱ���");
	      
	    try { 
	      sqlTool = new SqlTool(); 
	      ResultSet rs = sqlTool.queryExecute(sql, paras); 
	      while(rs.next()) { 
	        Vector row = new Vector(); 
	        row.add(rs.getString(1)); 
	        row.add(rs.getString(2)); 
	        row.add(rs.getString(3)); 
	        row.add(rs.getString(4)); 
	        row.add(rs.getString(5)); 
	        row.add(rs.getString(6)); 
	        row.add(rs.getString(7));
	        row.add(rs.getString(8));
	        row.add(rs.getString(9));
	        row.add(rs.getString(10));
	        rowDates.add(row); 
	      } 
	    } catch (Exception e) { 
	      // TODO: handle exception 
	    } finally { 
	      sqlTool.close(); 
	    } 
	      
	  } 
	
	
	 @Override
	  public int getColumnCount() { 
	    // TODO Auto-generated method stub 
	    return this.columnNames.size(); 
	  } 
	  
	  @Override
	  public int getRowCount() { 
	    // TODO Auto-generated method stub 
	    return this.rowDates.size(); 
	  } 
	  
	  @Override
	  public Object getValueAt(int row, int col) { 
	    // TODO Auto-generated method stub 
	    if(!rowDates.isEmpty()) 
	      return ((Vector)this.rowDates.get(row)).get(col); 
	    else
	      return null; 
	  } 
	  
	    
	  @Override
	  public String getColumnName(int column) { 
	    // TODO Auto-generated method stub 
	    return (String)this.columnNames.get(column); 
	  } 
}

