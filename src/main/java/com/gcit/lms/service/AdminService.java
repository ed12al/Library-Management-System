package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
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
	
	public List<Author> readAllAuthors() throws SQLException {
		return adao.readAllAuthors();
	}
	
	public Integer getAuthorsCount(String q) throws SQLException {
		return adao.getAuthorsCount(q);
	}
	
	public Author getAuthorWithDetailById(Integer authorId) throws SQLException {
		Author author = getAuthorById(authorId);
		author.setBooks(bdao.readAllBooksByAuthor(author));
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
	public void deleteAuthor(Integer authorId) throws SQLException {
		Author author = new Author();
		author.setAuthorId(authorId);
		adao.deleteAuthor(author);
	}

	@Transactional
	public Integer addAuthor(String authorName) throws SQLException {
		Author author = new Author();
		author.setAuthorName(authorName.trim().length() == 0 ? null: authorName);
		return adao.addAuthorWithID(author);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * admin: publisher * * * * * * * * * * * * * * * * * * * * * * * */
	public List<Publisher> readAllPublishersWithPageNo(Integer pageNo, String q) throws SQLException {
		return pdao.readAllPublishersWithPageNo(pageNo, Constants.pageSize, q);
	}
	
	public List<Publisher> readAllPublishers() throws SQLException {
		return pdao.readAllPublishers();
	}
	
	public Integer getPublishersCount(String q) throws SQLException {
		return pdao.getPublishersCount(q);
	}
	
	public Publisher getPublisherWithDetailById(Integer publisherId) throws SQLException {
		Publisher publisher = getPublisherById(publisherId);
		publisher.setBooks(bdao.readAllBooksByPublisher(publisher));
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
	public void deletePublisher(Integer publisherId) throws SQLException {
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		pdao.deletePublisher(publisher);
	}

	@Transactional
	public Integer addPublisher(String publisherName, String publisherAddress, String publisherPhone) throws SQLException {
		Publisher publisher = new Publisher();
		publisher.setPublisherName(publisherName.trim().length() == 0 ? null: publisherName);
		publisher.setPublisherAddress(publisherAddress.trim().length() == 0 ? null : publisherAddress);
		publisher.setPublisherPhone(publisherPhone.trim().length() == 0 ? null : publisherPhone);
		return pdao.addPublisherWithID(publisher);
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
		branch.setBookCopy(bcdao.readAllBookCopiesByBranch(branch));
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
	public void deleteBranch(Integer branchId) throws SQLException {
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
		borrower.setLoans(ldao.readAllLoansByBorrower(borrower));
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
	public void deleteBorrower(Integer cardNo) throws SQLException {
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
	
	/* * * * * * * * * * * * * * * * * * * * * * * * admin: loan * * * * * * * * * * * * * * * * * * * * * * * */
	public List<Loan> readAllLoansWithPageNo(Integer pageNo, String q, Boolean seeAll) throws SQLException {
		return ldao.readAllLoansWithPageNo(pageNo, Constants.pageSize, q, seeAll);
	}
	
	public Integer getLoansCount(String q, Boolean seeAll) throws SQLException {
		return ldao.getLoansCount(q, seeAll);
	}
	
	@Transactional
	public void updateDueDateWithDiff(Integer loanId, Integer diff) throws SQLException {
		Loan loan = new Loan();
		loan.setLoanId(loanId);
		loan = ldao.readLoanById(loan);
		long dueDateTime = loan.getDueDate().getTime() + diff * Constants.ONE_DAY;
		long dateOut = loan.getDateOut().getTime();
		if(dueDateTime < dateOut){
			throw new NullPointerException("dueDate cannot be before dateOut");
		}
		loan.setDueDate(new Date(dueDateTime));
		ldao.updateDueDate(loan);
	}

	@Transactional
	public void deleteLoan(Integer loanId) throws SQLException {
		Loan loan = new Loan();
		loan.setLoanId(loanId);
		ldao.deleteLoan(loan);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * admin: book * * * * * * * * * * * * * * * * * * * * * * * */
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
		book = bdao.readBookById(book);
		return book;
	}
	
	@Transactional
	public void updateBookWithDetails(Integer bookId, String title, Integer publisherId, Integer[] authorIds, Integer[] genreIds) throws SQLException {
		Book book = new Book();
		book.setBookId(bookId);
		book.setTitle(title.trim().length() == 0 ? null: title);
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		book.setPublisher(publisherId == null ? null: publisher);
		if(authorIds != null) {
			List<Author> authors = new ArrayList<>();
			for(Integer id : authorIds){
				Author author = new Author();
				author.setAuthorId(id);
				authors.add(author);
			}
			book.setAuthors(authors);
		}
		if(genreIds != null){
			List<Genre> genres = new ArrayList<>();
			for(Integer id : genreIds){
				Genre genre = new Genre();
				genre.setGenreId(id);
				genres.add(genre);
			}
			book.setGenres(genres);
		}
		bdao.updateBookWithDetails(book);
	}

	@Transactional
	public void deleteBook(Integer bookId) throws SQLException {
		Book book = new Book();
		book.setBookId(bookId);
		bdao.deleteBook(book);
	}

	@Transactional
	public void addBookWithDetails(String title, Integer publisherId, Integer[] authorIds, Integer[] genreIds) throws SQLException {
		Book book = new Book();
		book.setTitle(title.trim().length() == 0 ? null: title);
		Publisher publisher = new Publisher();
		publisher.setPublisherId(publisherId);
		book.setPublisher(publisherId == null ? null: publisher);
		if(authorIds != null) {
			List<Author> authors = new ArrayList<>();
			for(Integer id : authorIds){
				Author author = new Author();
				author.setAuthorId(id);
				authors.add(author);
			}
			book.setAuthors(authors);
		}
		if(genreIds != null){
			List<Genre> genres = new ArrayList<>();
			for(Integer id : genreIds){
				Genre genre = new Genre();
				genre.setGenreId(id);
				genres.add(genre);
			}
			book.setGenres(genres);
		}
		bdao.addBookWithDetails(book);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * admin: book * * * * * * * * * * * * * * * * * * * * * * * */
	public List<Genre> readAllGenres() throws SQLException {
		return gdao.readAllGenres();
	}
	
	@Transactional
	public Integer addGenre(String genreName) throws SQLException {
		Genre genre = new Genre();
		genre.setGenreName(genreName.trim().length() == 0 ? null: genreName);
		return gdao.addGenreWithID(genre);
	}
}
