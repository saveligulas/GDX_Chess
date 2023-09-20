package model;

public interface PieceInterface {
    byte MAX_COORDINATE_VALUE = 7;
    char[] ALLOWED_COLORS = new char[] {'B', 'W'};
    char[] COLUMN_CHARACTERS = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

    public char[] twoCharPieceInsigne();
    public void removeFromBoard();
}
