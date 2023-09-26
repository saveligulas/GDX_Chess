package board;

import lombok.Data;

@Data
public class ChessBoard {
    private final LogicTile[] logicTiles;

    private boolean moveOrderWhite = true;

    public ChessBoard() {
        this.logicTiles = LogicTile.getStandard64Tiles();
    }

    public void performMove(LogicTile tileWithPieceToMove, LogicTile targetedTile) {

    }
}
