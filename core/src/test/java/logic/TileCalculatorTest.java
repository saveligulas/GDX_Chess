package logic;

import board.TileCalculator;
import error.CoordinateOutOfBoundsException;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TileCalculatorTest {
    static MoveData[] verticalData;
    static MoveData[] horizontalData;
    static MoveData[] diagonalData;
    static MoveData[] knightData;

    @BeforeClass
    public static void setData() {
        verticalData = new MoveData[5];
        horizontalData = new MoveData[5];
        diagonalData = new MoveData[5];
        knightData = new MoveData[2];

        for (byte i = 0; i < 5; i++) {
            boolean upwards = i % 2 == 0;
            boolean leftwards = i % 2 != 0;
            byte startIndex = (byte) (25 + i);

            verticalData[i] = MoveData.builder()
                    .range(i)
                    .upwards(upwards)
                    .leftwards(leftwards)
                    .startIndex(startIndex)
                    .endIndex((byte) (startIndex + ((i % 2 == 0) ? + (8 * i) : - ( 8 * i))))
                    .build();

            horizontalData[i] = MoveData.builder()
                    .range(i)
                    .upwards(upwards)
                    .leftwards(leftwards)
                    .startIndex(startIndex)
                    .endIndex((byte) (i != 4 ? (startIndex + ((i % 2 == 0) ? + i : -i)) : -1))
                    .build();
        }

        knightData[0] = MoveData.builder()
                .range((byte) 0)
                .upwards(false)
                .leftwards(false)
                .startIndex((byte) 9)
                .endIndex((byte) -1)
                .endIndexes(new byte[] {26, 19, 3, 24})
                .build();

        knightData[1] = MoveData.builder()
                .range((byte) 0)
                .upwards(false)
                .leftwards(false)
                .startIndex((byte) 36)
                .endIndex((byte) -1)
                .endIndexes(new byte[] {53, 46, 30, 21, 19, 26, 42, 51})
                .build();
    }
    @Test
    public void testGetIndex() {
        byte[] correctAnswers = new byte[64];
        byte[] calculatedAnswers = new byte[64];

        for (byte i = 0; i < 64; i++) {
            correctAnswers[i] = i;
        }

        for (byte rows = 0; rows < 8; rows++) {
            for (byte columns = 0; columns < 8; columns++) {
                calculatedAnswers[TileCalculator.getIndex(columns, rows)] = TileCalculator.getIndex(columns, rows);
            }
        }

        for (byte k = 0; k < 64; k++) {
            assertEquals(calculatedAnswers[k], correctAnswers[k]);
        }
    }

    @Test
    public void testGetIndexVerticalCalculations() {
        for (MoveData data : verticalData) {
            byte calculation = TileCalculator.getIndexFromVerticalMove(data.isUpwards(), data.getRange(), data.getStartIndex());
            assertEquals(calculation, data.getEndIndex());
        }
    }

    @Test
    public void testGetIndexHorizontalCalculations() {
        for (MoveData data : horizontalData) {
            byte calculation;
            try {
                calculation = TileCalculator.getIndexFromHorizontalMove(data.isLeftwards(), data.getRange(), data.getStartIndex());
            } catch (CoordinateOutOfBoundsException e) {
                calculation = -1;
            }
            assertEquals(calculation, data.getEndIndex());
        }
    }

    @Test
    public void testGetIndexForPossibleKnightMoves() {
        for (MoveData data : knightData) {
            byte[] calculations = TileCalculator.getPossibleIndexForKnightMove(data.getStartIndex());
            assertEquals(calculations.length, data.getEndIndexes().length);
            for (int i = 0; i < calculations.length; i++) {
                assertEquals(calculations[i], data.getEndIndexes()[i]);
            }
        }
    }
}