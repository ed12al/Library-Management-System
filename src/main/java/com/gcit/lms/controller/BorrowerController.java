package com.gcit.lms.controller;

import java.sql.SQLException;
import java.util.List;

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
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.service.BorrowerService;

@Controller
@RequestMapping("/borrower")
public class BorrowerController {
	private static final Logger logger = LoggerFactory.getLogger(BorrowerController.class);
	
	@Autowired
	BorrowerService borService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String login(Model model) {
		logger.info("borrower GET login.jsp");
		return "login";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String loginPOST(Model model, @RequestParam("cardNo") String cardNoStr) throws SQLException {
		logger.info("borrower POST login.jsp: cardNo="+cardNoStr);
		try{
			Borrower borrower = borService.readBorrowerById(Integer.parseInt(cardNoStr));
			if(borrower == null){
				model.addAttribute("error", "Card number does not exist!");
				return "login";
			}else{
				model.addAttribute("borrower", borrower);
				return "borrower";
			}
		}catch(NumberFormatException e){
			model.addAttribute("error", "Please enter a valid card number!");
			return "login";
		}
	}
	
	@RequestMapping(value = "/viewbooks", method = RequestMethod.GET)
	public String viewBooks(Model model, @RequestParam(value="searchString", required=false) String q, @RequestParam("pageNo") Integer pageNo) throws SQLException {
		logger.info("borrower GET book/viewbooks.jsp: q="+q+" pageNo="+pageNo);
		if(pageNo == null) pageNo = 1;
		List<Book> books = borService.readAllBooksWithPageNo(pageNo, q);
		Integer count = borService.getBooksCount(q);
		Integer pages = (count + Constants.pageSize - 1)/Constants.pageSize;
		model.addAttribute("books", books);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pages", pages);
		return "book/viewborrowerbooks";
	}	
	
	@RequestMapping(value = "/viewbook", method = RequestMethod.GET)
	public String viewBook(Model model, @RequestParam("bookId") Integer bookId) throws SQLException{
		logger.info("borrower GET book/viewbook.jsp: bookId="+bookId);
		Book book = borService.getBookWithDetailById(bookId);
		model.addAttribute("book", book);
		return "book/viewbook";
	}
	
	@RequestMapping(value = "/borrowbook", method = RequestMethod.GET)
	public String borrowBook(Model model, @RequestParam("bookId") Integer bookId) throws SQLException{
		logger.info("borrower GET book/borrowbook.jsp: bookId="+bookId);
		Book book = borService.getBookById(bookId);
		List<BookCopy> bookCopies = borService.getBookCopiesByBook(bookId);
		model.addAttribute("book", book);
		model.addAttribute("bookCopies", bookCopies);
		return "book/borrowbook";
	}
	
	@RequestMapping(value = "/borrowbook", method = RequestMethod.POST)
	@ResponseBody
	public void borrowedBook(Model model, @RequestParam("branchId") Integer branchId, @RequestParam("bookId") Integer bookId, @RequestParam("cardNo") Integer cardNo) throws SQLException{
		logger.info("borrower GET book/borrowbook.jsp: branchId="+branchId+" bookId="+bookId+" cardNo="+cardNo);
		borService.borrowBook(branchId, bookId, cardNo);
	}
	
	@RequestMapping(value = "/returnbooks", method = RequestMethod.GET)
	public String returnBook(Model model, @RequestParam("cardNo") Integer cardNo) throws SQLException{
		logger.info("borrower GET book/returnbook.jsp: cardNo="+cardNo);
		Borrower borrower = borService.getBorrowerWithDetailById(cardNo);
		model.addAttribute("borrower", borrower);
		return "book/returnbook";
	}
	
	@RequestMapping(value = "/returnbooks", method = RequestMethod.POST)
	@ResponseBody
	public void returnedBook(Model model, @RequestParam("loanId") Integer loanId) throws SQLException {
		logger.info("borrower GET book/returnbook.jsp: loanId="+loanId);
		borService.returnLoanById(loanId);
	}
}
