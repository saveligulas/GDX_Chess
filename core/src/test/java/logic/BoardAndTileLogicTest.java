package logic;

import board.ChessBoard;
import board.LogicTile;
import board.LogicTileCalculator;
import model.Piece_Type;
import org.junit.Before;
import org.junit.Test;
import pieces.Pawn;
import pieces.Rook;

import static org.junit.Assert.assertEquals;

public class BoardAndTileLogicTest {
    static ChessBoard board;

    @Before
    public void setup() {
        board = new ChessBoard();
    }

    @Test
    public void boardAndTileSetCalculationTest() {
        byte correctTilesFound = 0;

        for(LogicTile logicTile : board.getLogicTiles()) {
            byte x = logicTile.getX_coordinate();
            byte y = logicTile.getY_coordinate();

            byte calIndex = LogicTileCalculator.getIndex(x, y);
            LogicTile calLogicTile = board.getLogicTiles()[calIndex];
            if (calLogicTile.equals(logicTile)) {
                correctTilesFound++;
            }
        }
        assertEquals(64, correctTilesFound);
    }

    @Test
    public void testBoardMoves() {
        board.performMove((byte) 0, (byte) 12);
        assertEquals(board.getRemovedPieces().get(0).getClass(), Pawn.class);
        assertEquals(board.getLogicTiles()[12].getPieceOnTile().getClass(), Rook.class);
    }
}
