package likinsDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;

/** the class process the user information */
public class Userinfo extends Access_db{
	private String userID;  
	private String username; 
	private String password;
	private String signature;   //signature path
	private String portrait;   //portrait path
	private String sql;       //sql statement to perform various operations 
	
	/** constructor */
	public Userinfo(){
		userID="";
		username="";
		password="";
		signature="";
		portrait="";
	}
	
	/** constructor */
	public Userinfo(String userid,String name,String pass,String signaturePath,String portraitPath){
		userID=userid;
		username=name;
		password=pass;
		signature=signaturePath;
		portrait=portraitPath;
	}
	
	/** set user ID */
	public void setUserID(String id){
		this.userID=id;
	}
	
	/** set user name */
	public void setUserName(String name){
		this.username=name;
	}
	
	/** set user password */
	public void setPassword(String pass){
		this.password=pass;
	}
	
	/** set user signature path */
	public void setSignature(String sig){
		this.signature=sig;
	}
	
	/** set user portrait path */
	public void setPortrait(String path){
		this.portrait=path;
	}
	
	/** get user ID */
	public String getUserID(){
		return userID;
	}
	
	/** get user name */
	public String getUsername(){
		return username;
	}
	
	/** get password */
	public String getPassword(){
		return password;
	}
	/** get signature path */
	public String getSignature(){
		return signature;
	}
	/** get portrait path */
	public String getPortrait(){
		return portrait;
	}
	
	/** set user's information parameter are username,signature path,portrait path
	 * according to the userID */
	public boolean setUserInfo(){
		boolean is_set_success=false;
		//insert username,signature,portrait into userinfo 
		sql=" update userinfo set username="+ "'" +getUsername()+ "'," ;
		sql=sql+"signature=" + "'" + getSignature() + "',";
		sql=sql+"portrait=" + "'" + getPortrait() + "'";
		sql=sql+"where userID=" + "'" + getUserID() + "';" ;    
		is_set_success=super.exeSQL(sql);
		return is_set_success;
	}
	
	//the function modifying information 
	/** modify the user name */
	public boolean modifyUsername() {
		boolean is_modify_success=false;
		sql="update userinfo set username=" + "'"+getUsername()+ "'";
		sql=sql+" where userID=" +"'" + getUserID()+ "';";
		is_modify_success=super.exeSQL(sql);
		return is_modify_success;
		
	}
	
	/** modify the user password */
	public boolean modifyPassword(){
		boolean is_modify_success=false;
		sql="update userinfo set password=" + "'"+getPassword()+ "'";
		sql=sql+" where userID=" +"'" + getUserID()+ "';";
		is_modify_success=super.exeSQL(sql);
		return is_modify_success;
		
	}
	
	/** modify the user signature */
	public boolean modifySignature(){
		boolean is_modify_success=false;
		sql="update userinfo set signature=" + "'"+getSignature()+ "'";
		sql=sql+" where userID=" +"'" + getUserID()+ "';";
		is_modify_success=super.exeSQL(sql);
		return is_modify_success;
	}
	
	/** modify the user portrait */
	public boolean modifyPortrait(){
		boolean is_modify_success=false;
		sql="update userinfo set portrait=" + "'"+getPortrait()+ "'";
		sql=sql+" where userID=" +"'" + getUserID()+ "';";
		is_modify_success=super.exeSQL(sql);
		return is_modify_success;
	}
	
	
	//the function for getting information from database
	/** get user name from database */
	public String getUserNameFromDb(){
		ResultSet rs=null;
		String userName="";
		sql="select username from userinfo where userID=";   //select username
		sql=sql+ "'" + getUserID() + "';";
		
		rs=exeSqlQuery(sql);
		try {
			userName=rs.getString("username");   //convert rs to String 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userName;
		
	}
	
	/** get signature from database */
	public String getSignatureFromDb(){
		ResultSet rs=null; 
		String signaturePath="";
		
		sql="select signature from userinfo where userID=";
		sql=sql+ "'" + getUserID() + "';";  
		rs=exeSqlQuery(sql);
		try {
			signaturePath=rs.getString("signature");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return signaturePath;
	}
	
	/** get portrait from database */
	public String getPortraitFromDb(){
		ResultSet rs=null;
		String portraitPath="";
		sql="select portrait from userinfo where userID=";
		sql=sql+ "'" + getUserID() + "';";
		rs=exeSqlQuery(sql);
		try { 
			portraitPath=rs.getString("portrait");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return portraitPath;
	}
	
	/** Determine whether the username is exist */
	public boolean isUserNameExist(){
		ResultSet rs=null;
		//query the username on table userinfo
		sql="select * from userinfo where username='"+getUsername()+"';";
		boolean is_exist=false;  //do not exist
		try{
			rs=super.exeSqlQuery(sql);
			if(rs.next()){
				is_exist=true;   //username is exist, return true
			}
		}catch (Exception e) {
			System.out.println(e.toString());// TODO: handle exception
		}
		return is_exist;
	}
}
