package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

public abstract class Move {

    final Board board;
    final int Position;
    final Piece Piece;

    private  Move(final Board board, final int position, final Piece piece) {
        this.board = board;
        this.Position = position;
        this.Piece = piece;
    }

    public static final class MajorMove extends Move{

        public MajorMove(final Board board, final int position, final Piece piece) {
            super(board, position, piece);
        }

    }

    public static final class AttackMove extends Move{

        final Piece attackedPiece;
        public AttackMove( final Board board, final int position, final Piece piece, final Piece attackedPiece) {
            super(board, position, piece);
            this.attackedPiece = attackedPiece;
        }
    }


}
