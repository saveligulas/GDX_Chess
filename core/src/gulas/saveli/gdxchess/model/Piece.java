package model;

import error.CoordinateOutOfBoundsException;
import lombok.Data;

@Data
public class Piece implements PieceInterface {
    private Piece_Type type;
    private boolean colorIsWhite;
    //TODO override setters of bytes to set ranges
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

    public String getInsigne() {
        StringBuilder sb = new StringBuilder();
        char[] ins = twoCharPieceInsigne();
        sb.append(ins[0]);
        sb.append(ins[1]);
        return sb.toString();
    }

    @Override
    public char[] twoCharPieceInsigne() {
        char color;
        char sign = 'e';
        if (colorIsWhite) {
            color = 'w';
        } else {
            color = 'b';
        }
        char[] insigne = new char[2];
        insigne[0] = color;

        if (type == Piece_Type.PAWN) {
            sign = 'P';
        }
        if (type == Piece_Type.KING) {
            sign = 'K';
        }
        if (type == Piece_Type.KNIGHT) {
            sign = 'N';
        }
        if (type == Piece_Type.ROOK) {
            sign = 'R';
        }
        if (type == Piece_Type.BISHOP) {
            sign = 'B';
        }
        if (type == Piece_Type.QUEEN) {
            sign = 'Q';
        }
        insigne[1] = sign;
        return insigne;
    }

    @Override
    public void removeFromBoard() {
        this.column = -1;
        this.row = -1;
    }
}
