package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.common.Constants;

public class AdminService {
	@Autowired
	AuthorDAO adao;

	@Autowired
	BookDAO bdao;
	
/* * * * * * * * * * * * * * * * * * * * * * * * admin: author * * * * * * * * * * * * * * * * * * * * * * * */
	public List<Author> readAllAuthorsWithPageNo(Integer pageNo, String q) throws SQLException {
		return adao.readAllAuthorsWithPageNo(pageNo, Constants.pageSize, q);
	}
	
	public Integer getAuthorsCount(String q) throws SQLException {
		return adao.getAuthorsCount(q);
	}
	
	public Author getAuthorWithDetailById(Integer authorId) throws SQLException {
		Author author = getAuthorById(authorId);
		List<Book> books = bdao.readAllBooksByAuthor(author);
		author.setBooks(books);
		return author;
	}
	
	public Author getAuthorById(Integer authorId) throws SQLException {
		Author author = new Author();
		author.setAuthorId(authorId);
		author = adao.readAuthorById(author);
		return author;
	}
	
	@Transactional
	public void updateAuthor(Integer authorId, String authorName) throws SQLException {
		Author author = new Author();
		author.setAuthorId(authorId);
		author.setAuthorName(authorName.length() == 0 ? null: authorName);
		adao.updateAuthor(author);
	}

	@Transactional
	public void deleteAuthor(int authorId) throws SQLException {
		Author author = new Author();
		author.setAuthorId(authorId);
		adao.deleteAuthor(author);
	}

	@Transactional
	public void addAuthor(String authorName) throws SQLException {
		Author author = new Author();
		author.setAuthorName(authorName);
		adao.addAuthor(author);
	}
}
