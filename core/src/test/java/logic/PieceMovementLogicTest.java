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
        assertEquals(tiles[3].getPieceOnTile().getType(), Piece_Type.QUEEN);
        assertEquals(tiles[59].getPieceOnTile().getType(), Piece_Type.QUEEN);
    }

    @Test
    public void testBishopPosition() {
        assertEquals(tiles[2].getPieceOnTile().getType(), Piece_Type.BISHOP);
        assertEquals(tiles[58].getPieceOnTile().getType(), Piece_Type.BISHOP);
    }
}
