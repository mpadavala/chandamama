package com.sample.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sample.store.pojo.Item;
import com.sample.store.pojo.Price;

@Repository
public class ItemDao {

	private String INSERT_ITEM = "INSERT INTO ITEM (TITLE, DESCRIPTION, CATEGORY_ID, PRICE_ID, "
			+ "ITEM_STATE, COLORS, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE) VALUES (?, ?, ?, ?, ?, ? ,?, ?, ?, ?)";
	
	private String DELETE_ITEM = "DELETE FROM ITEM WHERE ID= ?";
	
	private String SELECT_ITEM_BY_ID = "SELECT * FROM ITEM WHERE ID = ?";
	
	private String SELECT_ALL_ITEMS = "SELECT * FROM ITEM";

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void add(Item item) {

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(INSERT_ITEM, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, item.getTitle());
			ps.setString(2, item.getDescription());
			ps.setLong(3, 123);
			ps.setDouble(4, item.getPrice().getPrice());
			ps.setString(5, item.getItemSate().getItemStateCode());
			ps.setLong(6, 2345);
			ps.setString(7, "Murali");
			ps.setDate(8, new java.sql.Date(new Date().getTime()));
			ps.setString(9, "Murali");
			ps.setDate(10, new java.sql.Date(new Date().getTime()));
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()){
	            item.setItemId(rs.getInt(1));
	        }
	        
			ps.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	public List<Item> getItems() {
		
		List<Item> itemsList = new ArrayList<>();

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			Statement statement = conn.createStatement();

			ResultSet rs = statement.executeQuery(SELECT_ALL_ITEMS);
			while (rs.next()) {
				Item item = new Item();
				item.setItemId(rs.getLong("ID"));
				item.setTitle(rs.getString("TITLE"));
				item.setDescription(rs.getString("DESCRIPTION"));
				//item.setcrs.getInt("CATEGORY_ID"));
				item.setPrice(new Price(rs.getDouble("PRICE_ID")));
				//item.setItemSate(rs.getString("ITEM_STATE"));
				//rs.getInt("COLORS"));
				//rs.getString("CREATED_BY"));
				//rs.getDate("CREATED_DATE"));
				//rs.getString("MODIFIED_BY"));
				//rs.getDate("MODIFIED_DATE"));
				
				itemsList.add(item);
			}
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return itemsList;
	}

	
	public int delete(long itemId) {

		Connection conn = null;
		int rowsDeleted = -1;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(DELETE_ITEM);
			ps.setLong(1, itemId);
			rowsDeleted = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		
		return rowsDeleted;
	}

	
	
	public Date checkConnectivity() {
		Date date = null;
		try {
			Connection conn = dataSource.getConnection();
			Statement statement = null;
			if (conn != null) {
				System.out.println("Connection established");
				try {
					statement = conn.createStatement();
					ResultSet r = statement.executeQuery("Select sysdate() from dual");
					while (r.next()){
						date = r.getDate(1);
						System.out.println(r.getDate(1));
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
			}

			if (statement != null) {
				statement.close();
			}
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
}
