package crudapp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import crudapp.entities.Book;

@Component
public class BookDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Transactional
	public void insertBookDetails(Book book) {
		
		this.hibernateTemplate.saveOrUpdate(book);
	}
	
	public List<Book> getAllBooks() {
		
		List<Book> list = this.hibernateTemplate.loadAll(Book.class);
		return list; 
	}
	
	public Book getSingleBook(int id) {
		
		Book b = this.hibernateTemplate.get(Book.class, id);
		return b;
	}
	
	@Transactional
	public void deleteSingleBook(int id) {
		
		
		Book b = this.hibernateTemplate.get(Book.class, id);
		this.hibernateTemplate.delete(b);
	}
}
