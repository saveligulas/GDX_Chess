package error;

public class NoPieceToPlaceOnTileException extends RuntimeException {
    public NoPieceToPlaceOnTileException() {
        super("No piece to be placed on tile");
    }
}
