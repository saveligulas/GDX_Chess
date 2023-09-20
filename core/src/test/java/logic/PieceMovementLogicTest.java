package logic;

import board.ChessBoard;
import board.LogicTile;
import model.Piece_Type;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PieceMovementLogicTest {
    private static ChessBoard chessBoard;

    @BeforeClass
    public static void setUpChessBoard() {
        chessBoard = new ChessBoard();
    }

    @Test
    public void testKingPosition() {
        LogicTile[] tiles = chessBoard.getLogicTiles();
        assertEquals(tiles[4].getPieceOnTile().getType(), Piece_Type.KING);
    }
}
