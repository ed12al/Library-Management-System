package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.gcit.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO implements RowMapper<Publisher>{

	public void addPublisher(Publisher publisher) throws SQLException {
		template.update("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?, ?, ?)", 
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone()});
	}
	
	public void updatePublisher(Publisher publisher) throws SQLException {
		template.update("update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?",
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone(), publisher.getPublisherId() });
	}

	public void deletePublisher(Publisher publisher) throws SQLException {
		template.update("delete from tbl_publisher where publisherId = ?", 
				new Object[] { publisher.getPublisherId() });
	}

	public List<Publisher> readAllPublishers() throws SQLException {
		return template.query("select * from tbl_publisher", this);
	}
	
	public List<Publisher> readAllPublishersWithPageNo(Integer pageNo, Integer pageSize, String q) throws SQLException {
		if(q==null||q.trim().length()==0){
			return template.query("select * from tbl_publisher limit ? , ?", new Object[] { (pageNo-1)*pageSize, pageSize }, this);
		}else{
			q = "%"+q+"%";
			return template.query("select * from tbl_publisher where publisherName like ? limit ? , ?", new Object[]{q, (pageNo-1)*pageSize, pageSize }, this);
		}
	}
	
	public Integer getPublishersCount(String q) throws SQLException{
		if(q==null||q.trim().length()==0){
			return template.queryForObject("select count(*) AS COUNT from tbl_publisher", Integer.class);
		}else{
			q = "%"+q+"%";
			return template.queryForObject("select count(*) AS COUNT from tbl_publisher where publisherName like ?", new Object[]{q}, Integer.class);
		}
	}
	
	public Publisher readPublisherById(Publisher publisher) throws SQLException{
		if(publisher.getPublisherId() == 0){
			return null;
		}
		return template.queryForObject("select * from tbl_publisher where publisherId = ?", new Object[]{ publisher.getPublisherId() }, this);
	}

	@Override
	public Publisher mapRow(ResultSet rs, int row) throws SQLException {
		Publisher p = new Publisher();
		p.setPublisherId(rs.getInt("publisherId"));
		p.setPublisherAddress(rs.getString("publisherAddress"));
		p.setPublisherName(rs.getString("publisherName"));
		p.setPublisherPhone(rs.getString("publisherPhone"));
		return p;
	}
}
