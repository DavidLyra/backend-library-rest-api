package be.lampiris.repository;

import be.lampiris.model.BookFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookFamilyRepository extends JpaRepository<BookFamily, Long> {

    @Query("select b from BookFamily b where library_id = :id")
    List<BookFamily> findByLibrary(Long id);
}
