package gulas.saveli.gdxchess.model;

import gulas.saveli.error.CoordinateOutOfBoundsException;
import lombok.Data;

@Data
public class Piece implements PieceInterface {
    private Piece_Type type;
    private boolean colorIsWhite;
    private byte column;
    private byte row;

    public Piece() {
    }

    public Piece(boolean colorIsWhite) {
        setColorIsWhite(colorIsWhite);
    }

    protected void movePiece(byte x_coordinate, byte y_coordinate) {
        if (x_coordinate > 0 && x_coordinate <= MAX_COORDINATE_VALUE && y_coordinate > 0 && y_coordinate <= MAX_COORDINATE_VALUE) {
            this.column = x_coordinate;
            this.row = y_coordinate;
        } else {
            throw new CoordinateOutOfBoundsException();
        }
    }

    @Override
    public void removeFromBoard() {
        this.column = -1;
        this.row = -1;
    }
}
