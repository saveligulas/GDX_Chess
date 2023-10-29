package error;

import pieces.Pawn;

public class PawnHasMovedException extends RuntimeException {
    public PawnHasMovedException() {
        super("Pawn has already moved");
    }
}
