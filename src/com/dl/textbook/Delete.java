package com.dl.textbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
	public static void main(String[] arg) throws SQLException, ClassNotFoundException{
		
		PreparedStatement stmt;
		Connection conn = null;//连接数据库
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DL", "debian-sys-maint", "RfeYWvi4FejdqXRn");
		String sql = "delete from textbook where bookid = ?";
		PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
		ptmt.setInt(1, 50);
        ptmt.execute();
        System.out.println("successful");
	}
}
