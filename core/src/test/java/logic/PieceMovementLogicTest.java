package logic;

import board.ChessBoard;
import board.LogicTile;
import model.Piece_Type;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PieceMovementLogicTest { //TODO extend method to also check for correct color
    private static ChessBoard chessBoard;
    private static LogicTile[] tiles;
    private static byte[] indexesToCheck;

    @BeforeClass
    public static void setUpChessBoard() {
        chessBoard = new ChessBoard();
    }

    @Before
    public void setUpLogicTilesAndIndexes() {
        tiles = chessBoard.getLogicTiles();
        indexesToCheck = null;
    }

    @Test
    public void testKingPosition() {
        indexesToCheck = new byte[] {4, 60};
        for (byte index : indexesToCheck) {
            assertEquals(tiles[index].getPieceOnTile().getType(), Piece_Type.KING);
        }
    }

    @Test
    public void testQueenPosition() {
        indexesToCheck = new byte[] {3, 59};
        for (byte index : indexesToCheck) {
            assertEquals(tiles[index].getPieceOnTile().getType(), Piece_Type.QUEEN);
        }
    }

    @Test
    public void testBishopPosition() {
        indexesToCheck = new byte[] {2, 5, 58, 61};
        for (byte index : indexesToCheck) {
            assertEquals(tiles[index].getPieceOnTile().getType(), Piece_Type.BISHOP);
        }
    }

    @Test
    public void testKnightPosition() {
        indexesToCheck = new byte[] {1, 6, 57, 62};
        for (byte index : indexesToCheck) {
            assertEquals(tiles[index].getPieceOnTile().getType(), Piece_Type.KNIGHT);
        }
    }
}
