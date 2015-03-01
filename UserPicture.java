package likinsDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** process the user picture */
public class UserPicture extends Access_db{
	
	
	private String userID;
	private String picturePath;   //path of picture
	private String introduction;  //introduction of picture
	private String sql;    //sql statement to perform various operations 
	 
	/** constructor */
	public UserPicture(){
		userID="";
		picturePath="";
		introduction="";
		sql="";
	}
	
	/** constructor */
	public UserPicture(String userid,String path,String intro){
		userID=userid;
		picturePath=path;
		introduction=intro;
	}
	
	/** get userID */
	public String getUserID() {
		return userID;
	}

	/** set userID */
	public void setUserID(String userid) {
		this.userID = userid;
	}


	/** get path of picture */
	public String getPicturePath() {
		return picturePath;
	}

	/** set path of picture */
	public void setPicturePath(String Path) {
		this.picturePath = Path;
	}

	/** get introduction of picture */
	public String getIntroduction() {
		return introduction;
	}

	/** set introduction of picture */
	public void setIntroduction(String intro) {
		this.introduction = intro;
	}
	
	


	//function for adding picture
	/** add the path of picture into table user_picture */
	public boolean addPicture(){
		ResultSet rs=null;
		int id=0;        //store the id get from the table userinfo
		boolean is_add_success=false;
		
		sql="select ID from userinfo where username=" + "'"+ getUserID() +"';" ;  //get ID according to the userID
		rs=super.exeSqlQuery(sql);
		try {
			id=rs.getInt(1);    //convert rs to integer
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//insert picture into table user_picture
		sql="insert into user_picture (ID,picture_path,introduction,time) values ";
		sql=sql+ "(" + id + ",";
		sql=sql+ "'" + getPicturePath() + "',";
		sql=sql+ "'" + getIntroduction() + "',";
		sql=sql+ "now()";
		
		is_add_success=super.exeSQL(sql);
		return is_add_success;
		
	}
	
	//function for deleting picture
	
	/** get picture id by path */
	int getPictureIDByPath() throws SQLException{
		ResultSet rs=null;
		int pictureID=0;
		sql="select picture_id from user_picture where picture_path="+getPicturePath()+";";
		rs=exeSqlQuery(sql);
		try{
			pictureID=rs.getInt("user_picture");
		}catch (Exception e) {
			// TODO: handle exception
		}
		return pictureID;
		
	} 
	
	/** delete comment about the picture 
	 * @throws SQLException */
	boolean deleteComment() throws SQLException{
		//delete information about the picture in the table comment
		
		sql=" delete from comment where picture_id=" + getPictureIDByPath()+";";

		boolean is_comment_delete=super.exeSQL(sql);
		//ensure delete successfully 
		while(is_comment_delete==false){
			is_comment_delete=super.exeSQL(sql);
		}
		return is_comment_delete;	
	}
	
	/** delete praise number about the picture */
	boolean deletePraise(){
		//delete information about the picture in the table praise
		try {
			sql=" delete from praise where picture_id=" + getPictureIDByPath()+";";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean is_praise_delete=super.exeSQL(sql);
		//ensure delete successfully
		while(is_praise_delete==false){
			is_praise_delete=super.exeSQL(sql);
		}
		return is_praise_delete;
	}
	
	/**delete picture
	 * @throws SQLException */
	public boolean deletePicture() throws SQLException{
		//after delete comment and praise,delete the picture
		if(deleteComment()==true&&deletePraise()==true){
			//delete the picture
			try {
				sql="delete from user_picture where picture_id=" + getPictureIDByPath() +";";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}
			boolean is_picture_delete=super.exeSQL(sql);
			//ensure delete successfully
			while(is_picture_delete==false){
				is_picture_delete=super.exeSQL(sql);
			}
			
			return is_picture_delete;
		}
		else return false;
		
	}
	
	
	//function for showing picture
	
	/** get the ID in the table userinfo by the userID */
	public int getIDFromDB(){
		ResultSet rs=null;
		int id=0;                 //store the ID select from the userinfo
		String 
		
		//query the ID in the table userinfo by the userID;
		sql="select ID from userinfo where userID=" + "'" + getUserID()+ "';" ; 
		rs=super.exeSqlQuery(sql);
		try {
			rs.next();
			id=rs.getInt("ID");          // convert rs to integer
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
		
	
	/** get a array of picture id by the user id */
	public int[] showPictureList(int id){
		ResultSet rs=null;
		List<Integer> pictureList=new ArrayList<Integer>();     //store the picture id
		int[] pictureIDArray={};

		//query the picture id according to the ID
		sql="select picture_id from user_picture where ID=" + id +";";
		rs=super.exeSqlQuery(sql);
		try {
			while(rs.next()){           //select the nest row
				pictureList.add(1);     //add rows to the list
			}
			
			//create the array
			if(pictureList!=null&&pictureList.size()>0){
				pictureIDArray=new int[pictureList.size()];
				
			     //assign the value to the array
				for(int i=0;i<pictureList.size();++i){
					pictureIDArray[i]=pictureList.get(i);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pictureIDArray;
		
	}

	
	/** get the picture path according to the picture id */
	public String getPicturePathFromDB(int pictureId){
		ResultSet rs=null;
		String path="";

		//query the information about the picture
		sql="select picture_path from user_picture where picture_id="+pictureId+";";
		rs=super.exeSqlQuery(sql);
		try {
			rs.next();
			path=rs.getString("picture_path");     //convert rs to String
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
		return path;
	}
	
	/** get the picture introduction according to the picture id */
	public String getIntroFromDB(int pictureId){
		ResultSet rs=null;
		String intro="";

		//query the information about the picture
		sql="select introduction from user_picture where ID="+pictureId+";";
		rs=super.exeSqlQuery(sql);
		try {
			rs.next();
			intro=rs.getString("introduction");     //convert rs to String
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
		return intro;
	}
	
	/** get the upload time of the picture according to the picture id */
	public String getTimeFromDB(int pictureId){
		ResultSet rs=null;
		String time="";

		//query the information about the picture
		sql="select time from user_picture where ID="+pictureId+";";
		rs=super.exeSqlQuery(sql);
		try {
			rs.next();
			time=rs.getString("time");     //convert rs to String
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}      
		return time;
	}
	
	
}
