package com.gcit.lms.controller;

import java.sql.SQLException;
import java.util.List;

import org.json.simple.parser.ParseException;
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
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.service.LibrarianService;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {
	private static final Logger logger = LoggerFactory.getLogger(LibrarianController.class);
	
	@Autowired
	LibrarianService libService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String librarian(Model model) {
		logger.info("librarian GET librarian.jsp");
		return "librarian";
	}
	
	@RequestMapping(value = "/viewbranches", method = RequestMethod.GET)
	public String viewBranches(Model model, @RequestParam(value="searchString", required=false) String q, @RequestParam("pageNo") Integer pageNo) throws SQLException {
		logger.info("librarian GET branch/viewbranches.jsp: q="+q+" pageNo="+pageNo);
		if(pageNo == null) pageNo = 1;
		List<Branch> branches = libService.readAllBranchesWithPageNo(pageNo, q);
		Integer count = libService.getBranchesCount(q);
		Integer pages = (count + Constants.pageSize - 1)/Constants.pageSize;
		model.addAttribute("branches", branches);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pages", pages);
		return "branch/viewlibrarianbranches";
	}
	
	@RequestMapping(value = "/viewbranch", method = RequestMethod.GET)
	public String viewBranch(Model model, @RequestParam("branchId") Integer branchId) throws SQLException{
		logger.info("librarian GET branch/viewbranch.jsp: branchId="+branchId);
		Branch branch = libService.getBranchWithDetailById(branchId);
		model.addAttribute("branch", branch);
		return "branch/viewbranch";
	}
	
	@RequestMapping(value = "/editbranch", method = RequestMethod.GET)
	public String editBranch(Model model, @RequestParam("branchId") Integer branchId) throws SQLException{
		logger.info("librarian GET branch/editbranch.jsp: branchId="+branchId);
		Branch branch = libService.getBranchById(branchId);
		model.addAttribute("branch", branch);
		return "branch/editbranch";
	}
	
	@RequestMapping(value = "/editbranch", method = RequestMethod.POST)
	@ResponseBody
	public void updateBranch(Model model, @RequestParam("branchId") Integer branchId, @RequestParam("branchName") String branchName,
			@RequestParam("branchAddress") String branchAddress) throws SQLException{
		logger.info("librarian POST branch/editbranch.jsp: branchId="+branchId+" branchName="+branchName+"branchAddress="+branchAddress);
		libService.updateBranch(branchId, branchName, branchAddress);
	}
	
	@RequestMapping(value = "/editbookcopies", method = RequestMethod.GET)
	public String editBookCopies(Model model, @RequestParam("branchId") Integer branchId) throws SQLException{
		logger.info("librarian GET bookcopy/editbookcopies.jsp: branchId="+branchId);
		Branch branch = libService.getBranchWithDetailById(branchId);
		List<Book> noCopyBooks = libService.readAllBooksWithNoCopyByBranch(branchId);
		model.addAttribute("branch", branch);
		model.addAttribute("noCopyBooks", noCopyBooks);
		return "bookcopy/editbookcopies";
	}
	
	@RequestMapping(value = "/editbookcopies", method = RequestMethod.POST)
	@ResponseBody
	public void updateBookCopies(Model model, @RequestParam("branchId") Integer branchId, @RequestParam("bookCopies") String bookCopiesStr) throws SQLException, ParseException{
		logger.info("librarian POST bookcopy/editbookcopies.jsp: branchId="+branchId+"bookCopies="+bookCopiesStr);
		libService.updateBookCopies(branchId, bookCopiesStr);
	}
}
