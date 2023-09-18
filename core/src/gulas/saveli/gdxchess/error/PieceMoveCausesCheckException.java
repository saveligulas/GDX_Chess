package error;

public class PieceMoveCausesCheckException extends RuntimeException {
    public PieceMoveCausesCheckException() {
        super("Movement of Piece causes Check");
    }
}
