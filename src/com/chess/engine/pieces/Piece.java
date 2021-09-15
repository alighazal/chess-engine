package  com.chess.engine.pieces;


import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFisrtMove;


    Piece (final int piecePosition, final Alliance pieceAlliance, final boolean isFisrtMove){
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        this.isFisrtMove = false;
    }

    public Alliance getAlliance() {
        return this.pieceAlliance;
    }

    public boolean isFirstMove (){
        return this.isFisrtMove;
    }

    public abstract Collection<Move> possibleMoves(final Board board);



}
