package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.common.Constants;
import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LoanDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Loan;

public class BorrowerService {
	
	@Autowired
	AuthorDAO adao;

	@Autowired
	BookDAO bdao;
	
	@Autowired
	BorrowerDAO brdao;
	
	@Autowired
	BookCopyDAO bcdao;
	
	@Autowired
	PublisherDAO pdao;
	
	@Autowired
	GenreDAO gdao;
	
	@Autowired
	LoanDAO ldao;
	
	public Borrower readBorrowerById(Integer cardNo) throws SQLException{
		Borrower borrower = new Borrower();
		borrower.setCardNo(cardNo);
		try{
			return brdao.readBorrowerById(borrower);
		}catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	
	public List<Book> readAllBooksWithPageNo(Integer pageNo, String q) throws SQLException {
		return bdao.readAllBooksWithPageNo(pageNo, Constants.pageSize, q);
	}
	
	public Integer getBooksCount(String q) throws SQLException {
		return bdao.getBooksCount(q);
	}
	
	public Book getBookWithDetailById(Integer bookId) throws SQLException {
		Book book = getBookById(bookId);
		book.setPublisher(pdao.readPublisherByBook(book));
		book.setAuthors(adao.readAllAuthorsByBook(book));
		book.setGenres(gdao.readAllGenresByBook(book));
		return book;
	}
	
	public Book getBookById(Integer bookId) throws SQLException {
		Book book = new Book();
		book.setBookId(bookId);
		return bdao.readBookById(book);
	}
	
	public List<BookCopy> getBookCopiesByBook(Integer bookId) throws SQLException {
		Book book = new Book();
		book.setBookId(bookId);
		return bcdao.readAllBookCopiesByBook(book);
	}
	
	@Transactional
	public void borrowBook(Integer branchId, Integer bookId, Integer cardNo) throws SQLException {
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		Book book = new Book();
		book.setBookId(bookId);
		Borrower borrower = new Borrower();
		borrower.setCardNo(cardNo);
		Loan loan = new Loan();
		loan.setBook(book);
		loan.setBorrower(borrower);
		loan.setBranch(branch);
		BookCopy bookCopy = new BookCopy();
		bookCopy.setBook(book);
		bookCopy.setBranch(branch);
		if(bcdao.deductOneBookCopy(bookCopy)){
			ldao.addLoan(loan);
		}
	}
	
	public Borrower getBorrowerWithDetailById(Integer cardNo) throws SQLException {
		Borrower borrower = getBorrowerById(cardNo);
		borrower.setLoans(ldao.readAllLoansByBorrower(borrower, Boolean.FALSE));
		return borrower;
	}
	
	public Borrower getBorrowerById(Integer cardNo) throws SQLException {
		Borrower borrower = new Borrower();
		borrower.setCardNo(cardNo);
		return brdao.readBorrowerById(borrower);
	}
	
	@Transactional
	public void returnLoanById(Integer loanId) throws SQLException {
		Loan loan = new Loan();
		loan.setLoanId(loanId);
		loan = ldao.readLoanById(loan);
		if(loan.getDateIn() == null){
			loan.setDateIn(new Date());
			ldao.returnBookByLoan(loan);
			BookCopy bookCopy = new BookCopy();
			bookCopy.setBook(loan.getBook());
			bookCopy.setBranch(loan.getBranch());
			bcdao.addOneBookCopy(bookCopy);
		}else{
			throw new NullPointerException("Have already returned this book");
		}
	}
}
