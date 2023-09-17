package logic;

import board.ChessBoard;
import board.LogicTile;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardAndTileLogicTest {
    @Test
    public void boardAndTileSetCalculationTest() {
        ChessBoard board = new ChessBoard();
        byte correctTilesFound = 0;

        for(LogicTile logicTile : board.getLogicTiles()) {
            byte x = logicTile.getX_coordinate();
            byte y = logicTile.getY_coordinate();

            byte calIndex = LogicTile.getIndex(x, y);
            LogicTile calLogicTile = board.getLogicTiles()[calIndex];
            if (calLogicTile.equals(logicTile)) {
                correctTilesFound++;
            }
        }
        assertEquals(64, correctTilesFound);
    }

    @Test
    public void
}
