package logic;

import board.ChessBoard;
import board.Tile;
import board.TileCalculator;
import board.PieceMovementLogic;
import error.InvalidTargetedTileException;
import error.InvalidTileSelectionException;
import error.PieceUnableToReachTileException;
import model.Piece;
import model.Piece_Type;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class PieceMovementTest {
    private static PieceMovementLogic pieceMovementLogic;
    private static Tile[] tiles;
    private static byte[] indexesToCheck;

    @Before
    public void setup() {
        ChessBoard chessBoard = new ChessBoard();
        pieceMovementLogic = new PieceMovementLogic(chessBoard);
        tiles = chessBoard.getTiles();
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
                indexesToCheck[i] = TileCalculator.getIndex((byte) i, (byte) 1);
            } else {
                indexesToCheck[i] = TileCalculator.getIndex((byte) ((byte) i/2), (byte) 6);
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
        for (byte i = 16; i < 48; i++) {
            assertEquals(InvalidTileSelectionException.class, pieceMovementLogic.updateBoard(i, i).getException().getClass());
        }
        for (byte k = 0; k < 16; k++) {
            assertEquals(InvalidTargetedTileException.class, pieceMovementLogic.updateBoard(k, k).getException().getClass());
        }
    }

    @Test
    public void testPawnMovement() {
        assertEquals(PieceUnableToReachTileException.class, pieceMovementLogic.updateBoard((byte) 8, (byte) 32).getException().getClass());

        assertNull(pieceMovementLogic.updateBoard((byte) 10, (byte) 18).getException());
        assertEquals(Piece_Type.PAWN, pieceMovementLogic.getBoard().getTiles()[(byte) 18].getPieceOnTile().getType());

        assertNull(pieceMovementLogic.updateBoard((byte) 9, (byte) 25).getException());
        assertEquals(Piece_Type.PAWN, pieceMovementLogic.getBoard().getTiles()[(byte) 25].getPieceOnTile().getType());

        pieceMovementLogic.updateBoard((byte) 63, (byte) 17);
        assertNull(pieceMovementLogic.updateBoard((byte) 8, (byte) 17).getException());
        assertEquals(Piece_Type.PAWN, pieceMovementLogic.getBoard().getTiles()[(byte) 17].getPieceOnTile().getType());

        pieceMovementLogic.updateBoard((byte) 55, (byte) 22);
        assertNull(pieceMovementLogic.updateBoard((byte) 15, (byte) 22).getException());
        assertEquals(Piece_Type.PAWN, pieceMovementLogic.getBoard().getTiles()[(byte) 22].getPieceOnTile().getType());

        assertNull(pieceMovementLogic.updateBoard((byte) 52, (byte) 44).getException());
        assertNull(pieceMovementLogic.updateBoard((byte) 53, (byte) 37).getException());

    }
}
