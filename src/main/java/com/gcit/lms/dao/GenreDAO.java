package com.gcit.lms.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;

public class GenreDAO extends BaseDAO implements RowMapper<Genre>{
	
	public void addGenre(Genre genre) throws SQLException {
		template.update("insert into tbl_genre (genreName) values (?)", 
				new Object[] { genre.getGenreName() });
	}

	public void updateGenre(Genre genre) throws SQLException {
		template.update("update tbl_genre set genreName = ? where genreId = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void deleteGenre(Genre genre) throws SQLException {
		template.update("delete from tbl_genre where genreId = ?", 
				new Object[] { genre.getGenreId() });
	}

	public List<Genre> readAllGenres() throws SQLException {
		return template.query("select * from tbl_genre", this);
	}
	
	public Integer getGenresCount() throws SQLException{
		return template.queryForObject("select count(*) AS COUNT from tbl_genre", Integer.class);
	}
	
	public Genre readGenreById(Genre genre) throws SQLException{
		return template.queryForObject("select * from tbl_genre where genreId = ?", new Object[]{genre.getGenreId()}, this);
	}

	public List<Genre> readAllGenresByBook(Book book) throws SQLException {
		return template.query("select * from tbl_genre where genreId IN (select genreId from tbl_book_genres where bookId = ?)", 
				new Object[] { book.getBookId() }, this);
	}

	@Override
	public Genre mapRow(ResultSet rs, int row) throws SQLException {
		Genre g = new Genre();
		g.setGenreId(rs.getInt("genreId"));
		g.setGenreName(rs.getString("genreName"));
		return g;
	}

}
