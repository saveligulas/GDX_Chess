package board;

import lombok.Data;
import model.Piece;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChessBoard {
    private final Tile[] tiles;

    private boolean moveOrderWhite = true;

    private List<Piece> removedPieces = new ArrayList<>();

    public ChessBoard() {
        this.tiles = Tile.getStandard64Tiles();
    }

    public void performMove(byte selectedIndex, byte targetedIndex) {
        Piece selectedPiece = tiles[selectedIndex].getPieceOnTile();
        if (tiles[targetedIndex].getPieceOnTile() != null) {
            removedPieces.add(tiles[targetedIndex].getPieceOnTile());
        }
        tiles[targetedIndex].setPieceOnTile(selectedPiece);
        tiles[selectedIndex].setPieceOnTile(null);
    }
}
