package com.chess.engine.board;

public class BoardUtils {
    public static final boolean[] FIRST = null ;
    public static final boolean[] SECOND = null ;
    public static final boolean[] SEVENTH = null ;
    public static final boolean[] EIGHTH = null ;

    BoardUtils (){
        throw new RuntimeException("you can't instantiate this class");
    }

    public static boolean isValidTileOffset(int tileOffset) {
        return tileOffset >= 0 && tileOffset <= 63;
    }
}
