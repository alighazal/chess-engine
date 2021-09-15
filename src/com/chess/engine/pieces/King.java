package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.google.common.collect.ImmutableList;

public class King extends Piece {

    private static final int[] CANDIDATE_MOVE_COORDINATE = { -9, -8, -7, -1, 1, 7, 8, 9 };

    King(int piecePosition, Alliance pieceAlliance, boolean isFisrtMove) {
        super(piecePosition, pieceAlliance, isFisrtMove);
    }

    @Override
    public Collection<Move> possibleMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {

             int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if (isFirstColumnExclusion(this.piecePosition, candidateDestinationCoordinate)
                    || isEightColumnExclusion(this.piecePosition, candidateDestinationCoordinate))
                continue;
            
            final Tile possibleDistTile = board.getTile(candidateDestinationCoordinate);
            
            if (!possibleDistTile.isOccupied()) {
                legalMoves.add(new MajorMove(board, candidateDestinationCoordinate, this));
            } else {
                final Piece possibleDistPiece = possibleDistTile.getPiece();
                final Alliance possibleDistPieceAlliance = possibleDistPiece.getAlliance();
                if (possibleDistPieceAlliance != this.pieceAlliance) {
                    legalMoves.add(new AttackMove(board, candidateDestinationCoordinate, this, possibleDistPiece));
                }
            }

        }

    return ImmutableList.copyOf(legalMoves);

    }

    private static boolean isFirstColumnExclusion(int piecePosition, int moveOffset) {
        return (BoardUtils.FIRST_COL[piecePosition] && (moveOffset == -9 || moveOffset == -1 || moveOffset == 7));
    }

    private static boolean isEightColumnExclusion(int piecePosition, int moveOffset) {
        return (BoardUtils.EIGHTH_COL[piecePosition] && (moveOffset == -7 || moveOffset == 1 || moveOffset == 9));
    }

}
