package group.okis.apitest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private BookRepo repo;
	
	@GetMapping("/getAll")
	public List<Book> getAll(){
		return repo.findAll();
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping("/get/{id}")
	public ResponseEntity<Book> getOne(@PathVariable("id") int id) {		
		Optional<Book> tutorialData = repo.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/add")
	public Book add(@RequestParam("name") String name){
		return repo.save(new Book(name));
	}
	
	@PostMapping("/addBook")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		try {
			repo.save(book);
			return new ResponseEntity<>(book, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Book> updateTutorial(@PathVariable("id") int id, @RequestBody Book book) {
		Optional<Book> bookData = repo.findById(id);

		if (bookData.isPresent()) {
			Book uBook = bookData.get();
			uBook.setName(book.getName());
			return new ResponseEntity<>(repo.save(uBook), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") int id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			repo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
