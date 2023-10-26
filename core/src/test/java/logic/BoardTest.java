package logic;

import board.ChessBoard;
import board.Tile;
import board.TileCalculator;
import org.junit.Before;
import org.junit.Test;
import pieces.Pawn;
import pieces.Rook;

import static org.junit.Assert.assertEquals;

public class BoardTest {
    static ChessBoard board;

    @Before
    public void setup() {
        board = new ChessBoard();
    }

    @Test
    public void boardAndTileSetCalculationTest() {
        byte correctTilesFound = 0;

        for(Tile tile : board.getTiles()) {
            byte x = tile.getX_coordinate();
            byte y = tile.getY_coordinate();

            byte calIndex = TileCalculator.getIndex(x, y);
            Tile calTile = board.getTiles()[calIndex];
            if (calTile.equals(tile)) {
                correctTilesFound++;
            }
        }
        assertEquals(64, correctTilesFound);
    }

    @Test
    public void testBoardMoves() {
        board.performMove((byte) 0, (byte) 12);
        assertEquals(board.getRemovedPieces().get(0).getClass(), Pawn.class);
        assertEquals(board.getTiles()[12].getPieceOnTile().getClass(), Rook.class);
    }
}
