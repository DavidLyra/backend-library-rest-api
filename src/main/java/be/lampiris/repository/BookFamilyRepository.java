package be.lampiris.repository;

import be.lampiris.model.BookFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookFamilyRepository extends JpaRepository<BookFamily, Long> {
}
