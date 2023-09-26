package board;

import board.ChessBoard;
import board.LogicTile;
import error.*;
import jdk.jpackage.internal.Log;
import lombok.Setter;
import model.PieceInterface;
import model.Piece_Type;

public class PieceMovement implements PieceInterface {
    boolean targetTileHasOpposingPiece = false;
    byte targetIndex;
    byte selectionIndex;
    boolean moveIsDiagonal;
    boolean upwards;
    boolean leftwards;

    public ChessBoard returnUpdatedBoard(ChessBoard board, LogicTile selectedTile, LogicTile targetTile) {
        selectionIndex = selectedTile.getIndex();
        targetIndex = targetTile.getIndex();
        moveIsDiagonal = LogicTileCalculator.isDiagonal(selectionIndex, targetIndex);
        upwards = board.isMoveOrderWhite();
        leftwards = moveIsDiagonal && LogicTileCalculator.isLeftwards(selectionIndex, targetIndex);


        try {
            checkTargetedAndSelectedTile(board, selectedTile, targetTile);
            checkIfTargetedTileIsAccessible(selectedTile.getPieceOnTile().getType(), board, selectedTile, targetTile);
            board.performMove(selectedTile, targetTile);
        } catch (InvalidTileSelectionException e) { //TODO ADD custom return Statements to give info to player
            return null;
        } catch (InvalidTargetedTileException e) {
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }

        return board;
    }


    private void checkIfTargetedTileIsAccessible(Piece_Type piece_type, ChessBoard board, LogicTile selectedTile, LogicTile targetTile) {
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

    private void checkIfPawnCanMove(ChessBoard board, LogicTile selectedTile, LogicTile targetTile) {
        if (LogicTileCalculator.getIndexFromDiagonalMove(upwards, leftwards, (byte) 1, selectionIndex) == targetIndex) {
            if (!targetTileHasOpposingPiece) {
                throw new PieceUnableToReachTileException();
            }
            return;
        }
        if ()
    }

    private void checkIfKingCanMove(ChessBoard board, LogicTile selectedTile, LogicTile targetTile) {

    }

    private void checkIfKnightCanMove(ChessBoard board, LogicTile selectedTile, LogicTile targetTile) {

    }

    private void checkIfBishopCanMove(ChessBoard board, LogicTile selectedTile, LogicTile targetTile) {

    }

    private void checkIfRookCanMove(ChessBoard board, LogicTile selectedTile, LogicTile targetTile) {

    }

    private void checkIfQueenCanMove(ChessBoard board, LogicTile selectedTile, LogicTile targetTile) {

    }

    private LogicTile getLogicTileFromBoard(ChessBoard board, byte index) {
        LogicTile[] logicTiles = board.getLogicTiles();
        try {
            return logicTiles[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CoordinateOutOfBoundsException();
        }
    }

    private void checkTargetedAndSelectedTile(ChessBoard board, LogicTile selectedTile, LogicTile targetTile) {
        if (selectedTile.getPieceOnTile().isColorIsWhite() != board.isMoveOrderWhite()) {
            throw new InvalidTileSelectionException();
        }
        if (targetTile.getPieceOnTile().isColorIsWhite() == board.isMoveOrderWhite()) {
            throw new InvalidTargetedTileException();
        }
        if (targetTile.getPieceOnTile().isColorIsWhite() != board.isMoveOrderWhite() && targetTile.getPieceOnTile() != null) {
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
