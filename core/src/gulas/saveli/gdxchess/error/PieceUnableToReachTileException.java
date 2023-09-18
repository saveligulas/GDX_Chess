package error;

public class PieceUnableToReachTileException extends RuntimeException {
    public PieceUnableToReachTileException() {
        super("Piece can't reach targeted Tile");
    }
}
