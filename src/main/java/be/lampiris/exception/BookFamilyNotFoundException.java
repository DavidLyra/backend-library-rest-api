package be.lampiris.exception;

class BookFamilyNotFoundException extends RuntimeException {

    BookFamilyNotFoundException(Long id) {

        super("Could not find book family: " + id);
    }
}
