package gulas.saveli.gdxchess.Board;

import gulas.saveli.model.Piece;
import lombok.Data;

@Data
public class LogicTile {
    private byte x_coordinate;
    private byte y_coordinate;

    private Piece pieceOnTile = null;

    public static LogicTile[] getStandard64Tiles() {
        LogicTile[] logicTiles = new LogicTile[64];
        for(byte rows = 0; rows < 8; rows++) {
            for(byte columns = 0; columns < 8; columns++) {
                byte index = (byte) ((rows*8)+columns);
                LogicTile logicTile = new LogicTile();
                logicTile.setY_coordinate(rows);
                logicTile.setX_coordinate(columns);
                logicTiles[index] = logicTile;
            }
        }
        return logicTiles;
    }

    public static byte getIndex(byte x_coordinate, byte y_coordinate) {
        return (byte) (y_coordinate * 8 + x_coordinate);
    }
}
