package pieces;

import error.CoordinateOutOfBoundsException;
import lombok.Setter;
import model.Piece;
import model.Piece_Type;

public class Pawn extends Piece {
    @Setter
    private byte startingColumn;

    public Pawn(boolean isWhite, byte positionColumn) {
        super(isWhite);
        setType(Piece_Type.PAWN);
        if (isWhite) {
            setRow((byte) 1);
        } else {
            setRow((byte) 6);
        }

        if (positionColumn >=0 && positionColumn <= MAX_COORDINATE_VALUE) {
            setColumn(positionColumn);
            setStartingColumn(positionColumn);
        } else {
            throw new CoordinateOutOfBoundsException();
        }
    }

    public boolean getPawnHasMoved() {
        return startingColumn != getColumn();
    }

    @Override
    public String toString() {
        return COLUMN_CHARACTERS[startingColumn] + "-" + "Pawn";
    }
}
