package board;

import board.ChessBoard;
import board.LogicTile;
import error.*;
import jdk.jpackage.internal.Log;
import model.PieceInterface;
import model.Piece_Type;

public class PieceMovement implements PieceInterface {

    public ChessBoard returnUpdatedBoard(ChessBoard board, byte x_coordinate_selection, byte y_coordinate_selection, byte x_coordinate_target, byte y_coordinate_target ) {
        byte selectionIndex = LogicTile.getIndex(x_coordinate_selection, y_coordinate_selection);
        byte targetIndex = LogicTile.getIndex(x_coordinate_target, y_coordinate_target);

        try {
            LogicTile selectedTile = getLogicTileFromBoard(board, selectionIndex);
            LogicTile targetTile = getLogicTileFromBoard(board, targetIndex);
            checkTargetedAndSelectedTile(board, selectedTile, targetTile);
            checkIfTargetedTileIsAccessible(selectedTile.getPieceOnTile().getType(), board, selectedTile, targetTile);
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
        if (board.isMoveOrderWhite()) {
            return;
        } else {
            return;
        }
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
    }
    @Override
    public void removeFromBoard() {
    }
}
