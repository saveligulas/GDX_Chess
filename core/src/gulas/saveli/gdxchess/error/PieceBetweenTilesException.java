package error;

public class PieceBetweenTilesException extends RuntimeException {
    public PieceBetweenTilesException() {
        super("Piece is between selected and targeted tile");
    }
}
