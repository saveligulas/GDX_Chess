package pieces;

import model.Piece;
import model.Piece_Type;

public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite);
        setType(Piece_Type.KING);
        if (isWhite) {
            setRow((byte) 0);
        } else {
            setRow((byte) 7);
        }
        setColumn((byte) 4);
    }

    @Override
    public String toString() {
        String king = "King";
        if (isColorWhite()) {
            return "White " + king;
        } else {
            return "Black " + king;
        }
    }
}
