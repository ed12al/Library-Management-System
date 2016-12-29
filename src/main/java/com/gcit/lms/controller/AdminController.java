package com.gcit.lms.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcit.lms.common.Constants;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Loan;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String admin(Model model) {
		logger.info("admin GET admin.jsp");
		return "admin";
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * admin: author * * * * * * * * * * * * * * * * * * * * * * * */
	@RequestMapping(value = "/authors", method = RequestMethod.GET)
	public String authors(Model model) {
		logger.info("admin GET author/authors.jsp");
		return "author/authors";
	}
	
	@RequestMapping(value = "/viewauthors", method = RequestMethod.GET)
	public String viewAuthors(Model model, @RequestParam(value="searchString", required=false) String q, @RequestParam("pageNo") Integer pageNo) throws SQLException {
		logger.info("admin GET author/viewauthors.jsp: q="+q+" pageNo="+pageNo);
		if(pageNo == null) pageNo = 1;
		List<Author> authors = adminService.readAllAuthorsWithPageNo(pageNo, q);
		Integer count = adminService.getAuthorsCount(q);
		Integer pages = (count + Constants.pageSize - 1)/Constants.pageSize;
		model.addAttribute("authors", authors);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pages", pages);
		return "author/viewauthors";
	}	
	
	@RequestMapping(value = "/viewauthor", method = RequestMethod.GET)
	public String viewAuthor(Model model, @RequestParam("authorId") Integer authorId) throws SQLException{
		logger.info("admin GET author/viewauthor.jsp: authorId="+authorId);
		Author author = adminService.getAuthorWithDetailById(authorId);
		model.addAttribute("author", author);
		return "author/viewauthor";
	}
	
	@RequestMapping(value = "/editauthor", method = RequestMethod.GET)
	public String editAuthor(Model model, @RequestParam("authorId") Integer authorId) throws SQLException{
		logger.info("admin GET author/editauthor.jsp: authorId="+authorId);
		Author author = adminService.getAuthorById(authorId);
		model.addAttribute("author", author);
		return "author/editauthor";
	}
	
	@RequestMapping(value = "/editauthor", method = RequestMethod.POST)
	@ResponseBody
	public void updateAuthor(Model model, @RequestParam("authorId") Integer authorId, @RequestParam("authorName") String authorName) throws SQLException{
		logger.info("admin POST author/editauthor.jsp: authorId="+authorId+" authorName="+authorName);
		adminService.updateAuthor(authorId, authorName);
	}
	
	@RequestMapping(value = "/deleteauthor", method = RequestMethod.POST)
	@ResponseBody
	public void deleteAuthor(Model model, @RequestParam("authorId") Integer authorId) throws SQLException{
		logger.info("admin POST author/deleteauthor.jsp: authorId="+authorId);
		adminService.deleteAuthor(authorId);
	}
	
	@RequestMapping(value = "/addauthor", method = RequestMethod.POST)
	@ResponseBody
	public String addAuthor(Model model, @RequestParam("authorName") String authorName) throws SQLException{
		logger.info("admin POST author/addauthor.jsp: authorName="+authorName);
		return adminService.addAuthor(authorName).toString();
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * admin: publisher * * * * * * * * * * * * * * * * * * * * * * * */
	@RequestMapping(value = "/publishers", method = RequestMethod.GET)
	public String publishers(Model model) {
		logger.info("admin GET publisher/publishers.jsp");
		return "publisher/publishers";
	}
	
	@RequestMapping(value = "/viewpublishers", method = RequestMethod.GET)
	public String viewPublishers(Model model, @RequestParam(value="searchString", required=false) String q, @RequestParam("pageNo") Integer pageNo) throws SQLException {
		logger.info("admin GET publisher/viewpublishers.jsp: q="+q+" pageNo="+pageNo);
		if(pageNo == null) pageNo = 1;
		List<Publisher> publishers = adminService.readAllPublishersWithPageNo(pageNo, q);
		Integer count = adminService.getPublishersCount(q);
		Integer pages = (count + Constants.pageSize - 1)/Constants.pageSize;
		model.addAttribute("publishers", publishers);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pages", pages);
		return "publisher/viewpublishers";
	}	
	
	@RequestMapping(value = "/viewpublisher", method = RequestMethod.GET)
	public String viewPublisher(Model model, @RequestParam("publisherId") Integer publisherId) throws SQLException{
		logger.info("admin GET publisher/viewpublisher.jsp: publisherId="+publisherId);
		Publisher publisher = adminService.getPublisherWithDetailById(publisherId);
		model.addAttribute("publisher", publisher);
		return "publisher/viewpublisher";
	}
	
	@RequestMapping(value = "/editpublisher", method = RequestMethod.GET)
	public String editPublisher(Model model, @RequestParam("publisherId") Integer publisherId) throws SQLException{
		logger.info("admin GET publisher/editpublisher.jsp: publisherId="+publisherId);
		Publisher publisher = adminService.getPublisherById(publisherId);
		model.addAttribute("publisher", publisher);
		return "publisher/editpublisher";
	}
	
	@RequestMapping(value = "/editpublisher", method = RequestMethod.POST)
	@ResponseBody
	public void updatePublisher(Model model, @RequestParam("publisherId") Integer publisherId, @RequestParam("publisherName") String publisherName,
			@RequestParam("publisherAddress") String publisherAddress, @RequestParam("publisherPhone") String publisherPhone) throws SQLException{
		logger.info("admin POST publisher/editpublisher.jsp: publisherId="+publisherId+" publisherName="+publisherName+"publisherAddress="+publisherAddress+"publisherPhone="+publisherPhone);
		adminService.updatePublisher(publisherId, publisherName, publisherAddress, publisherPhone);
	}
	
	@RequestMapping(value = "/deletepublisher", method = RequestMethod.POST)
	@ResponseBody
	public void deletePublisher(Model model, @RequestParam("publisherId") Integer publisherId) throws SQLException{
		logger.info("admin POST publisher/deletepublisher.jsp: publisherId="+publisherId);
		adminService.deletePublisher(publisherId);
	}
	
	@RequestMapping(value = "/addpublisher", method = RequestMethod.POST)
	@ResponseBody
	public String addPublisher(Model model, @RequestParam("publisherName") String publisherName, 
			@RequestParam("publisherAddress") String publisherAddress, @RequestParam("publisherPhone") String publisherPhone) throws SQLException{
		logger.info("admin POST publisher/addpublisher.jsp: publisherName="+publisherName+"publisherAddress="+publisherAddress+"publisherPhone="+publisherPhone);
		return adminService.addPublisher(publisherName, publisherAddress, publisherPhone).toString();
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * admin: branch * * * * * * * * * * * * * * * * * * * * * * * */
	@RequestMapping(value = "/branches", method = RequestMethod.GET)
	public String branches(Model model) {
		logger.info("admin GET branch/branches.jsp");
		return "branch/branches";
	}
	
	@RequestMapping(value = "/viewbranches", method = RequestMethod.GET)
	public String viewBranches(Model model, @RequestParam(value="searchString", required=false) String q, @RequestParam("pageNo") Integer pageNo) throws SQLException {
		logger.info("admin GET branch/viewbranches.jsp: q="+q+" pageNo="+pageNo);
		if(pageNo == null) pageNo = 1;
		List<Branch> branches = adminService.readAllBranchesWithPageNo(pageNo, q);
		Integer count = adminService.getBranchesCount(q);
		Integer pages = (count + Constants.pageSize - 1)/Constants.pageSize;
		model.addAttribute("branches", branches);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pages", pages);
		return "branch/viewbranches";
	}	
	
	@RequestMapping(value = "/viewbranch", method = RequestMethod.GET)
	public String viewBranch(Model model, @RequestParam("branchId") Integer branchId) throws SQLException{
		logger.info("admin GET branch/viewbranch.jsp: branchId="+branchId);
		Branch branch = adminService.getBranchWithDetailById(branchId);
		model.addAttribute("branch", branch);
		return "branch/viewbranch";
	}
	
	@RequestMapping(value = "/editbranch", method = RequestMethod.GET)
	public String editBranch(Model model, @RequestParam("branchId") Integer branchId) throws SQLException{
		logger.info("admin GET branch/editbranch.jsp: branchId="+branchId);
		Branch branch = adminService.getBranchById(branchId);
		model.addAttribute("branch", branch);
		return "branch/editbranch";
	}
	
	@RequestMapping(value = "/editbranch", method = RequestMethod.POST)
	@ResponseBody
	public void updateBranch(Model model, @RequestParam("branchId") Integer branchId, @RequestParam("branchName") String branchName,
			@RequestParam("branchAddress") String branchAddress) throws SQLException{
		logger.info("admin POST branch/editbranch.jsp: branchId="+branchId+" branchName="+branchName+"branchAddress="+branchAddress);
		adminService.updateBranch(branchId, branchName, branchAddress);
	}
	
	@RequestMapping(value = "/deletebranch", method = RequestMethod.POST)
	@ResponseBody
	public void deleteBranch(Model model, @RequestParam("branchId") Integer branchId) throws SQLException{
		logger.info("admin POST branch/deletebranch.jsp: branchId="+branchId);
		adminService.deleteBranch(branchId);
	}
	
	@RequestMapping(value = "/addbranch", method = RequestMethod.POST)
	@ResponseBody
	public void addBranch(Model model, @RequestParam("branchName") String branchName, @RequestParam("branchAddress") String branchAddress) throws SQLException{
		logger.info("admin POST branch/addbranch.jsp: branchName="+branchName+"branchAddress="+branchAddress);
		adminService.addBranch(branchName, branchAddress);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * admin: borrower * * * * * * * * * * * * * * * * * * * * * * * */
	@RequestMapping(value = "/borrowers", method = RequestMethod.GET)
	public String borrowers(Model model) {
		logger.info("admin GET borrower/borrowers.jsp");
		return "borrower/borrowers";
	}
	
	@RequestMapping(value = "/viewborrowers", method = RequestMethod.GET)
	public String viewBorrowers(Model model, @RequestParam(value="searchString", required=false) String q, @RequestParam("pageNo") Integer pageNo) throws SQLException {
		logger.info("admin GET borrower/viewborrowers.jsp: q="+q+" pageNo="+pageNo);
		if(pageNo == null) pageNo = 1;
		List<Borrower> borrowers = adminService.readAllBorrowersWithPageNo(pageNo, q);
		Integer count = adminService.getBorrowersCount(q);
		Integer pages = (count + Constants.pageSize - 1)/Constants.pageSize;
		model.addAttribute("borrowers", borrowers);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pages", pages);
		return "borrower/viewborrowers";
	}	
	
	@RequestMapping(value = "/viewborrower", method = RequestMethod.GET)
	public String viewBorrower(Model model, @RequestParam("cardNo") Integer cardNo) throws SQLException{
		logger.info("admin GET borrower/viewborrower.jsp: cardNo="+cardNo);
		Borrower borrower = adminService.getBorrowerWithDetailById(cardNo);
		model.addAttribute("borrower", borrower);
		return "borrower/viewborrower";
	}
	
	@RequestMapping(value = "/editborrower", method = RequestMethod.GET)
	public String editBorrower(Model model, @RequestParam("cardNo") Integer cardNo) throws SQLException{
		logger.info("admin GET borrower/editborrower.jsp: cardNo="+cardNo);
		Borrower borrower = adminService.getBorrowerById(cardNo);
		model.addAttribute("borrower", borrower);
		return "borrower/editborrower";
	}
	
	@RequestMapping(value = "/editborrower", method = RequestMethod.POST)
	@ResponseBody
	public void updateBorrower(Model model, @RequestParam("cardNo") Integer cardNo, @RequestParam("borrowerName") String borrowerName,
			@RequestParam("borrowerAddress") String borrowerAddress, @RequestParam("borrowerPhone") String borrowerPhone) throws SQLException{
		logger.info("admin POST borrower/editborrower.jsp: cardNo="+cardNo+" borrowerName="+borrowerName+"borrowerAddress="+borrowerAddress+"borrowerPhone="+borrowerPhone);
		adminService.updateBorrower(cardNo, borrowerName, borrowerAddress, borrowerPhone);
	}
	
	@RequestMapping(value = "/deleteborrower", method = RequestMethod.POST)
	@ResponseBody
	public void deleteBorrower(Model model, @RequestParam("cardNo") Integer cardNo) throws SQLException{
		logger.info("admin POST borrower/deleteborrower.jsp: cardNo="+cardNo);
		adminService.deleteBorrower(cardNo);
	}
	
	@RequestMapping(value = "/addborrower", method = RequestMethod.POST)
	@ResponseBody
	public void addBorrower(Model model, @RequestParam("borrowerName") String borrowerName, 
			@RequestParam("borrowerAddress") String borrowerAddress, @RequestParam("borrowerPhone") String borrowerPhone) throws SQLException{
		logger.info("admin POST borrower/addborrower.jsp: borrowerName="+borrowerName+"borrowerAddress="+borrowerAddress+"borrowerPhone="+borrowerPhone);
		adminService.addBorrower(borrowerName, borrowerAddress, borrowerPhone);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * admin: loan * * * * * * * * * * * * * * * * * * * * * * * */
	@RequestMapping(value = "/loans", method = RequestMethod.GET)
	public String loans(Model model) {
		logger.info("admin GET loan/loans.jsp");
		return "loan/loans";
	}
	
	@RequestMapping(value = "/viewloans", method = RequestMethod.GET)
	public String viewLoans(Model model, @RequestParam(value="searchString", required=false) String q, @RequestParam("pageNo") Integer pageNo, @RequestParam("seeAll") boolean seeAll) throws SQLException {
		logger.info("admin GET loan/viewloans.jsp: q="+q+" pageNo="+pageNo+" seeAll="+seeAll);
		if(pageNo == null) pageNo = 1;
		List<Loan> loans = adminService.readAllLoansWithPageNo(pageNo, q, seeAll);
		Integer count = adminService.getLoansCount(q, seeAll);
		Integer pages = (count + Constants.pageSize - 1)/Constants.pageSize;
		model.addAttribute("loans", loans);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pages", pages);
		return "loan/viewloans";
	}	
	
	@RequestMapping(value = "/editloan", method = RequestMethod.POST)
	@ResponseBody
	public void updateLoan(Model model, @RequestParam("loanId") Integer loanId, @RequestParam("diff") Integer diff) throws SQLException{
		logger.info("admin POST loan/editloan.jsp: loanId="+loanId+" diff="+diff);
		adminService.updateDueDateWithDiff(loanId, diff);
	}
	
	@RequestMapping(value = "/deleteloan", method = RequestMethod.POST)
	@ResponseBody
	public void deleteLoan(Model model, @RequestParam("loanId") Integer loanId) throws SQLException{
		logger.info("admin POST loan/deleteloan.jsp: loanId="+loanId);
		adminService.deleteLoan(loanId);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * admin: book * * * * * * * * * * * * * * * * * * * * * * * */
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String books(Model model) {
		logger.info("admin GET book/books.jsp");
		return "book/books";
	}
	
	@RequestMapping(value = "/viewbooks", method = RequestMethod.GET)
	public String viewBooks(Model model, @RequestParam(value="searchString", required=false) String q, @RequestParam("pageNo") Integer pageNo) throws SQLException {
		logger.info("admin GET book/viewbooks.jsp: q="+q+" pageNo="+pageNo);
		if(pageNo == null) pageNo = 1;
		List<Book> books = adminService.readAllBooksWithPageNo(pageNo, q);
		Integer count = adminService.getBooksCount(q);
		Integer pages = (count + Constants.pageSize - 1)/Constants.pageSize;
		model.addAttribute("books", books);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pages", pages);
		return "book/viewbooks";
	}	
	
	@RequestMapping(value = "/viewbook", method = RequestMethod.GET)
	public String viewBook(Model model, @RequestParam("bookId") Integer bookId) throws SQLException{
		logger.info("admin GET book/viewbook.jsp: bookId="+bookId);
		Book book = adminService.getBookWithDetailById(bookId);
		model.addAttribute("book", book);
		return "book/viewbook";
	}
	
	@RequestMapping(value = "/editbook", method = RequestMethod.GET)
	public String editBook(Model model, @RequestParam("bookId") Integer bookId) throws SQLException{
		logger.info("admin GET book/editbook.jsp: bookId="+bookId);
		Book book = adminService.getBookWithDetailById(bookId);
		List<Publisher> publishers = adminService.readAllPublishers();
		List<Author> authors = adminService.readAllAuthors();
		List<Genre> genres = adminService.readAllGenres();
		Set<Author> bookAuthors = new HashSet<>();
		if(book.getAuthors() != null) bookAuthors.addAll(book.getAuthors());
		Set<Genre> bookGenres = new HashSet<>();
		if(book.getGenres() != null) bookGenres.addAll(book.getGenres());
		model.addAttribute("book", book);
		model.addAttribute("publishers", publishers);
		model.addAttribute("authors", authors);
		model.addAttribute("genres", genres);
		model.addAttribute("bookAuthors", bookAuthors);
		model.addAttribute("bookGenres", bookGenres);
		return "book/editbook";
	}
	
	@RequestMapping(value = "/editbook", method = RequestMethod.POST)
	@ResponseBody
	public void updateBook(Model model, @RequestParam("bookId") Integer bookId, @RequestParam("title") String title, @RequestParam("publisherId") Integer publisherId,
			@RequestParam(value="authorIds[]", required=false) Integer[] authorIds, @RequestParam(value="genreIds[]", required=false) Integer[] genreIds) throws SQLException{
		logger.info("admin POST book/editbook.jsp: bookId="+bookId+" title="+title+" publisherId="+publisherId+" authorIds="+authorIds+" genreIds="+genreIds);
		adminService.updateBookWithDetails(bookId, title, publisherId, authorIds, genreIds);
	}
	
	@RequestMapping(value = "/deletebook", method = RequestMethod.POST)
	@ResponseBody
	public void deleteBook(Model model, @RequestParam("bookId") Integer bookId) throws SQLException{
		logger.info("admin POST book/deletebook.jsp: bookId="+bookId);
		adminService.deleteBook(bookId);
	}
	
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String getAddBook(Model model) throws SQLException {
		logger.info("admin GET book/addbook.jsp");
		List<Publisher> publishers = adminService.readAllPublishers();
		List<Author> authors = adminService.readAllAuthors();
		List<Genre> genres = adminService.readAllGenres();
		model.addAttribute("publishers", publishers);
		model.addAttribute("authors", authors);
		model.addAttribute("genres", genres);
		return "book/addbook";
	}
	
	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	@ResponseBody
	public void addBook(Model model, @RequestParam("title") String title, @RequestParam(value="publisherId", required=false) Integer publisherId,
			@RequestParam(value="authorIds[]", required=false) Integer[] authorIds, @RequestParam(value="genreIds[]", required=false) Integer[] genreIds) throws SQLException {
		logger.info("admin POST book/addbook.jsp: title="+title+" publisherId="+publisherId+" authorIds="+authorIds+" genreIds="+genreIds);
		adminService.addBookWithDetails(title, publisherId, authorIds, genreIds);
	}
	
	/* * * * * * * * * * * * * * * * * * * * * * * * admin: genre * * * * * * * * * * * * * * * * * * * * * * * */
	@RequestMapping(value = "/addgenre", method = RequestMethod.POST)
	@ResponseBody
	public String addGenre(Model model, @RequestParam("genreName") String genreName) throws SQLException{
		logger.info("admin POST genre/addgenre.jsp: genreName="+genreName);
		return adminService.addGenre(genreName).toString();
	}
}
