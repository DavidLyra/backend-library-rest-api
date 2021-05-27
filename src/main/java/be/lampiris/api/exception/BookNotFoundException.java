package be.lampiris.api.exception;

class BookNotFoundException extends RuntimeException {

    BookNotFoundException(Long id) {
        super("Could not find book: " + id);
    }
}
