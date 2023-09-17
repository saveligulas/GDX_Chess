package error;

public class InvalidTargetedTileException extends RuntimeException {
    public InvalidTargetedTileException() {
        super("Invalid targeted Tile");
    }
}
