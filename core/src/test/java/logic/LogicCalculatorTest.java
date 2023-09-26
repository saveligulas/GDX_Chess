package logic;

import board.LogicTileCalculator;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LogicCalculatorTest {
    MoveData[] verticalData;
    MoveData[] horizontalData;
    MoveData[] diagonalData;

    @BeforeClass
    public void setData() {
        data = new MoveData[]
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
}