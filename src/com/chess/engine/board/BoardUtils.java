package com.chess.engine.board;

public class BoardUtils {

    public static final int NUM_COLS = 8;
    public static final int NUM_TILES = 64;

    public static final boolean[] FIRST = initColumns(0) ;
    public static final boolean[] SECOND = initColumns(1) ;
    public static final boolean[] SEVENTH = initColumns(7) ;
    public static final boolean[] EIGHTH = initColumns(8) ;

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
