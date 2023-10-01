package logic;

import board.LogicTileCalculator;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LogicCalculatorTest {
    static MoveData[] verticalData;
    static MoveData[] horizontalData;
    static MoveData[] diagonalData;

    @BeforeClass
    public static void setData() {
        verticalData = new MoveData[5];
        horizontalData = new MoveData[5];
        diagonalData = new MoveData[5];

        for (byte i = 0; i < 5; i++) {
            byte range = i;
            boolean upwards = i % 2 == 0;
            boolean leftwards = i % 2 != 0;
            byte startIndex = (byte) (25 + i);

            verticalData[i] = MoveData.builder()
                    .range(range)
                    .upwards(upwards)
                    .leftwards(leftwards)
                    .startIndex(startIndex)
                    .endIndex((byte) (startIndex + ((i % 2 == 0) ? + (8 * i) : - ( 8 * i))))
                    .build();

            horizontalData[i] = MoveData.builder()
                    .range(range)
                    .upwards(upwards)
                    .leftwards(leftwards)
                    .startIndex(startIndex)
                    .endIndex((byte) (startIndex + ((i % 2 == 0) ? + i : -i)))
                    .build();
        }
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
                calculatedAnswers[LogicTileCalculator.getIndex(columns, rows)] = LogicTileCalculator.getIndex(columns, rows);
            }
        }

        for (byte k = 0; k < 64; k++) {
            assertEquals(calculatedAnswers[k], correctAnswers[k]);
        }
    }

    @Test
    public void testGetIndexVerticalCalculations() {
        for (MoveData data : verticalData) {
            byte calculation = LogicTileCalculator.getIndexFromVerticalMove(data.isUpwards(), data.getRange(), data.getStartIndex());
            assertEquals(calculation, data.getEndIndex());
        }
    }
}