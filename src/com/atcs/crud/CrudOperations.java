package com.atcs.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class CrudOperations {

	public static void main(String[] args)throws Exception {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Press 1 for insert\nPress 2 for update\nPress 3 for Delete\nPress 4 for display all\nPress 5 for display using id\nPress 6 to exit");
		int in=sc.nextInt();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb", "root", "root");
		
		while(in!=6) {
			if(in==1)
				insert(sc,conn);
			if(in==2)
				update(sc,conn);
			if(in==3)
				delete(sc,conn);
			if(in==4)
				displayAll(sc,conn);
			if(in==5)
				displayUsingId(sc,conn);
			if(in==6)
				break;
			System.out.println("Press 1 for insert\nPress 2 for update\nPress 3 for Delete\nPress 4 for display all\nPress 5 for display using id\nPress 6 to exit");

			in=sc.nextInt();
			
		}
		
		
		
	}

	private static void displayUsingId(Scanner sc,Connection conn) throws Exception {
		System.out.println("Enter id to show details");
		String query="select * from people where id ="+sc.nextInt();
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		while(rs.next()) {
			System.out.println("First "+rs.getString(1)+" Last "+rs.getString(2)+" Age "+rs.getInt(3)+" City "+rs.getString(4)+" ID  "+rs.getInt(5));

		}
	}

	private static void displayAll(Scanner sc,Connection conn) throws Exception {
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("select * from people");
		System.out.println("First   "+"   Last   "+" Age "+"   City   "+"   ID   ");
		while(rs.next()) {
			System.out.println(rs.getString(1)+"      "+rs.getString(2)+"    "+rs.getInt(3)+"    "+rs.getString(4)+"      "+rs.getInt(5));
		}
		
		
	}

	private static void delete(Scanner sc,Connection conn) throws Exception {
		// TODO Auto-generated method stub
		String query="delete from people where id=?";
		PreparedStatement ps=conn.prepareStatement(query);
		System.out.println("Enter id to delete");
		ps.setInt(1, sc.nextInt());
		
		ps.executeUpdate();
		
	}

	private static void update(Scanner sc,Connection conn) throws Exception {
		sc.nextLine();
		System.out.println("What you want to update?");
		String setWhat=sc.nextLine();
		System.out.println("Set Column for condition");
		String conditionWhat=sc.nextLine();
		String query="update people set "+setWhat+"=? where "+conditionWhat+"=?";
		PreparedStatement ps=conn.prepareStatement(query);
		
		System.out.println("Enter new Value for "+setWhat);
		ps.setString(1, sc.nextLine());
		System.out.println("Where ? Enter "+conditionWhat+" of the row");
		ps.setString(2, sc.nextLine());
		
		ps.executeUpdate();
	
	}

	private static void insert(Scanner sc,Connection conn) throws Exception {
		String insert="insert into people values(?,?,?,?,?)";
		PreparedStatement ps =conn.prepareStatement(insert);
		System.out.println("Enter values First,Last,Age,City,ID");
		sc.nextLine();
		String First=sc.nextLine();
		String Last=sc.nextLine();
		String Age=sc.nextLine();
		String City=sc.nextLine();
		String ID=sc.nextLine();
		
		ps.setString(1, First);
		ps.setString(2, Last);
		ps.setInt(3, Integer.parseInt(Age));
		ps.setString(4, City);
		ps.setInt(5, Integer.parseInt(ID));
		
		ps.executeUpdate();
	}
}
