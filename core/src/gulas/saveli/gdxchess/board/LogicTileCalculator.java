package board;

public class LogicTileCalculator {
    public static byte[] getCoordinatesXandY(byte index) {
        int row = index / 8;
        int col = index % 8;

        byte[] coordinates = new byte[2];
        coordinates[0] = (byte) col;
        coordinates[1] = (byte) row;

        return coordinates;
    }

    public static byte getIndex(byte x, byte y) {
        return (byte) (y * 8 + x);
    }



    public static byte getIndexFromDiagonalMove(boolean upwards, byte range, byte )
}
