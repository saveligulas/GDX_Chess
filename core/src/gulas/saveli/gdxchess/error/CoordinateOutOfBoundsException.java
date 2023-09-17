package error;

public class CoordinateOutOfBoundsException extends RuntimeException {
    public CoordinateOutOfBoundsException() {
        super("Given Coordinates are out of bounds");
    }
}
