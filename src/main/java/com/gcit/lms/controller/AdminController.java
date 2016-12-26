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
}
