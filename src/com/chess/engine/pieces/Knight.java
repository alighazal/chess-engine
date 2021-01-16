package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece {

    private static final int[] POSSIBLE_MOVES_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> possibleMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (int PossibleMove : POSSIBLE_MOVES_COORDINATES){

            final int moveDestination = PossibleMove + this.piecePosition;
            if (BoardUtils.isValidTileOffset(moveDestination)){

                if (isFirstColumnExclusion(this.piecePosition, moveDestination)
                        || isSecondColumnExclusion(this.piecePosition, moveDestination)
                        || isSeventhColumnExclusion(this.piecePosition, moveDestination)
                        || isEighthColumnExclusion(this.piecePosition, moveDestination)){
                    continue;
                }


                final Tile possibleDistTile = board.getTile(moveDestination);
                if (!possibleDistTile.isOccupied()) {
                    legalMoves.add(new MajorMove( board, moveDestination, this ));
                } else {
                    final Piece possibleDistPiece = possibleDistTile.getPiece();
                    final Alliance possibleDistPieceAlliance = possibleDistPiece.getAlliance();
                    if (possibleDistPieceAlliance != this.pieceAlliance){
                        legalMoves.add(new AttackMove(board, moveDestination, this, possibleDistPiece ));
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }


    private static boolean isFirstColumnExclusion (int piecePosition, int moveOffset){
        return (BoardUtils.FIRST[piecePosition] &&
                ( moveOffset == -17 || moveOffset == -10 || moveOffset == 6 || moveOffset == 15 ) );
    }

    private static boolean isSecondColumnExclusion (int piecePosition, int moveOffset){
        return (BoardUtils.SECOND[piecePosition] && ( moveOffset == -10 || moveOffset == 6  ) );
    }

    private static boolean isSeventhColumnExclusion (int piecePosition, int moveOffset){
        return (BoardUtils.SEVENTH[piecePosition] && ( moveOffset == -6 || moveOffset == 10) );
    }


    private static boolean isEighthColumnExclusion (int piecePosition, int moveOffset){
        return (BoardUtils.EIGHTH[piecePosition] &&
                ( moveOffset == 17 || moveOffset == 10 || moveOffset == -6 || moveOffset == -15 ) );
    }


}
