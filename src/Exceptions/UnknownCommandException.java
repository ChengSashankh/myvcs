package Exceptions;

/***
 * Implements exception for when unknown commands are entered into the program
 */
public class UnknownCommandException extends Exception {
    public UnknownCommandException(String message) {
        super(message);
    }
}
