package pieces;

import board.ChessBoard;
import board.LogicTile;
import error.InvalidTargetedTileException;
import error.InvalidTileSelectionException;
import model.PieceInterface;
import model.Piece_Type;

public class PieceMovement implements PieceInterface {

    public ChessBoard returnUpdatedBoard(ChessBoard board, byte x_coordinate_selection, byte y_coordinate_selection, byte x_coordinate_target, byte y_coordinate_target ) {
        boolean playerIsWhite = board.isMoveOrderIsWhite();

        try {
            pieceOnSelectedTileHasPlayersColorAndTileIsNotEmpty(playerIsWhite, board, x_coordinate_selection, y_coordinate_selection);
            Piece_Type piece_type = getPieceTypeOfSelectedTile(board, x_coordinate_selection, y_coordinate_selection);
            checkIfTargetedTileIsAccessible(piece_type, board, LogicTile.getIndex(x_coordinate_selection, y_coordinate_selection), LogicTile.getIndex(x_coordinate_target, y_coordinate_target));
        } catch (InvalidTileSelectionException e) { //TODO ADD custom return Statements to give info to player
            System.out.println(e.getMessage());
            return null;
        } catch (InvalidTargetedTileException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return board;
    }

    private void pieceOnSelectedTileHasPlayersColorAndTileIsNotEmpty(boolean colorIsWhite, ChessBoard board, byte x_coordinate_selection, byte y_coordinate_selection) {
        LogicTile[] boardLogicTiles = board.getLogicTiles();
        LogicTile selectedLogicTile = boardLogicTiles[LogicTile.getIndex(x_coordinate_selection, y_coordinate_selection)];
        if (selectedLogicTile.getPieceOnTile().isColorIsWhite() != colorIsWhite) {
            throw new InvalidTileSelectionException();
        }
    }

    private Piece_Type getPieceTypeOfSelectedTile(ChessBoard board, byte x_coordinate_selection, byte y_coordinate_selection) {
        LogicTile[] boardLogicTiles = board.getLogicTiles();
        LogicTile selectedLogicTile = boardLogicTiles[LogicTile.getIndex(x_coordinate_selection, y_coordinate_selection)];
        if (selectedLogicTile.getPieceOnTile() == null) {
            throw new InvalidTileSelectionException();
        }
        return selectedLogicTile.getPieceOnTile().getType();
    }

    private void checkIfTargetedTileIsAccessible(Piece_Type piece_type, ChessBoard board, byte selectionIndex, byte targetIndex) {
        if (piece_type == Piece_Type.PAWN) {

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
    }

    private void checkIfPawnCanMove(ChessBoard board, byte selectionIndex, byte targetIndex) {
        LogicTile[] logicTiles = board.getLogicTiles();
        LogicTile selectedLogicTile = logicTiles[selectionIndex];
        LogicTile targetedLogicTile = logicTiles[targetIndex];
    }

    @Override
    public void removeFromBoard() {
    }
}
