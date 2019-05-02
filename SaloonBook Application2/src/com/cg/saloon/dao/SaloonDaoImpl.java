package com.cg.saloon.dao;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.cg.saloon.dto.Customer;
import com.cg.saloon.dto.Saloon;
import com.cg.saloon.exception.SaloonNotFoundException;
import com.cg.saloon.util.DBUtil;

public class SaloonDaoImpl implements SaloonDao{

	PreparedStatement pstm;
	Statement stmt;


	@Override
	public Saloon save(Saloon sal) {
		
		
		Connection conn = DBUtil.getConnection();
		
		
 
		
		try {
		
		
			pstm = conn.prepareStatement("INSERT INTO address (city,state,postalcode) VALUES (?,?,?)");
			 
			pstm.setString(1, sal.getAddress().getCity());
			pstm.setString(2, sal.getAddress().getState());
			pstm.setLong(3, sal.getAddress().getPostalcode());
			pstm.executeUpdate();
			
			
			 String sq="select max(address_id)from address";
			pstm=conn.prepareStatement(sq);
			ResultSet res=pstm.executeQuery();
			if(res.next())
			{
				  int address_id = res.getInt(1);
					pstm = conn.prepareStatement("INSERT INTO saloon (name,phonenumber,address_id) VALUES (?,?,?)");
					 
					pstm.setString(1, sal.getName());
					pstm.setString(2, sal.getPhoneNumber().toString());
					pstm.setInt(3, address_id);
					int n2=pstm.executeUpdate();
					int saloon_id=0;
					if(n2==1)
					{
					
					    String sq1="select max(saloon_id)from saloon";
					pstm=conn.prepareStatement(sq1);
					ResultSet rs=pstm.executeQuery();
					if(rs.next())
					{
						saloon_id=rs.getInt(1);
					}
					
						pstm = conn.prepareStatement("INSERT INTO customer(name,emailid,mobilenumber,saloon_id) VALUES (?,?,?,?)");
						
					//	pstm.setString(1, sal.getCustomerList())
						pstm.setString(1, sal.getCustomerList().get(0).getName());
						pstm.setString(2, sal.getCustomerList().get(0).getEmailId());
						pstm.setString(3, sal.getCustomerList().get(0).getMobileNumber().toString());
						pstm.setInt(4,saloon_id);
						pstm.executeUpdate();
				 
					}
			}
				
				
		 
				
			
             
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sal;
		

	}
		@Override
		public List<Saloon> findByName(String name) throws SaloonNotFoundException {
List<Saloon> myList = new ArrayList<>();
Connection conn=null;

try {
	conn=DBUtil.getConnection();
	ResultSet rs;
	pstm=conn.prepareStatement("select * from saloon where name=?");
	pstm.setString(1, name);
	
	
	rs=pstm.executeQuery();
	while(rs.next())
	{
		int sal_id=rs.getInt("saloon_id");
	    String sal_name=rs.getString("name");

	    Saloon sal=new Saloon();
	    sal.setId(sal_id);
	    sal.setName(sal_name);
	   
	    myList.add(sal);
	}
	
}catch (SQLException e) {
	throw new SaloonNotFoundException("saloon not found");
}finally {
	try {
		pstm.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

			return myList;
		}
		
}
		
		
		
		
		
		
		
		
		
		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//	
////		Connection conn=DBUtil.getConnection();
//		
//		/*try {
//			Connection connection = null;
//			pstm = connection.prepareStatement("INSERT INTO address (city,state,postalcode) VALUES (?,?,?)");
//			int addressId = 0;
//			
//				pstm.setString(1, sal.getAddress().getCity());
//				pstm.setString(2, sal.getAddress().getState());
//				pstm.setLong(3, sal.getAddress().getPostalcode());
//				pstm.executeUpdate();
//
//				pstm = connection.prepareStatement("select max(address_id) from address");
//				ResultSet result = pstm.executeQuery();
//				if (result.next()) {
//					addressId = result.getInt(1);
//				}			
//			int saloonId = 0;
//			try {
//				
//				pstm = connection.prepareStatement("INSERT INTO saloon(name,address_id) VALUES (?,?)");
//				pstm.setString(1,sal.getName());
//				pstm.setInt(2,addressId);
//				pstm.executeUpdate();
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (SQLException e) {
//
//		}
//		
//		return sal;
//		
//	}*/
//	
////		try {
//		// TODO Auto-generated method stub
////		PreparedStatement pstm = conn.prepareStatement("insert into address (city,state,postalcode)VALUES (?,?,?)");
//		//System.out.println(sal);
////		int addressId=0;
//		try {
//			pstm.setString(1,sal.getAddress().getCity());
//			pstm.setString(1,sal.getAddress().getState());
//			pstm.setLong(1,sal.getAddress().getPostalcode());
//			pstm.executeUpdate();
//			/*pstm=conn.prepareStatement("select max(id) from address");
//			ResultSet result=pstm.executeQuery();
//			if(result.next()) {
//				addressId=result.getInt(1);*/
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			
//		}
//		/*try (PreparedStatement pstm1=Connection.prepareStatement
//				("INSERT INTO saloon(saloon_id,saloon_name,saloon_address,saloon_phonenumber,address_id)VALUES(?,?,?,?,?)")){
//			pstm1.setInt(1, sal.getId());
//			pstm1.setString(2, sal.getString());
//			pstm1.setString(3, sal.getString());
//			pstm1.setString(4, sal.getPhoneNumber().toString());
//			pstm1.setInt(5, addressId());
//			pstm1.executeUpdate();
//		}
//		int saloonId=0;
//		try {
//			pstm=Connection.prepareStatement("select max(saloon_id) from saloon");
//		
//		ResultSet result=pstm.executeQuery();
//		if(result.next()) {
//			saloonId=result.getInt(1);
//		
//		}
//		pstm=Connection.prepareStatement
//				("INSERT INTO customer(customer_id,customer_name,customer_emailid,customer_mobilenumber,saloon_id)VALUES(?,?,?,?,?)");
//		for(Customer customers : saloon.getCustomer()) {
//			pstm.setInt(1, customers.getId());
//			pstm.setString(2, customers.getName());
//			pstm.setString(3, customers.getEmailId());
//			pstm.setString(4, customers.getMobileNumber().toString());
//			pstm.setInt(5, saloonId());
//			pstm.executeUpdate();
//		}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return conn;
//		
////			DBUtil.sal.add(sal);
////			DBUtil.sal.add(sal);
////			DBUtil.sal.add(sal);
////			DBUtil.sal.add(sal);
////		DBUtil.sal.add(sal);
////		 return sal;
//			*/
//	
//
//	@Override
//	public List<Saloon> findByName(String name) {
//		List<Saloon> saloonList = new ArrayList<>();
//		for (Saloon saloon :DBUtil.sal) {
//			if(saloon.getName().equals(name))
//			{
//				saloonList.add(saloon);
//		}
//		
//	}
//		return saloonList;
//	
//	}
//	private int saloonId() {
//	// TODO Auto-generated method stub
//	return 0;
//}
//	private int addressId() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//}
//
//

