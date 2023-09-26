package logic;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MoveData {

    private boolean upwards;
    private boolean leftwards;
    private byte range;
    private byte startIndex;
    private byte endIndex;

}
