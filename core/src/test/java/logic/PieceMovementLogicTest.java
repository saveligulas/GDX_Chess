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

    @BeforeClass
    public static void setUpChessBoard() {
        chessBoard = new ChessBoard();
    }

    @Before
    public void setUpLogicTiles() {
        tiles = chessBoard.getLogicTiles();
    }

    @Test
    public void testKingPosition() {
        assertEquals(tiles[4].getPieceOnTile().getType(), Piece_Type.KING);
        assertEquals(tiles[60].getPieceOnTile().getType(), Piece_Type.KING);
    }

    @Test
    public void testQueenPosition() {
        assertEquals(tiles[3].getPieceOnTile().getType(), Piece_Type.QUEEN);
        assertEquals(tiles[59].getPieceOnTile().getType(), Piece_Type.QUEEN);
    }

    @Test
    public void testBishopPosition(LogicTile[] tiles) {
        assertEquals(tiles[2].getPieceOnTile().getType(), Piece_Type.BISHOP);
        assertEquals(tiles[58].getPieceOnTile().getType(), Piece_Type.BISHOP);
    }
}
