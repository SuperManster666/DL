package com.dl.textbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.sun.org.apache.bcel.internal.classfile.Code;

public class Update {
public static void main(String[] arg) throws SQLException, ClassNotFoundException{
		
		PreparedStatement stmt;
		Connection conn = null;//连接数据库
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DL", "debian-sys-maint", "RfeYWvi4FejdqXRn");
		
		
		Scanner input = new Scanner(System.in);
    	System.out.println("bookid:");
    	int bookid= input.nextInt();
    	System.out.println("input:");
    	String bookname= input.next();
    	String booknumber= input.next();
         String sql  ="update textbook set bookname = ? booknumber = ? where bookid= " +bookid;
        PreparedStatement pre = (PreparedStatement) conn.prepareStatement(sql);
        pre.setString(1, bookname);
        pre.setString(1, booknumber);
        int count = pre.executeUpdate();
        System.out.println(count+"change");
        pre.close();

	    System.out.println("successful");
	}
}
