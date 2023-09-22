package pieces;

import model.Piece;
import model.Piece_Type;

public class Bishop extends Piece {
    private final String specification;
    public Bishop(boolean isWhite, byte column) {
        super(isWhite);
        setType(Piece_Type.BISHOP);
        if (isWhite) {
            setRow((byte) 0);
            if (column == 2) {
                specification = "Dark";
            } else {
                specification = "Light";
            }
        } else {
            setRow((byte) 7);
            if (column == 2) {
                specification = "Light";
            } else {
                specification = "Dark";
            }
        }

        setColumn(column);
    }

    @Override
    public String toString() {
        return specification + "-Colored Bishop";
    }
}
