package pdsj;

import java.io.*;
import java.sql.*;

public class Driver {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/pds";
	static final String USER = "root";
	static final String PASS = "";
	public static void main(String args[]) throws NumberFormatException, IOException {
		Connection conn = null;
		Statement stmt = null;
		try{
		      
		      Class.forName("com.mysql.cj.jdbc.Driver");
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      System.out.println("Creating table in given database...");
		      stmt = conn.createStatement();
		      
		      String sql = "CREATE TABLE pizza " +
		                   "(id INTEGER not NULL, " +
		                   " name VARCHAR(255), " + 
		                   "price INTEGER not NULL,"+
		                   " type VARCHAR(255), " + 
		                   " size VARCHAR(255), " + 
		                   " base VARCHAR(255), " +
		                   " PRIMARY KEY ( id ))"; 

		      stmt.executeUpdate(sql);
		      
		      System.out.println("Created table pizza in given database...");
		      sql = "CREATE TABLE order1 " +
	                   "(oid INTEGER not NULL, " +
	                   "pid VARCHAR(255) not NULL, " +
	                   " name VARCHAR(255), " + 
	                   " type VARCHAR(255), " + 
	                   " size VARCHAR(255), " + 
	                   " base VARCHAR(255), " +
	                   "amount INTEGER, " +
	                   " status VARCHAR(255), " +
	                   " PRIMARY KEY ( oid ))"; 

	      stmt.executeUpdate(sql);
	      System.out.println("Created table order in given database...");
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String sql1;
			ResultSet rs;
			int ch,sch,cch;
			int pId=0,oId=0;

			do {
				System.out.println("Welcome to Pizza King");
				System.out.println("\n\n Press - \n 1. Shopkeeper\n2.Customer\n 0. Exit\n");
				ch= Integer.parseInt(br.readLine());
				switch(ch) {
				case 1:
					do {
						String name,type,size,base;
						int price;
						System.out.println("\nWelcome\n\n 1. Add Pizza \n2. View Order \n3. Exit\n");
						sch= Integer.parseInt(br.readLine());
						if(sch==1) {
							
							System.out.println("enter pizza name : ");
							name=br.readLine();
							System.out.println("enter pizza price : ");
							price=Integer.parseInt(br.readLine());
							System.out.println("enter pizza type : ");
							type=br.readLine();
							System.out.println("enter pizza size : ");
							size=br.readLine();
							System.out.println("enter pizza base : ");
							base=br.readLine();
							
							
							sql1= "INSERT INTO pizza (id,name,price,type,size,base)" +
					                   "VALUES ('"+pId+"','"+name+"','"+price+"','"+type+"','"+size+"','"+base+"')";
							stmt.executeUpdate(sql1);
							pId++;
							System.out.println("\n\n Pizza added successfully to menu\n\n Your current menu is \n\n Pizza Id\tPizza \t\tPrice\t\tType\t\tSize\t\tBase\n");
							sql1="SELECT * from pizza";
							rs=stmt.executeQuery(sql1);
							while(rs.next()) {
								System.out.println(rs.getInt("id")+"\t\t"+rs.getString("name")+"\t\t"+rs.getInt("price")+"\t\t"+rs.getString("type")+"\t\t"+rs.getString("size")+"\t\t"+rs.getString("base")+"\n");
							}
							
						}
						if(sch==2) {
							System.out.println("the orders are :\n");
							System.out.println("Order Id\tPizza Id\tPizza name\tPizza Type\tPizza size\tPizza Base\tTotal Amount\n");
							sql1="SELECT * from order1";
							rs=stmt.executeQuery(sql1);
							while(rs.next()) {
								System.out.println(rs.getInt("oid")+"\t\t"+rs.getString("pid")+"\t\t"+rs.getString("name")+"\t\t"+rs.getString("type")+"\t\t"+rs.getString("size")+"\t\t"+rs.getString("base")+"\t\t"+rs.getInt("amount")+"\t\t"+rs.getString("status")+"\n");
							}
							
						}
					}while(sch!=3);
					break;
				case 2:
					
					do {
						int ach=1;
						int nch;
						int k=0;
						String nId="",nName="",nType="",nSize="",nBase="";
						double nPrice=0.0;
						System.out.println("\nWelcome to Pizza King\n\n");
						System.out.println("1. Place an order\n2.View Order\n3.Cancel Order\n4.Order Received\n 5. Exit\n\n Please select an appropriate option:\n");
						cch=Integer.parseInt(br.readLine());
						if(cch==1) {
							while(ach==1) {
								
								System.out.println("our menu is :\n Pizza Id\t\tPizza \t\tPrice\t\tType\t\tSize\t\tBase\n\n");
								sql1="SELECT * from pizza";
								rs= stmt.executeQuery(sql1);
								while(rs.next()) {
									System.out.println(rs.getInt("id")+"\t\t"+rs.getString("name")+"\t\t"+rs.getInt("price")+"\t\t"+rs.getString("type")+"\t\t"+rs.getString("size")+"\t\t"+rs.getString("base")+"\n");
								}
								System.out.println("\n Please select the pizza id to order:\n");
								nch=Integer.parseInt(br.readLine());
								
								if(nch>=pId) {
									System.out.println("wrong choice\n");
								}
								else {
									sql1="SELECT * from pizza where id='"+nch+"'";
									
									rs=stmt.executeQuery(sql1);
									
									if(k==0) {
										while(rs.next()) {
											nName=rs.getString("name");
											
											nId=Integer.toString(rs.getInt("id"));
											nType=rs.getString("type");
											
											System.out.println("what size do you want -> regular (r), medium(m),large(l):\n");
											nSize=br.readLine();
											System.out.println("What base do you want -> regular (r), pan(p)\n");
											nBase=br.readLine();
											if(nSize.equals("r")) {
												nPrice=rs.getInt("price");
											}
											else if(nSize.equals("m")) {
												nPrice=rs.getInt("price")+100;
											}
											else if(nSize.equals("l")){
												nPrice=rs.getInt("price")+200;
											}
											if(nBase.equals("p")) {
												nPrice+=100;
											}
											

										}
																				
									}
									else {
										String s,b;
										while(rs.next()) {
											nName+=","+rs.getString("name");
											nId+=","+rs.getInt("id");
											nType+=","+rs.getString("type");
											System.out.println("what size do you want -> regular (r), medium(m),large(l):\n");
											s=br.readLine();
											nSize+=","+s;
											System.out.println("What base do you want -> regular (r), pan(p)\n");
											b=br.readLine();
											nBase+=","+b;
											if(s.equals("r")) {
												nPrice+=rs.getInt("price");
											}
											else if(s.equals("m")) {
												nPrice+=rs.getInt("price")+100;
											}
											else {
												nPrice+=rs.getInt("price")+200;
											}
											if(b.equals("p")) {
												nPrice+=100;
											}
											
										}
										
									}
									
									
									
								}
								k++;
								System.out.println("do you want to add more pizza\n1. Yes\n2.No\n");
								ach=Integer.parseInt(br.readLine());
							}
							sql1="INSERT INTO order1 "+
									"VALUES ('"+oId+"','"+nId+"','"+nName+"','"+nType+"','"+nSize+"','"+nBase+"','"+nPrice+"','not delivered')";
							stmt.executeUpdate(sql1);
							
							System.out.println("Your order is :\n");
							System.out.println("Order Id\tPizza Id\tPizza name\tPizza Type\tPizza size\tPizza Base\tTotal Amount\tStatus\n");
							System.out.println(oId+"\t\t"+nId+"\t\t"+nName+"\t\t"+nType+"\t\t"+nSize+"\t\t"+nBase+"\t\t"+nPrice+"\t Not delivered\n");
							
							oId++;
						}
						else if(cch==2) {
							
							int id,flag=0;
							System.out.println("Please provide your order id:\n");
							id=Integer.parseInt(br.readLine());
							sql1="SELECT * FROM order1 WHERE oid='"+id+"'";
							rs=stmt.executeQuery(sql1);
							if(rs!=null) {
								while(rs.next()) {
									System.out.println("Your order is :\n");
									System.out.println("Order Id\tPizza Id\tPizza name\tPizza Type\tPizza size\tPizza Base\tTotal Amount\tStatus\n");
									System.out.println(rs.getInt("oid")+"\t\t"+rs.getString("pid")+"\t\t"+rs.getString("name")+"\t\t"+rs.getString("type")+"\t\t"+rs.getString("size")+"\t\t"+rs.getString("base")+"\t\t"+rs.getInt("amount")+"\t\t"+rs.getString("status")+"\n");
								}
								flag=1;
							}
							
							if(flag==0) {
								System.out.println("wrong order id\n");
							}
							
						}
						else if(cch==3) {
							int id,flag=0;
							
							System.out.println("Please provide your order id:\n");
							id=Integer.parseInt(br.readLine());
							sql1="SELECT * FROM order1";
							rs=stmt.executeQuery(sql1);
							while(rs.next()) {
								if(rs.getInt("oid")==id) {
									flag=1;
									break;
								}
							}
							if(flag==1) {
								sql1="DELETE FROM order1 WHERE oid='"+id+"'";
								stmt.executeUpdate(sql1);
							}
							
							if(flag==0) {
								System.out.println("wrong order id\n");
							}
						}
						else if(cch==4) {
							int id,flag=0;
							
							System.out.println("Please provide your order id:\n");
							id=Integer.parseInt(br.readLine());
							sql1="SELECT * FROM order1";
							rs=stmt.executeQuery(sql1);
							while(rs.next()) {
								if(rs.getInt("oid")==id) {
									
									flag=1;
									break;
								}
							}
							if(flag==1) {
								String stat="delivered";
								sql1= "UPDATE order1 SET status='"+stat+"' WHERE oid='"+id+"'";
								stmt.executeUpdate(sql1);
								
								System.out.println("your order has been delivered.Enjoy your pizza\n\n");
							}
							
							if(flag==0) {
								System.out.println("wrong order id\n");
							}
						}
						
					}while(cch!=5);
					break;
				}
			}while(ch!=0);
		   }catch(SQLException se){
			     
			      se.printStackTrace();
			   }catch(Exception e){
			      
			      e.printStackTrace();
			   }
	
		
	}

}
