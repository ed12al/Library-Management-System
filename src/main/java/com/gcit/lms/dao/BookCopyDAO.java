package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Book;

public class BookCopyDAO extends BaseDAO implements RowMapper<BookCopy>{

	public void addBookCopy(BookCopy bookCopy) throws SQLException {
		template.update("insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?, ?, ?)", 
				new Object[] { bookCopy.getBook().getBookId(), bookCopy.getBranch().getBranchId(), bookCopy.getNoOfCopies()});
	}
	
	public void updateBookCopy(BookCopy bookCopy) throws SQLException {
		template.update("update tbl_book_copies set noOfCopies = ? where bookId = ? and branchId = ?",
				new Object[] { bookCopy.getNoOfCopies(), bookCopy.getBook().getBookId(), bookCopy.getBranch().getBranchId()});
	}

	public void deleteBookCopy(BookCopy bookCopy) throws SQLException {
		template.update("delete from tbl_book_copies where bookId = ? and branchId = ?", 
				new Object[] { bookCopy.getBook().getBookId(), bookCopy.getBranch().getBranchId() });
	}
	
	public BookCopy readBookCopyByIds(BookCopy bookCopy) throws SQLException{
		return template.queryForObject("select * from tbl_book_copies Left Join tbl_book using(bookId) Left Join tbl_library_branch using(branchId) where bookId = ? and branchId = ?", 
				new Object[]{bookCopy.getBook().getBookId(), bookCopy.getBranch().getBranchId()}, this);
	}

	public List<BookCopy> readAllBookCopiesByBook(Book book) throws SQLException{
		return template.query("select * from tbl_book_copies Left Join tbl_book using(bookId) Left Join tbl_library_branch using(branchId) where bookId = ?)",
				new Object[] { book.getBookId()}, this);
	}
	
	public List<BookCopy> readAllBookCopiesByBranch(Branch branch) throws SQLException{
		return template.query("select * from tbl_book_copies Left Join tbl_book using(bookId) Left Join tbl_library_branch using(branchId) where branchId = ?",
				new Object[] { branch.getBranchId()}, this);
	}

	@Override
	public BookCopy mapRow(ResultSet rs, int row) throws SQLException {
		BookCopy b = new BookCopy();
		Book book = new Book();
		book.setBookId(rs.getInt("bookId"));
		book.setTitle(rs.getString("title"));
		b.setBook(book);
		Branch branch = new Branch();
		branch.setBranchId(rs.getInt("branchId"));
		branch.setBranchName(rs.getString("branchName"));
		b.setBranch(branch);
		b.setNoOfCopies(rs.getInt("noOfCopies"));
		return b;
	}

}
