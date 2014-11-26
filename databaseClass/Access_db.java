package likinsDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** 数据库访问类，提供对数据库的连接和操作,
其它对数据库操作的各个类以及jsp页面对数据库的操作都调用此类的方法 */
public class Access_db {
	String url="jdbc:mysql://localhost:3306/likins";  //数据库url
	String username="root";
	String password="root";
	Connection conn;          //数据库连接
	Statement stmt;           
	ResultSet rs;            //执行查询返回的结果集
	
	/** 构造函数，初始化各变量，连接数据库 */
	public Access_db(){
		stmt=null;
		rs=null;
		conn=null;
		
		//连接数据库
		try{
			Class.forName("com.mysql.jdbc.Driver");  //加载连接数据库的驱动
			conn=DriverManager.getConnection(url, username, password);//获得数据库的连接对象
		}catch(SQLException e){
			System.out.println(e.toString());  //输出异常信息
		}
		catch(ClassNotFoundException e){
			System.out.println(e.toString());  //输出异常信息
		}
	}
	
	/** 执行SQL语句，主要是insert update delete */ 
	public boolean exeSQL(String sql){
		try{
			stmt=conn.createStatement();  //创建Statement对象
			stmt.executeUpdate(sql);    //执行insert 或update语句
			System.out.println("insert into mysql ");
			return true;
		}
		catch(Exception e){
			System.out.println(e.toString());  //输出异常信息
			return false;
		}
	}
	
	
	/** 执行SQL语句，主要是select(查询),返回ResultSet类型的对象 */
	public ResultSet exeSqlQuery(String sql){
		try{
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			System.out.println("select mysql ");
		}
		catch(Exception e){
			System.out.println(e.toString());  //输出异常信息
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
