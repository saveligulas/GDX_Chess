package board;

import error.*;
import lombok.Getter;
import model.CustomMessage;
import model.PieceInterface;
import model.Piece_Type;
import pieces.Pawn;

import java.util.Objects;

@Getter
public class PieceMovementLogic implements PieceInterface {
    private boolean targetTileHasOpposingPiece = false;
    private byte targetIndex;
    private byte selectionIndex;
    private boolean moveIsDiagonal;
    private boolean upwards;
    private boolean leftwards;

    private Tile selectedTile;
    private Tile targetTile;
    private ChessBoard board;

    public CustomMessage updateBoard(ChessBoard board, byte selectionIndex, byte targetIndex) { //TODO use TileMoveData and Calculator to simplify and cleanup method/class
        this.targetIndex = targetIndex;
        this.selectionIndex = selectionIndex;
        this.selectedTile = getLogicTileFromBoard(board, selectionIndex);
        this.targetTile = getLogicTileFromBoard(board, targetIndex);
        this.moveIsDiagonal = TileCalculator.isDiagonal(selectionIndex, targetIndex);
        this.upwards = board.isMoveOrderWhite();
        this.leftwards = moveIsDiagonal && TileCalculator.isLeftwards(selectionIndex, targetIndex);
        this.board = board;

        CustomMessage message = new CustomMessage();

        try {
            checkTargetedAndSelectedTile(board, selectedTile, targetTile);
            checkIfTargetedTileIsAccessible(board, selectedTile, targetTile);
            board.performMove(selectionIndex, targetIndex);
        } catch (InvalidTileSelectionException e) { //TODO ADD custom return Statements to give info to player
            message.setException(e);
            return message;
        } catch (InvalidTargetedTileException e) {
            message.setException(e);
            return message;
        } catch (ArrayIndexOutOfBoundsException e) { //TODO override setters of LogicTiles/Pieces to not allow invalid values and throw exception
            message.setException(e);
            return message;
        }

        message.setException(null);
        message.setMessage("Successful");

        return message;
    }


    private void checkIfTargetedTileIsAccessible() {
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

    private void checkIfPawnCanMove() {
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

    private void checkIfKingCanMove() {

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
