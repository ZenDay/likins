package likinsDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** ���ݿ�����࣬�ṩ�����ݿ�����ӺͲ���,
���������ݿ�����ĸ������Լ�jspҳ������ݿ�Ĳ��������ô���ķ��� */
public class Access_db {
	String url="jdbc:mysql://localhost:3306/likins";  //���ݿ�url
	String username="root";
	String password="root";
	Connection conn;          //���ݿ�����
	Statement stmt;           
	ResultSet rs;            //ִ�в�ѯ���صĽ����
	
	/** ���캯������ʼ�����������������ݿ� */
	public Access_db(){
		stmt=null;
		rs=null;
		conn=null;
		
		//�������ݿ�
		try{
			Class.forName("com.mysql.jdbc.Driver");  //�����������ݿ������
			conn=DriverManager.getConnection(url, username, password);//������ݿ�����Ӷ���
		}catch(SQLException e){
			System.out.println(e.toString());  //����쳣��Ϣ
		}
		catch(ClassNotFoundException e){
			System.out.println(e.toString());  //����쳣��Ϣ
		}
	}
	
	/** ִ��SQL��䣬��Ҫ��insert update delete */ 
	public boolean exeSQL(String sql){
		try{
			stmt=conn.createStatement();  //����Statement����
			stmt.executeUpdate(sql);    //ִ��insert ��update���
			System.out.println("insert into mysql ");
			return true;
		}
		catch(Exception e){
			System.out.println(e.toString());  //����쳣��Ϣ
			return false;
		}
	}
	
	
	/** ִ��SQL��䣬��Ҫ��select(��ѯ),����ResultSet���͵Ķ��� */
	public ResultSet exeSqlQuery(String sql){
		try{
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			System.out.println("select mysql ");
		}
		catch(Exception e){
			System.out.println(e.toString());  //����쳣��Ϣ
			rs=null;
		}
		return rs;
	}
	
	public void destroy(){
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
