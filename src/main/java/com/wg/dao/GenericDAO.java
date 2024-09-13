package com.wg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wg.config.DatabaseConfig;

public abstract class GenericDAO<T, ID> {
	
	protected abstract String getTableName();
	protected abstract T mapResultSetToEntity(ResultSet result) throws SQLException;
    protected abstract void setPreparedStatementForEntity(PreparedStatement stmt, T entity) throws SQLException;
    protected abstract String getPrimaryKeyColumn();
    protected abstract void setPreparedStatementForPrimaryKey(PreparedStatement stmt, ID id) throws SQLException;
	protected abstract String getPlaceholders();
    
	Connection connection;
	
	public GenericDAO () {
		try {
			connection = DatabaseConfig.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    // Add User
    public void add(T entity) throws SQLException {
    	String INSERT_QUERY = String.format("INSERT INTO %s VALUES (%s)", getTableName(), getPlaceholders());
    	try(PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY)) {
    		setPreparedStatementForEntity(stmt, entity);
    		stmt.executeUpdate();
    	}
    }

    // Get By ID
    public List<T> getById(ID id) throws SQLException {
    	String SELECT_QUERY = "SELECT * FROM " + getTableName() + " WHERE " + getPrimaryKeyColumn() + " = ?";
    	List<T> entities = new ArrayList<>();
    	
    	try (PreparedStatement stmt = connection.prepareStatement(SELECT_QUERY)) {
    		setPreparedStatementForPrimaryKey(stmt, id);
    		try (ResultSet result = stmt.executeQuery()) {
    			while(result.next()) {
    				entities.add(mapResultSetToEntity(result));
    			}
    		}
    	}
    	return entities;
    }
     
    // Get All
    public List<T> getAll() throws SQLException {
    	String SELECT_QUERY = "SELECT * FROM " + getTableName();
    	List<T> entities = new ArrayList<>();
    	try (PreparedStatement stmt = connection.prepareStatement(SELECT_QUERY);
    			ResultSet result = stmt.executeQuery()) {
    		while(result.next()) {
    			entities.add(mapResultSetToEntity(result));
    		}
    	}
    	return entities;
    }
    
    // Update
//    public void update(T entity, ID id) throws SQLException {
//        String UPDATE_QUERY = "UPDATE " + getTableName() + " SET column1 = ?, column2 = ?, column3 = ? WHERE " + getPrimaryKeyColumn() + " = ?";
//        try (Connection connection = DatabaseConfig.getConnection();
//        		PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {
//            setPreparedStatementForEntity(stmt, entity);
//            setPreparedStatementForPrimaryKey(stmt, id);
//            stmt.executeUpdate();
//        }
//    }
    
    // Delete
    public void delete(ID id) throws SQLException {
        String DELETE_UPDATE = "DELETE FROM " + getTableName() + " WHERE " + getPrimaryKeyColumn() + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_UPDATE)) {
            setPreparedStatementForPrimaryKey(stmt, id);
            stmt.executeUpdate();
        }
    }
}
