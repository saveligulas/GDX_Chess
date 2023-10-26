package logic;

import board.ChessBoard;
import board.LogicTile;
import board.LogicTileCalculator;
import board.PieceMovementLogic;
import model.Piece_Type;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class PieceMovementLogicLogicTest { //TODO extend method to also check for correct color
    private static ChessBoard chessBoard;
    private static PieceMovementLogic pieceMovementLogic;
    private static LogicTile[] tiles;
    private static byte[] indexesToCheck;

    @BeforeClass
    public static void setup() {
        chessBoard = new ChessBoard();
        pieceMovementLogic = new PieceMovementLogic();
    }

    @Before
    public void setupLogicTilesAndIndexes() {
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

    @Test
    public void testRookPosition() {
        indexesToCheck = new byte[] {0, 7, 56, 63};
        for (byte index : indexesToCheck) {
            assertEquals(tiles[index].getPieceOnTile().getType(), Piece_Type.ROOK);
        }
    }

    @Test
    public void testPawnPosition() {
        indexesToCheck = new byte[16];
        for (int i = 0; i < indexesToCheck.length; i++) {
            if (i < 8) {
                indexesToCheck[i] = LogicTileCalculator.getIndex((byte) i, (byte) 1);
            } else {
                indexesToCheck[i] = LogicTileCalculator.getIndex((byte) ((byte) i/2), (byte) 6);
            }
        }
        for (byte index : indexesToCheck) {
            assertEquals(tiles[index].getPieceOnTile().getType(), Piece_Type.PAWN);
        }
    }

    @Test
    public void testColorOfPieces() {
        byte[] whitePieceIndexes = new byte[16];
        byte[] blackPieceIndexes = new byte[16];

        for (byte k = 0; k < whitePieceIndexes.length; k++) {
            whitePieceIndexes[k] = k;
            blackPieceIndexes[k] = (byte) (k + 48);
        }

        for (byte i = 0; i < whitePieceIndexes.length; i++) {
            assertTrue(tiles[whitePieceIndexes[i]].getPieceOnTile().isColorWhite());
            assertFalse(tiles[blackPieceIndexes[i]].getPieceOnTile().isColorWhite());
        }
    }

    @Test
    public void testCheckMethods() {

    }

    @Test
    public void testPawnMovement() {

    }
}
