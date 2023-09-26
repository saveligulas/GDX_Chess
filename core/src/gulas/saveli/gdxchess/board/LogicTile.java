package board;

import error.NoPieceToPlaceOnTileException;
import lombok.Data;
import model.Piece;
import model.Piece_Type;
import pieces.*;

@Data
public class LogicTile {
    //TODO override setters and add limits
    private byte x_coordinate;
    private byte y_coordinate;

    private Piece pieceOnTile = null;

    public byte getIndex() {
        return LogicTileCalculator.getIndex(this.x_coordinate, this.y_coordinate);
    }

    public static LogicTile[] getStandard64Tiles() {
        LogicTile[] logicTiles = new LogicTile[64];
        for (byte rows = 0; rows < 8; rows++) {
            for (byte columns = 0; columns < 8; columns++) {
                byte index = (byte) ((rows*8)+columns);
                LogicTile logicTile = new LogicTile();
                logicTile.setY_coordinate(rows);
                logicTile.setX_coordinate(columns);
                try {
                    Piece piece = checkIndexForPossiblePiece(index, columns, rows);
                    logicTile.setPieceOnTile(piece);
                } catch (NoPieceToPlaceOnTileException ignored) {}
                logicTiles[index] = logicTile;
            }
        }
        return logicTiles;
    }

    private static Piece checkIndexForPossiblePiece(byte index, byte column, byte row) {
        if (row == 1) {
            return new Pawn(true, column);
        }
        if (row == 6) {
            return new Pawn(false, column);
        }

        if (index == 4) {
            return new King(true);
        }
        if (index == 60) {
            return new King(false);
        }

        if (index == 1 || index == 6) {
            return new Knight(true, column);
        }
        if (index == 57 || index == 62) {
            return new Knight(false, column);
        }

        if (index == 0 || index == 7) {
            return new Rook(true, column);
        }
        if (index == 56 || index == 63) {
            return new Rook(false, column);
        }

        if (index == 2 || index == 5) {
         return new Bishop(true, column);
        }
        if (index == 58 || index == 61) {
            return new Bishop(false, column);
        }

        if (index == 3) {
            return new Queen(true);
        }
        if (index == 59) {
            return new Queen(false);
        }
        throw new NoPieceToPlaceOnTileException();
    }
}
