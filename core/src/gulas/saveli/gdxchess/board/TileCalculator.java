package board;

import error.CoordinateOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

public class TileCalculator {
    public static byte[] getCoordinatesXandY(byte index) {
        int row = index / 8;
        int col = index % 8;

        byte[] coordinates = new byte[2];
        coordinates[0] = (byte) col;
        coordinates[1] = (byte) row;

        return coordinates;
    }

    public static byte getIndex(byte x, byte y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return -1;
        }
        return (byte) (y * 8 + x);
    }

    public static boolean isLeftwards(byte selectedIndex, byte targetIndex) {
        return (getCoordinatesXandY(selectedIndex)[0] >= getCoordinatesXandY(targetIndex)[1]);
    }

    public static boolean isDiagonal(byte selectedIndex, byte targetIndex) {
        return (getCoordinatesXandY(selectedIndex)[1] != getCoordinatesXandY(targetIndex)[1]);
    }

    public static boolean isUpwards(byte selectedIndex, byte targetIndex) {
        return (getCoordinatesXandY(selectedIndex)[1] >= getCoordinatesXandY(targetIndex)[1]);
    }

    public static byte getIndexFromDiagonalMove(boolean upwards, boolean leftwards, byte range, byte selectedIndex) {
        byte[] coordinates = getCoordinatesXandY(selectedIndex);

        coordinates[0] = (byte) (leftwards ? coordinates[0] - range : coordinates[0] + range);
        coordinates[1] = (byte) (upwards ? coordinates[1] + range : coordinates[1] - range);

        return getIndex(coordinates[0], coordinates[1]);
    }

    public static byte getIndexFromHorizontalMove(boolean leftwards, byte range, byte selectedIndex) {
        byte startingRow = getCoordinatesXandY(selectedIndex)[1];
        byte calculatedIndex = (byte) (leftwards ? selectedIndex - range : selectedIndex + range);
        byte calculatedRow = getCoordinatesXandY(calculatedIndex)[1];

        if (startingRow != calculatedRow) {
            throw new CoordinateOutOfBoundsException();
        }

        return calculatedIndex;
    }

    public static byte[] getIndexesBetweenLateralMove(boolean leftwards, byte range, byte selectedIndex) {
        if (range < 2) {
            return null;
        }
        byte[] indexes = new byte[range-1];
        for (byte i = 1; i < range; i++) {
            indexes[i] = getIndexFromHorizontalMove(leftwards, i, selectedIndex);
        }
        return indexes;
    }

    public static byte getIndexFromVerticalMove(boolean upwards, byte range, byte selectedIndex) {
        return (byte) (upwards ? selectedIndex + (8*range) : selectedIndex - (8*range));
    }

    public static byte[] getIndexesBetweenVerticalMove(boolean upwards, byte range, byte selectionIndex) {
        if (range < 2) {
            return null;
        }
        byte[] indexes = new byte[range-1];
        for (byte i = 1; i < range; i++) {
            indexes[i] = getIndexFromVerticalMove(upwards, i, selectionIndex);
        }
        return indexes;
    }



    public static byte[] getPossibleIndexForKnightMove(byte selectedIndex) {
        byte startingRow = getCoordinatesXandY(selectedIndex)[1];
        byte startingColumn = getCoordinatesXandY(selectedIndex)[0];
        byte[] indexes = new byte[8];

        indexes[0] = getIndex((byte) (startingColumn + 1), (byte) (startingRow + 2));
        indexes[1] = getIndex((byte) (startingColumn + 2), (byte) (startingRow + 1));
        indexes[2] = getIndex((byte) (startingColumn + 2), (byte) (startingRow - 1));
        indexes[3] = getIndex((byte) (startingColumn + 1), (byte) (startingRow - 2));
        indexes[4] = getIndex((byte) (startingColumn - 1), (byte) (startingRow - 2));
        indexes[5] = getIndex((byte) (startingColumn - 2), (byte) (startingRow - 1));
        indexes[6] = getIndex((byte) (startingColumn - 2), (byte) (startingRow + 1));
        indexes[7] = getIndex((byte) (startingColumn - 1), (byte) (startingRow + 2));

        List<Byte> allPossibleIndexes = new ArrayList<>();

        for (byte index : indexes) {
            if (index >= 0) {
                allPossibleIndexes.add(index);
            }
        }

        byte[] possibleIndexesArray = new byte[allPossibleIndexes.size()];

        for (int k = 0; k < possibleIndexesArray.length; k++) {
            possibleIndexesArray[k] = allPossibleIndexes.get(k);
        }

        return possibleIndexesArray;
    }
}
