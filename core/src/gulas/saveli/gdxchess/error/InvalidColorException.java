package gulas.saveli.gdxchess.error;

public class InvalidColorException extends RuntimeException {
    public InvalidColorException() { super("Invalid Color set for Piece"); }
}
