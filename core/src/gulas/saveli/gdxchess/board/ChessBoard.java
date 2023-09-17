package board;

import lombok.Data;

@Data
public class ChessBoard {
    private final LogicTile[] logicTiles;

    private boolean moveOrderIsWhite = true;

    public ChessBoard() {
        this.logicTiles = LogicTile.getStandard64Tiles();
    }
}
