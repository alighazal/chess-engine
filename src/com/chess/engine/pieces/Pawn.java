package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.MajorMove;
import com.google.common.collect.ImmutableList;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATE = { 8, 16, 7, 9 };

    public Pawn(int piecePosition, Alliance pieceAlliance, boolean isFisrtMove) {
        super(piecePosition, pieceAlliance, isFisrtMove);
    }

    @Override
    public Collection<Move> possibleMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {

            final int candidateDestinationCoordinate = this.piecePosition
                    + (this.pieceAlliance.getDirection() * currentCandidateOffset);

            if (!BoardUtils.isValidTileOffset(candidateDestinationCoordinate)) {
                continue;
            }

            if (currentCandidateOffset == 8 && board.getTile(candidateDestinationCoordinate).isOccupied()) {
                // TODO more work to do here!!!!
                legalMoves.add(new MajorMove(board, candidateDestinationCoordinate, this));

            } else if (currentCandidateOffset == 16 && this.isFirstMove()
                    && (BoardUtils.SECOND_ROW[this.piecePosition] && this.getAlliance().isBlack())
                    || (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getAlliance().isWhite())) {

                final int behindCandidateDestinationCoordinate = this.piecePosition
                        + (this.pieceAlliance.getDirection() * 8);

                if (!board.getTile(behindCandidateDestinationCoordinate).isOccupied()
                        && !board.getTile(candidateDestinationCoordinate).isOccupied()) {

                    legalMoves.add(new MajorMove(board, candidateDestinationCoordinate, this));

                }
            } else if (currentCandidateOffset == 7
                    && !((BoardUtils.EIGHTH_COL[this.piecePosition] && this.pieceAlliance.isWhite()
                            || (BoardUtils.FIRST_COL[this.piecePosition] && this.pieceAlliance.isBlack())))) {
                if (board.getTile(candidateDestinationCoordinate).isOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();

                    if (this.pieceAlliance != pieceOnCandidate.getAlliance()) {
                        // TODO more to do here
                        legalMoves.add(new MajorMove(board, candidateDestinationCoordinate, this));
                    }
                }
            } else if (currentCandidateOffset == 9
                    && !((BoardUtils.FIRST_COL[this.piecePosition] && this.pieceAlliance.isWhite()
                            || (BoardUtils.EIGHTH_COL[this.piecePosition] && this.pieceAlliance.isBlack())))) {
                if (board.getTile(candidateDestinationCoordinate).isOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceAlliance != pieceOnCandidate.getAlliance()) {
                        // TODO more to do here
                        legalMoves.add(new MajorMove(board, candidateDestinationCoordinate, this));
                    }
                }
            }

        }

        return ImmutableList.copyOf(legalMoves);

    }

}
