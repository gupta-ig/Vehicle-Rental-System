package com.wg.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wg.model.Review;

public class ReviewDAO extends GenericDAO<Review, String>{

	@Override
	protected String getTableName() {
		return "REVIEW";
	}

	@Override
	protected Review mapResultSetToEntity(ResultSet result) throws SQLException {
		return new Review(
				result.getString("review_id"),
				result.getString("customer_id"),
				result.getTimestamp("creation_date"),
				result.getInt("rating"),
				result.getString("review_description"));
	}

	@Override
	protected void setPreparedStatementForEntity(PreparedStatement stmt, Review review) throws SQLException {
		stmt.setString(1, review.getReviewId());
		stmt.setString(2, review.getCustomerId());
		stmt.setTimestamp(3, review.getCreationDate());
		stmt.setInt(4, review.getRating());
		stmt.setString(5, review.getDesciption());
		
	}

	@Override
	protected String getPrimaryKeyColumn() {
		return "customer_id";
	}

	@Override
	protected void setPreparedStatementForPrimaryKey(PreparedStatement stmt, String reviewId) throws SQLException {
		stmt.setString(1, reviewId);
		
	}

	@Override
	protected String getPlaceholders() {
		return "?, ?, ?, ?, ?";
	}

}
