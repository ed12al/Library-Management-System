package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.LoanDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Loan;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.common.Constants;

public class AdminService {
	@Autowired
	AuthorDAO adao;

	@Autowired
	BookDAO bdao;
	
	@Autowired
	PublisherDAO pdao;
	
	@Autowired
	BranchDAO bhdao;
	
	@Autowired
	BorrowerDAO brdao;
	
	@Autowired
	BookCopyDAO bcdao;
	
	@Autowired
	GenreDAO gdao;
	
	@Autowired
	LoanDAO ldao;
	
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
		author.setAuthorName(authorName.trim().length() == 0 ? null: authorName);
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
		author.setAuthorName(authorName.trim().length() == 0 ? null: authorName);
		adao.addAuthor(author);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * admin: publisher * * * * * * * * * * * * * * * * * * * * * * * */
	public List<Publisher> readAllPublishersWithPageNo(Integer pageNo, String q) throws SQLException {
		return pdao.readAllPublishersWithPageNo(pageNo, Constants.pageSize, q);
	}
	
	public Integer getPublishersCount(String q) throws SQLException {
		return pdao.getPublishersCount(q);
	}
	
	public Publisher getPublisherWithDetailById(Integer publisherId) throws SQLException {
		Publisher publisher = getPublisherById(publisherId);
		List<Book> books = bdao.readAllBooksByPublisher(publisher);
		publisher.setBooks(books);
		return publisher;
	}
	
	public Publisher getPublisherById(Integer publisherId) throws SQLException {
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		publisher = pdao.readPublisherById(publisher);
		return publisher;
	}
	
	@Transactional
	public void updatePublisher(Integer publisherId, String publisherName, String publisherAddress, String publisherPhone) throws SQLException {
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		publisher.setPublisherName(publisherName.trim().length() == 0 ? null: publisherName);
		publisher.setPublisherAddress(publisherAddress.trim().length() == 0 ? null : publisherAddress);
		publisher.setPublisherPhone(publisherPhone.trim().length() == 0 ? null : publisherPhone);
		pdao.updatePublisher(publisher);
	}

	@Transactional
	public void deletePublisher(int publisherId) throws SQLException {
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		pdao.deletePublisher(publisher);
	}

	@Transactional
	public void addPublisher(String publisherName, String publisherAddress, String publisherPhone) throws SQLException {
		Publisher publisher = new Publisher();
		publisher.setPublisherName(publisherName.trim().length() == 0 ? null: publisherName);
		publisher.setPublisherAddress(publisherAddress.trim().length() == 0 ? null : publisherAddress);
		publisher.setPublisherPhone(publisherPhone.trim().length() == 0 ? null : publisherPhone);
		pdao.addPublisher(publisher);
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * admin: branch * * * * * * * * * * * * * * * * * * * * * * * */
	public List<Branch> readAllBranchesWithPageNo(Integer pageNo, String q) throws SQLException {
		return bhdao.readAllBranchesWithPageNo(pageNo, Constants.pageSize, q);
	}
	
	public Integer getBranchesCount(String q) throws SQLException {
		return bhdao.getBranchesCount(q);
	}
	
	public Branch getBranchWithDetailById(Integer branchId) throws SQLException {
		Branch branch = getBranchById(branchId);
		List<BookCopy> bookCopy = bcdao.readAllBookCopiesByBranch(branch);
		branch.setBookCopy(bookCopy);
		return branch;
	}
	
	public Branch getBranchById(Integer branchId) throws SQLException {
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		branch = bhdao.readBranchById(branch);
		return branch;
	}
	
	@Transactional
	public void updateBranch(Integer branchId, String branchName, String branchAddress) throws SQLException {
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		branch.setBranchName(branchName.trim().length() == 0 ? null: branchName);
		branch.setBranchAddress(branchAddress.trim().length() == 0 ? null : branchAddress);
		bhdao.updateBranch(branch);
	}

	@Transactional
	public void deleteBranch(int branchId) throws SQLException {
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		bhdao.deleteBranch(branch);
	}

	@Transactional
	public void addBranch(String branchName, String branchAddress) throws SQLException {
		Branch branch = new Branch();
		branch.setBranchName(branchName.trim().length() == 0 ? null: branchName);
		branch.setBranchAddress(branchAddress.trim().length() == 0 ? null : branchAddress);
		bhdao.addBranch(branch);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * admin: borrower * * * * * * * * * * * * * * * * * * * * * * * */
	public List<Borrower> readAllBorrowersWithPageNo(Integer pageNo, String q) throws SQLException {
		return brdao.readAllBorrowersWithPageNo(pageNo, Constants.pageSize, q);
	}
	
	public Integer getBorrowersCount(String q) throws SQLException {
		return brdao.getBorrowersCount(q);
	}
	
	public Borrower getBorrowerWithDetailById(Integer cardNo) throws SQLException {
		Borrower borrower = getBorrowerById(cardNo);
		List<Loan> loans = ldao.readAllLoansByBorrower(borrower);
		borrower.setLoans(loans);
		return borrower;
	}
	
	public Borrower getBorrowerById(Integer cardNo) throws SQLException {
		Borrower borrower = new Borrower();
		borrower.setCardNo(cardNo);
		borrower = brdao.readBorrowerById(borrower);
		return borrower;
	}
	
	@Transactional
	public void updateBorrower(Integer cardNo, String borrowerName, String borrowerAddress, String borrowerPhone) throws SQLException {
		Borrower borrower = new Borrower();
		borrower.setCardNo(cardNo);
		borrower.setName(borrowerName.trim().length() == 0 ? null: borrowerName);
		borrower.setAddress(borrowerAddress.trim().length() == 0 ? null : borrowerAddress);
		borrower.setPhone(borrowerPhone.trim().length() == 0 ? null : borrowerPhone);
		brdao.updateBorrower(borrower);
	}

	@Transactional
	public void deleteBorrower(int cardNo) throws SQLException {
		Borrower borrower = new Borrower();
		borrower.setCardNo(cardNo);
		brdao.deleteBorrower(borrower);
	}

	@Transactional
	public void addBorrower(String borrowerName, String borrowerAddress, String borrowerPhone) throws SQLException {
		Borrower borrower = new Borrower();
		borrower.setName(borrowerName.trim().length() == 0 ? null: borrowerName);
		borrower.setAddress(borrowerAddress.trim().length() == 0 ? null : borrowerAddress);
		borrower.setPhone(borrowerPhone.trim().length() == 0 ? null : borrowerPhone);
		brdao.addBorrower(borrower);
	}
}
