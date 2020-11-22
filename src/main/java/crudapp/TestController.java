package crudapp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import crudapp.dao.BookDao;
import crudapp.entities.Book;

@Controller
public class TestController {

	@Autowired
	private BookDao bookDao;

	@RequestMapping("/")
	public String home(Model m) {

		List<Book> books = this.bookDao.getAllBooks();
		m.addAttribute("books", books);

		return "index";
	}

	@RequestMapping("/add-books")
	public String addBook() {

		return "add_book_form";
	}

	@PostMapping("/handleaddbookform")
	public RedirectView handleAddBookForm(@ModelAttribute("book") Book book, HttpServletRequest request) {

		this.bookDao.insertBookDetails(book);

		RedirectView redirectView = new RedirectView();

		redirectView.setUrl(request.getContextPath() + "/");
		return redirectView;
	}

	@RequestMapping("/delete-book/{id}")
	public RedirectView deleteBook(@PathVariable("id") int id, HttpServletRequest request) {

		this.bookDao.deleteSingleBook(id);
		RedirectView redirectView = new RedirectView();

		redirectView.setUrl(request.getContextPath() + "/");
		return redirectView;

	}
	
	@RequestMapping("/update-book/{id}")
	public String updateBook(@PathVariable("id") int id, Model m) {
		
		Book book = this.bookDao.getSingleBook(id);
		m.addAttribute("singleBook", book);
		return "update_book_form";
	}
}
