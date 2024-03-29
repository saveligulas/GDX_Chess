package board;

import error.*;
import lombok.Getter;
import model.CustomMessage;
import model.PieceInterface;
import model.Piece_Type;
import pieces.Pawn;

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
    private final ChessBoard board;

    public PieceMovementLogic(ChessBoard board) {
        this.board = board;
    }

    public void movePiece(byte selectionIndex, byte targetIndex) {
        board.performMove(selectionIndex, targetIndex);
    }

    public CustomMessage updateBoard(byte selectionIndex, byte targetIndex) { //TODO use TileMoveData and Calculator to simplify and cleanup method/class
        this.targetIndex = targetIndex;
        this.selectionIndex = selectionIndex;
        this.selectedTile = getLogicTileFromBoard(selectionIndex);
        this.targetTile = getLogicTileFromBoard(targetIndex);
        this.moveIsDiagonal = TileCalculator.isDiagonal(selectionIndex, targetIndex);
        this.upwards = TileCalculator.isUpwards(selectionIndex, targetIndex);
        this.leftwards = moveIsDiagonal && TileCalculator.isLeftwards(selectionIndex, targetIndex);

        CustomMessage message = new CustomMessage();

        try {
            checkTargetedAndSelectedTile();
            checkIfTargetedTileIsAccessible();
            board.performMove(selectionIndex, targetIndex);
        } catch (RuntimeException e) {
            message.setException(e);
            return message;
        }

        message.setException(null);
        message.setMessage("Successful");

        return message;
    }

    private void checkIfTargetedTileIsAccessible() {
        Piece_Type piece_type = selectedTile.getPieceOnTile().getType();

        if (piece_type == Piece_Type.PAWN) {
            checkIfPawnCanMove();
        }

        if (piece_type == Piece_Type.KING) {
            checkIfKingCanMove();
        }

        if (piece_type == Piece_Type.KNIGHT) {
            checkIfKnightCanMove();
        }

        if (piece_type == Piece_Type.BISHOP) {
            checkIfBishopCanMove();
        }

        if (piece_type == Piece_Type.ROOK) {
            checkIfRookCanMove();
        }

        if (piece_type == Piece_Type.QUEEN) {
            checkIfQueenCanMove();
        }

    }

    private void checkIfPawnCanMove() {
        if (TileCalculator.getIndexFromVerticalMove(upwards, (byte) 1, selectionIndex) != targetIndex && 
                TileCalculator.getIndexFromVerticalMove(upwards, (byte) 2, selectionIndex) != targetIndex &&
                    !targetTileHasOpposingPiece) {
            throw new PieceUnableToReachTileException();
        }
        if (TileCalculator.getIndexFromVerticalMove(upwards, (byte) 2, selectionIndex) == targetIndex) {
            byte tileToCheck = TileCalculator.getIndexFromVerticalMove(upwards, (byte) 1, selectionIndex);
            if (board.getTiles()[tileToCheck].getPieceOnTile() != null) {
                throw new PieceBetweenTilesException();
            }
            if (selectedTile.getPieceOnTile() instanceof Pawn && ((Pawn) selectedTile.getPieceOnTile()).getPawnHasMoved()) {
                throw new PawnHasMovedException();
            }
        }
        if (targetTileHasOpposingPiece &&
                TileCalculator.getIndexFromDiagonalMove(upwards, leftwards, (byte) 1, selectionIndex) != targetIndex) {
            throw new PieceUnableToReachTileException();
        }
    }

    private void checkIfKingCanMove() {

    }

    private void checkIfKnightCanMove() {

    }

    private void checkIfBishopCanMove() {

    }

    private void checkIfRookCanMove() {

    }

    private void checkIfQueenCanMove() {

    }

    private Tile getLogicTileFromBoard(byte index) {
        Tile[] tiles = board.getTiles();
        try {
            return tiles[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CoordinateOutOfBoundsException();
        }
    }

    private void checkTargetedAndSelectedTile() {
        if (selectedTile.getPieceOnTile() == null) { //TODO integrate back: || selectedTile.getPieceOnTile().isColorWhite() != board.isMoveOrderWhite()
            throw new InvalidTileSelectionException();
        }
        if (targetTile.getPieceOnTile() == null) {
            this.targetTileHasOpposingPiece = false;
            return;
        }
        if (targetTile.getPieceOnTile().isColorWhite() == board.isMoveOrderWhite()) {
            throw new InvalidTargetedTileException();
        }
        this.targetTileHasOpposingPiece = true;
    }

    @Override
    public char[] twoCharPieceInsigne() {
        return new char[0];
    }

    @Override
    public void removeFromBoard() {
    }
}