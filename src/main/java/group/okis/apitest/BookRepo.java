package group.okis.apitest;

import org.springframework.data.jpa.repository.JpaRepository;

interface BookRepo extends JpaRepository<Book, Integer>{
	
}
