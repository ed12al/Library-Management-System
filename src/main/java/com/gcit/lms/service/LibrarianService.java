package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.BookCopyDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BranchDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopy;
import com.gcit.lms.entity.Branch;
import com.gcit.lms.common.Constants;

public class LibrarianService {

	@Autowired
	BookDAO bdao;
	
	@Autowired
	BranchDAO bhdao;
	
	@Autowired
	BookCopyDAO bcdao;
	
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
	
	public List<Book> readAllBooksWithNoCopyByBranch(Integer branchId) throws SQLException {
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		return bdao.readAllBooksWithNoCopyByBranch(branch);
	}
	
	@Transactional
	public void updateBookCopies(Integer branchId, String bookCopiesStr) throws ParseException, SQLException {
		Branch branch = new Branch();
		branch.setBranchId(branchId);
		JSONParser parser = new JSONParser();
		JSONArray jsonArray = (JSONArray) parser.parse(bookCopiesStr);
		List<BookCopy> bookCopies = new ArrayList<>();
		for(Object o : jsonArray){
			JSONObject jsonObject = (JSONObject) o;
			try{
				Book book = new Book();
				book.setBookId(Integer.parseInt(jsonObject.get("id").toString()));
				BookCopy bookCopy = new BookCopy();
				bookCopy.setBook(book);
				bookCopy.setNoOfCopies(Integer.parseInt(jsonObject.get("copy").toString()));
				bookCopies.add(bookCopy);
			}catch(NumberFormatException e){
				System.out.println("cannot parse one record!");
			}
		}
		branch.setBookCopy(bookCopies);
		bhdao.updateAllBookCopiesByBranch(branch);
	}
}
