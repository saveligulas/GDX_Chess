package board;

import error.*;
import model.PieceInterface;
import model.Piece_Type;
import pieces.Pawn;

import java.util.Objects;

public class PieceMovementLogic implements PieceInterface {
    boolean targetTileHasOpposingPiece = false;
    byte targetIndex;
    byte selectionIndex;
    boolean moveIsDiagonal;
    boolean upwards;
    boolean leftwards;

    public ChessBoard returnUpdatedBoard(ChessBoard board, byte selectionIndex, byte targetIndex) {
        Tile selectedTile = getLogicTileFromBoard(board, selectionIndex);
        Tile targetTile = getLogicTileFromBoard(board, targetIndex);
        moveIsDiagonal = TileCalculator.isDiagonal(selectionIndex, targetIndex);
        upwards = board.isMoveOrderWhite();
        leftwards = moveIsDiagonal && TileCalculator.isLeftwards(selectionIndex, targetIndex);


        try {
            checkTargetedAndSelectedTile(board, selectedTile, targetTile);
            checkIfTargetedTileIsAccessible(board, selectedTile, targetTile);
            board.performMove(selectionIndex, targetIndex);
        } catch (InvalidTileSelectionException e) { //TODO ADD custom return Statements to give info to player
            return null;
        } catch (InvalidTargetedTileException e) {
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }

        return board;
    }


    private void checkIfTargetedTileIsAccessible(ChessBoard board, Tile selectedTile, Tile targetTile) {
        Piece_Type piece_type = selectedTile.getPieceOnTile().getType();

        try {
            if (piece_type == Piece_Type.PAWN) {
                checkIfPawnCanMove(board, selectedTile, targetTile);
            }

            if (piece_type == Piece_Type.KING) {
                checkIfKingCanMove(board, selectedTile, targetTile);
            }

            if (piece_type == Piece_Type.KNIGHT) {
                checkIfKnightCanMove(board, selectedTile, targetTile);
            }

            if (piece_type == Piece_Type.BISHOP) {
                checkIfBishopCanMove(board, selectedTile, targetTile);
            }

            if (piece_type == Piece_Type.ROOK) {
                checkIfRookCanMove(board, selectedTile, targetTile);
            }

            if (piece_type == Piece_Type.QUEEN) {
                checkIfQueenCanMove(board, selectedTile, targetTile);
            }
        } catch (PieceUnableToReachTileException e) {
            return;
        } catch (PieceMoveCausesCheckException e) {
            return;
        }
    }

    private void checkIfPawnCanMove(ChessBoard board, Tile selectedTile, Tile targetTile) {
        if (TileCalculator.getIndexFromDiagonalMove(upwards, leftwards, (byte) 1, selectionIndex) == targetIndex) {
            if (!targetTileHasOpposingPiece) {
                throw new PieceUnableToReachTileException();
            }
            return;
        }
        if (TileCalculator.getIndexFromVerticalMove(upwards, (byte) 1, selectionIndex) == targetIndex) {
            if (targetTileHasOpposingPiece) {
                throw new PieceUnableToReachTileException();
            }
            return;
        }
        if (TileCalculator.getIndexFromVerticalMove(upwards, (byte) 2, selectionIndex) == targetIndex &&
                selectedTile.getPieceOnTile() instanceof Pawn && ((Pawn) selectedTile.getPieceOnTile()).getPawnHasMoved()) {
            byte[] tileToCheck = TileCalculator.getIndexesBetweenVerticalMove(upwards, (byte) 2, selectionIndex);
            if (board.getTiles()[Objects.requireNonNull(tileToCheck)[0]].getPieceOnTile() != null) {
                throw new PieceBetweenTilesException();
            }
            return;
        }
        throw new PieceUnableToReachTileException();
    }

    private void checkIfKingCanMove(ChessBoard board, Tile selectedTile, Tile targetTile) {

    }

    private void checkIfKnightCanMove(ChessBoard board, Tile selectedTile, Tile targetTile) {

    }

    private void checkIfBishopCanMove(ChessBoard board, Tile selectedTile, Tile targetTile) {

    }

    private void checkIfRookCanMove(ChessBoard board, Tile selectedTile, Tile targetTile) {

    }

    private void checkIfQueenCanMove(ChessBoard board, Tile selectedTile, Tile targetTile) {

    }

    private Tile getLogicTileFromBoard(ChessBoard board, byte index) {
        Tile[] tiles = board.getTiles();
        try {
            return tiles[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CoordinateOutOfBoundsException();
        }
    }

    private void checkTargetedAndSelectedTile(ChessBoard board, Tile selectedTile, Tile targetTile) {
        if (selectedTile.getPieceOnTile().isColorWhite() != board.isMoveOrderWhite()) {
            throw new InvalidTileSelectionException();
        }
        if (targetTile.getPieceOnTile().isColorWhite() == board.isMoveOrderWhite()) {
            throw new InvalidTargetedTileException();
        }
        if (targetTile.getPieceOnTile() != null) {
            targetTileHasOpposingPiece = true;
        }
    }

    @Override
    public char[] twoCharPieceInsigne() {
        return new char[0];
    }

    @Override
    public void removeFromBoard() {
    }
}
