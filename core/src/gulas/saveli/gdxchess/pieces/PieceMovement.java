package pieces;

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
            LogicTile tile = getLogicTileFromBoard(board, selectionIndex);
            if (!checkIfTileHasSameColor(board, selectionIndex)) {
                throw new InvalidTileSelectionException();
            }
            Piece_Type piece_type = getPieceTypeOfSelectedTile(board, selectionIndex);
            checkIfTargetedTileIsAccessible(piece_type, board, selectionIndex, targetIndex);
        } catch (InvalidTileSelectionException e) { //TODO ADD custom return Statements to give info to player
            System.out.println(e.getMessage());
            return null;
        } catch (InvalidTargetedTileException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        return board;
    }

    private Piece_Type getPieceTypeOfSelectedTile(ChessBoard board, byte selectionIndex) {
        LogicTile selectedLogicTile = getLogicTileFromBoard(board, selectionIndex);
        if (selectedLogicTile.getPieceOnTile() == null) {
            throw new InvalidTileSelectionException();
        }
        return selectedLogicTile.getPieceOnTile().getType();
    }

    private void checkIfTargetedTileIsAccessible(Piece_Type piece_type, ChessBoard board, byte selectionIndex, byte targetIndex) {
        try {
            if (piece_type == Piece_Type.PAWN) {
                checkIfPawnCanMove(board, selectionIndex, targetIndex);
            }

            if (piece_type == Piece_Type.KING) {

            }

            if (piece_type == Piece_Type.KNIGHT) {

            }

            if (piece_type == Piece_Type.BISHOP) {

            }

            if (piece_type == Piece_Type.ROOK) {

            }

            if (piece_type == Piece_Type.QUEEN) {

            }
        } catch (PieceUnableToReachTileException e) {

        } catch (PieceMoveCausesCheckException e) {

        }
    }

    private void checkIfPawnCanMove(ChessBoard board, byte selectionIndex, byte targetIndex) {
        LogicTile[] logicTiles = board.getLogicTiles();
        LogicTile selectedLogicTile = logicTiles[selectionIndex];
        LogicTile targetedLogicTile = logicTiles[targetIndex];
        if (board.isMoveOrderWhite()) {

        } else {

        }
    }

    private void checkIfKingCanMove(ChessBoard board, byte selectionIndex, byte targetIndex) {

    }

    private LogicTile getLogicTileFromBoard(ChessBoard board, byte index) {
        LogicTile[] logicTiles = board.getLogicTiles();
        try {
            return logicTiles[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CoordinateOutOfBoundsException();
        }
    }

    private boolean checkIfTileHasSameColor(ChessBoard board, byte index) {
        LogicTile tile = getLogicTileFromBoard(board, index);
        if (tile.getPieceOnTile() == null) {
            throw new InvalidTileSelectionException();
        }
        return tile.getPieceOnTile().isColorIsWhite() == board.isMoveOrderWhite();
    }
    @Override
    public void removeFromBoard() {
    }
}
