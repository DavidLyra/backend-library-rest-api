package be.lampiris.api.repository;

import be.lampiris.api.model.BookFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookFamilyRepository extends JpaRepository<BookFamily, Long> {
}
