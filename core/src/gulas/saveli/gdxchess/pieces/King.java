package pieces;

import model.Piece;
import model.Piece_Type;

public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite);
        setType(Piece_Type.KING);
    }
}
