package model;

import lombok.Data;

@Data
public class TileMoveData {
    private boolean upwards;
    private boolean leftwards;
    private byte range;
    private byte selectedIndex;
    private byte targetIndex;
}
