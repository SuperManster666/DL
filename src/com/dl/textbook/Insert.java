package com.dl.textbook; 
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	 
	
	public class Insert {
		public static void main(String[] arg) throws SQLException, ClassNotFoundException{
			
			PreparedStatement stmt;
			Connection conn = null;//连接数据库
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DL", "debian-sys-maint", "RfeYWvi4FejdqXRn");
			String sql = "insert into textbook values (?,?,?)";
			try {
				
				stmt = (PreparedStatement) conn.prepareStatement(sql);
				stmt.setInt(1, 50);
				stmt.setString(2, "wuli");
				stmt.setInt(3, 60);
				stmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}catch (ClassCastException e) {
				// TODO: handle exception
			}
			System.out.println("successful");
		}
	 
	}

