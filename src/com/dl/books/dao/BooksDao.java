package com.dl.books.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dl.pro.Book;
import com.dl.pro.BookBean;
import com.google.gson.Gson;



//import top.gendseo.books.pojo.*;

public class BooksDao {
	
	private static Gson gson = new Gson();
	private static Connection connection = null;
	
	private static void getConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DL", "debian-sys-maint", "RfeYWvi4FejdqXRn");
	}
	
	private static void getClose(Connection connection, Statement st, PreparedStatement ps, ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (st != null) {
			st.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		System.out.println(Query());
	}
	
	public static String Query() throws ClassNotFoundException, SQLException {
		PreparedStatement ps = null;
		/*
		 * n 本书的集合列表 booksList
		 * 声明 Book 的 POJO 实体类
		 * 实际参见 top.gendseo.books.pojo.Books
		 */
		List<Book> booksList = new ArrayList<>();
		getConnection();
		
		String sql = "SELECT * FROM textbook;";
		System.out.println(sql);
		ps = connection.prepareStatement(sql);
		// 获得查询出来的结果集合
		ResultSet rs = ps.executeQuery();
		// 如果结果集合不为空 do while
		while (rs.next()) {
			// 声明一本书的类，并且往里添加数据，一一对应
		Book book = new Book();
		book.setBookid(rs.getInt("bookid"));
		book.setBookname(rs.getString("bookname"));
		book.setBooknumber(rs.getInt("booknumber"));
			// 最后把这本书添加到书的集合列表 booksList
//			booksList.add(book);
			System.out.println(book.getBookid()+" "+book.getBookname()
			+" "+book.getBooknumber()+" ");
		}
		/*
		 * n 本书的集合列表 BooksBean
		 * 声明 BooksBean 的 POJO 实体类
		 * 实际参见 top.gendseo.books.pojo.BooksBean
		 */
		BookBean bookBean = new BookBean();
		// 图书的列表
		bookBean.setRows(booksList);
		// 图书的总数
		bookBean.setTotal(String.valueOf(booksList.size()));
		
		getClose(connection, null, ps, rs);
		return gson.toJson(bookBean);
	}
	public static String DELETE(String name) throws ClassNotFoundException, SQLException {
		Statement st = null;
		getConnection();
		
		st = connection.createStatement();
		String sql = "DELETE FROM textbook WHERE \"name\" in (" + name + ");";
		System.out.println(sql);
		// executeUpdate 不同于 executeQuery
		// executeUpdate 执行更新操作，不返回任何结果
		st.executeUpdate(sql);
		
		getClose(connection, st, null, null);
		return "true";
	}
	public static String UPDATE(String json) throws ClassNotFoundException, SQLException {
		PreparedStatement ps = null;
		getConnection();
		
		// 使用 Gson 将 JSON 转换成 POJO 实体类 Book
		Book book = gson.fromJson(json, Book.class);
		String sql = "UPDATE textbook SET \"bookid\" = ?,\"bookname\" = ?,\"booknumber\" = ?;";
		System.out.println(sql);
		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, book.getBookid());
		ps.setString(2, book.getBookname());
		ps.setInt(3, book.getBooknumber());
		ps.executeUpdate();
		
		getClose(connection, null, ps, null);
		return "true";
	}
	public static String INSERT(String json) throws ClassNotFoundException, SQLException {
		PreparedStatement ps = null;
		getConnection();

		// 使用 Gson 将 JSON 转换成 POJO 实体类 Book
		Book book = gson.fromJson(json, Book.class);
		String sql = "INSERT INTO textbook (\"bookid\", \"bookname\", \"booknumber\") VALUES (?, ?, ?);";
		 System.out.println(sql);

			
		ps = connection.prepareStatement(sql);
		ps.setInt(1, book.getBookid());
		ps.setString(2, book.getBookname());
		ps.setInt(3, book.getBooknumber());
		ps.executeUpdate();
		
		getClose(connection, null, ps, null);
		return "true";
	}
}
