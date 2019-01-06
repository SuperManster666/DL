package com.dl.textbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class textbook {
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection conn = null;//连接数据库
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DL", "debian-sys-maint", "RfeYWvi4FejdqXRn");
		Statement stat = null;
		stat = conn.createStatement();
		PreparedStatement stmt;
		String sql = "select * from textbook";
		ResultSet rs=stat.executeQuery(sql);//查询
		while(rs.next()) {
			String bookid = rs.getString("bookid");
			String bookname = rs.getString("bookname");
			String booknumber = rs.getString("booknumber");
			System.out.println(bookid);
			System.out.println(bookname);
			System.out.println(booknumber);
		}
		rs.close();
		stat.close();
		conn.close();
	}
}
