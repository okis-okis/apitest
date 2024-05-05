package group.okis.apitest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import group.okis.apitest.model.Book;

public interface BookRepo extends JpaRepository<Book, Integer>{
	
}
