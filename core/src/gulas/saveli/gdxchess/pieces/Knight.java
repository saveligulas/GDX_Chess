package pieces;

import model.Piece;
import model.Piece_Type;

public class Knight extends Piece {
    public Knight(boolean isWhite, byte column) {
        super(isWhite);
        setType(Piece_Type.KNIGHT);
        if (isWhite) {
            setRow((byte) 0);
        } else {
            setRow((byte) 7);
        }
        setColumn(column);
    }
}
