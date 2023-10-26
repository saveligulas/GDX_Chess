package board;

import error.NoPieceToPlaceOnTileException;
import lombok.Data;
import model.Piece;
import pieces.*;

@Data
public class Tile {
    //TODO override setters and add limits
    private byte x_coordinate;
    private byte y_coordinate;

    private Piece pieceOnTile = null;

    public byte getIndex() {
        return TileCalculator.getIndex(this.x_coordinate, this.y_coordinate);
    }

    public static Tile[] getStandard64Tiles() {
        Tile[] tiles = new Tile[64];
        for (byte rows = 0; rows < 8; rows++) {
            for (byte columns = 0; columns < 8; columns++) {
                byte index = (byte) ((rows*8)+columns);
                Tile tile = new Tile();
                tile.setY_coordinate(rows);
                tile.setX_coordinate(columns);
                try {
                    Piece piece = checkIndexForPossiblePiece(index, columns, rows);
                    tile.setPieceOnTile(piece);
                } catch (NoPieceToPlaceOnTileException ignored) {}
                tiles[index] = tile;
            }
        }
        return tiles;
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
