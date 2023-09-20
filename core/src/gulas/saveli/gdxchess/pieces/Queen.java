package pieces;

import model.Piece;
import model.Piece_Type;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite);
        setType(Piece_Type.QUEEN);
        if (isWhite) {
            setRow((byte) 0);
        } else {
            setRow((byte) 7);
        }
        setColumn((byte) 3);
    }
}
