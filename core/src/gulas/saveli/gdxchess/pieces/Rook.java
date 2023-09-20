package pieces;

import model.Piece;
import model.Piece_Type;

public class Rook extends Piece {
    public Rook(boolean isWhite, byte column) {
        super(isWhite);
        setType(Piece_Type.ROOK);
        if (isWhite) {
            setRow((byte) 0);
        } else {
            setRow((byte) 7);
        }
        setColumn(column);
    }
}
