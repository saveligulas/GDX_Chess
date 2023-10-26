package board;

import lombok.Data;
import model.Piece;

import java.sql.Array;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Data
public class ChessBoard {
    private final LogicTile[] logicTiles;

    private boolean moveOrderWhite = true;

    private List<Piece> removedPieces = new ArrayList<>();

    public ChessBoard() {
        this.logicTiles = LogicTile.getStandard64Tiles();
    }

    public void performMove(byte selectedIndex, byte targetedIndex) {
        Piece selectedPiece = logicTiles[selectedIndex].getPieceOnTile();
        if (logicTiles[targetedIndex].getPieceOnTile() != null) {
            removedPieces.add(logicTiles[targetedIndex].getPieceOnTile());
        }
        logicTiles[targetedIndex].setPieceOnTile(selectedPiece);
        logicTiles[selectedIndex].setPieceOnTile(null);
    }
}
