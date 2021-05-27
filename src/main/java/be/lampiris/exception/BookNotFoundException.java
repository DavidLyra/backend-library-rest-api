package be.lampiris.exception;

class BookNotFoundException extends RuntimeException {

    BookNotFoundException(Long id) {
        super("Could not find book: " + id);
    }
}
