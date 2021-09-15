package com.chess.engine.board;

public class BoardUtils {

    public static final int NUM_COLS = 8;
    public static final int NUM_TILES = 64;

    public static final boolean[] FIRST_COL = initColumns(0) ;
    public static final boolean[] SECOND_COL = initColumns(1) ;
    public static final boolean[] SEVENTH_COL = initColumns(7) ;
    public static final boolean[] EIGHTH_COL = initColumns(8) ;

    public static final boolean[] SECOND_ROW = null ;
    public static final boolean[] SEVENTH_ROW = null ;

    BoardUtils (){
        throw new RuntimeException("you can't instantiate this class");
    }

    public static boolean isValidTileOffset(int tileOffset) {
        return tileOffset >= 0 && tileOffset < NUM_TILES;
    }

    private static boolean[] initColumns(int ColumnNumber) {
        final boolean[] columns = new boolean[NUM_TILES];
        do {
            columns[ColumnNumber] = true;
            ColumnNumber += NUM_COLS;
        }   while (ColumnNumber < NUM_TILES);
        return columns;
    }
}
