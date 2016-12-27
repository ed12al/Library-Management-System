package com.gcit.lms.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
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
	public String viewAuthors(Model model, @RequestParam(value="searchString", required=false) String q, @RequestParam("pageNo") int pageNo, HttpServletResponse response) throws SQLException {
		logger.info("admin GET author/viewauthors.jsp: q="+q+" pageNo="+pageNo);
		List<Author> authors = adminService.readAllAuthorsWithPageNo(pageNo, q);
		Integer count = adminService.getAuthorsCount(q);
		Integer pages = (count + Constants.pageSize - 1)/Constants.pageSize;
		model.addAttribute("authors", authors);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pages", pages);
		return "author/viewauthors";
	}	
	
	@RequestMapping(value = "/viewauthor", method = RequestMethod.GET)
	public String viewAuthor(Model model, @RequestParam("authorId") int authorId) throws SQLException{
		logger.info("admin GET author/viewauthor.jsp: authorId="+authorId);
		Author author = adminService.getAuthorWithDetailById(authorId);
		model.addAttribute("author", author);
		return "author/viewauthor";
	}
	
	@RequestMapping(value = "/editauthor", method = RequestMethod.GET)
	public String editAuthor(Model model, @RequestParam("authorId") int authorId) throws SQLException{
		logger.info("admin GET author/editauthor.jsp: authorId="+authorId);
		Author author = adminService.getAuthorById(authorId);
		model.addAttribute("author", author);
		return "author/editauthor";
	}
	
	@RequestMapping(value = "/editauthor", method = RequestMethod.POST)
	@ResponseBody
	public void updateAuthor(Model model, @RequestParam("authorId") int authorId, @RequestParam("authorName") String authorName) throws SQLException{
		logger.info("admin POST author/editauthor.jsp: authorId="+authorId+" authorName="+authorName);
		adminService.updateAuthor(authorId, authorName);
	}
	
	@RequestMapping(value = "/deleteauthor", method = RequestMethod.POST)
	@ResponseBody
	public void deleteAuthor(Model model, @RequestParam("authorId") int authorId) throws SQLException{
		logger.info("admin POST author/deleteauthor.jsp: authorId="+authorId);
		adminService.deleteAuthor(authorId);
	}
	
	@RequestMapping(value = "/addauthor", method = RequestMethod.POST)
	@ResponseBody
	public void addAuthor(Model model, @RequestParam("authorName") String authorName) throws SQLException{
		logger.info("admin POST author/addauthor.jsp: authorName="+authorName);
		adminService.addAuthor(authorName);
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * admin: publisher * * * * * * * * * * * * * * * * * * * * * * * */
	@RequestMapping(value = "/publishers", method = RequestMethod.GET)
	public String publishers(Model model) {
		logger.info("admin GET publisher/publishers.jsp");
		return "publisher/publishers";
	}
	
	@RequestMapping(value = "/viewpublishers", method = RequestMethod.GET)
	public String viewPublishers(Model model, @RequestParam(value="searchString", required=false) String q, @RequestParam("pageNo") int pageNo, HttpServletResponse response) throws SQLException {
		logger.info("admin GET publisher/viewpublishers.jsp: q="+q+" pageNo="+pageNo);
		List<Publisher> publishers = adminService.readAllPublishersWithPageNo(pageNo, q);
		Integer count = adminService.getPublishersCount(q);
		Integer pages = (count + Constants.pageSize - 1)/Constants.pageSize;
		model.addAttribute("publishers", publishers);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pages", pages);
		return "publisher/viewpublishers";
	}	
	
	@RequestMapping(value = "/viewpublisher", method = RequestMethod.GET)
	public String viewPublisher(Model model, @RequestParam("publisherId") int publisherId) throws SQLException{
		logger.info("admin GET publisher/viewpublisher.jsp: publisherId="+publisherId);
		Publisher publisher = adminService.getPublisherWithDetailById(publisherId);
		model.addAttribute("publisher", publisher);
		return "publisher/viewpublisher";
	}
	
	@RequestMapping(value = "/editpublisher", method = RequestMethod.GET)
	public String editPublisher(Model model, @RequestParam("publisherId") int publisherId) throws SQLException{
		logger.info("admin GET publisher/editpublisher.jsp: publisherId="+publisherId);
		Publisher publisher = adminService.getPublisherById(publisherId);
		model.addAttribute("publisher", publisher);
		return "publisher/editpublisher";
	}
	
	@RequestMapping(value = "/editpublisher", method = RequestMethod.POST)
	@ResponseBody
	public void updatePublisher(Model model, @RequestParam("publisherId") int publisherId, @RequestParam("publisherName") String publisherName,
			@RequestParam("publisherAddress") String publisherAddress, @RequestParam("publisherPhone") String publisherPhone) throws SQLException{
		logger.info("admin POST publisher/editpublisher.jsp: publisherId="+publisherId+" publisherName="+publisherName+"publisherAddress="+publisherAddress+"publisherPhone="+publisherPhone);
		adminService.updatePublisher(publisherId, publisherName, publisherAddress, publisherPhone);
	}
	
	@RequestMapping(value = "/deletepublisher", method = RequestMethod.POST)
	@ResponseBody
	public void deletePublisher(Model model, @RequestParam("publisherId") int publisherId) throws SQLException{
		logger.info("admin POST publisher/deletepublisher.jsp: publisherId="+publisherId);
		adminService.deletePublisher(publisherId);
	}
	
	@RequestMapping(value = "/addpublisher", method = RequestMethod.POST)
	@ResponseBody
	public void addPublisher(Model model, @RequestParam("publisherName") String publisherName, 
			@RequestParam("publisherAddress") String publisherAddress, @RequestParam("publisherPhone") String publisherPhone) throws SQLException{
		logger.info("admin POST publisher/addpublisher.jsp: publisherName="+publisherName+"publisherAddress="+publisherAddress+"publisherPhone="+publisherPhone);
		adminService.addPublisher(publisherName, publisherAddress, publisherPhone);
	}

	/* * * * * * * * * * * * * * * * * * * * * * * * admin: branch * * * * * * * * * * * * * * * * * * * * * * * */
	@RequestMapping(value = "/branches", method = RequestMethod.GET)
	public String branches(Model model) {
		logger.info("admin GET branch/branches.jsp");
		return "branch/branches";
	}
	
	@RequestMapping(value = "/viewbranches", method = RequestMethod.GET)
	public String viewBranches(Model model, @RequestParam(value="searchString", required=false) String q, @RequestParam("pageNo") int pageNo, HttpServletResponse response) throws SQLException {
		logger.info("admin GET branch/viewbranches.jsp: q="+q+" pageNo="+pageNo);
		List<Branch> branches = adminService.readAllBranchesWithPageNo(pageNo, q);
		Integer count = adminService.getBranchesCount(q);
		Integer pages = (count + Constants.pageSize - 1)/Constants.pageSize;
		model.addAttribute("branches", branches);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pages", pages);
		return "branch/viewbranches";
	}	
	
	@RequestMapping(value = "/viewbranch", method = RequestMethod.GET)
	public String viewBranch(Model model, @RequestParam("branchId") int branchId) throws SQLException{
		logger.info("admin GET branch/viewbranch.jsp: branchId="+branchId);
		Branch branch = adminService.getBranchWithDetailById(branchId);
		model.addAttribute("branch", branch);
		return "branch/viewbranch";
	}
	
	@RequestMapping(value = "/editbranch", method = RequestMethod.GET)
	public String editBranch(Model model, @RequestParam("branchId") int branchId) throws SQLException{
		logger.info("admin GET branch/editbranch.jsp: branchId="+branchId);
		Branch branch = adminService.getBranchById(branchId);
		model.addAttribute("branch", branch);
		return "branch/editbranch";
	}
	
	@RequestMapping(value = "/editbranch", method = RequestMethod.POST)
	@ResponseBody
	public void updateBranch(Model model, @RequestParam("branchId") int branchId, @RequestParam("branchName") String branchName,
			@RequestParam("branchAddress") String branchAddress) throws SQLException{
		logger.info("admin POST branch/editbranch.jsp: branchId="+branchId+" branchName="+branchName+"branchAddress="+branchAddress);
		adminService.updateBranch(branchId, branchName, branchAddress);
	}
	
	@RequestMapping(value = "/deletebranch", method = RequestMethod.POST)
	@ResponseBody
	public void deleteBranch(Model model, @RequestParam("branchId") int branchId) throws SQLException{
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
	public String viewBorrowers(Model model, @RequestParam(value="searchString", required=false) String q, @RequestParam("pageNo") int pageNo, HttpServletResponse response) throws SQLException {
		logger.info("admin GET borrower/viewborrowers.jsp: q="+q+" pageNo="+pageNo);
		List<Borrower> borrowers = adminService.readAllBorrowersWithPageNo(pageNo, q);
		Integer count = adminService.getBorrowersCount(q);
		Integer pages = (count + Constants.pageSize - 1)/Constants.pageSize;
		model.addAttribute("borrowers", borrowers);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pages", pages);
		return "borrower/viewborrowers";
	}	
	
	@RequestMapping(value = "/viewborrower", method = RequestMethod.GET)
	public String viewBorrower(Model model, @RequestParam("cardNo") int cardNo) throws SQLException{
		logger.info("admin GET borrower/viewborrower.jsp: cardNo="+cardNo);
		Borrower borrower = adminService.getBorrowerWithDetailById(cardNo);
		model.addAttribute("borrower", borrower);
		return "borrower/viewborrower";
	}
	
	@RequestMapping(value = "/editborrower", method = RequestMethod.GET)
	public String editBorrower(Model model, @RequestParam("cardNo") int cardNo) throws SQLException{
		logger.info("admin GET borrower/editborrower.jsp: cardNo="+cardNo);
		Borrower borrower = adminService.getBorrowerById(cardNo);
		model.addAttribute("borrower", borrower);
		return "borrower/editborrower";
	}
	
	@RequestMapping(value = "/editborrower", method = RequestMethod.POST)
	@ResponseBody
	public void updateBorrower(Model model, @RequestParam("cardNo") int cardNo, @RequestParam("borrowerName") String borrowerName,
			@RequestParam("borrowerAddress") String borrowerAddress, @RequestParam("borrowerPhone") String borrowerPhone) throws SQLException{
		logger.info("admin POST borrower/editborrower.jsp: cardNo="+cardNo+" borrowerName="+borrowerName+"borrowerAddress="+borrowerAddress+"borrowerPhone="+borrowerPhone);
		adminService.updateBorrower(cardNo, borrowerName, borrowerAddress, borrowerPhone);
	}
	
	@RequestMapping(value = "/deleteborrower", method = RequestMethod.POST)
	@ResponseBody
	public void deleteBorrower(Model model, @RequestParam("cardNo") int cardNo) throws SQLException{
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
}
