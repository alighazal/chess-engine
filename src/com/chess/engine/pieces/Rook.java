package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook extends Piece {

    private static final int[] POSSIBLE_MOVES_VECTOR = {-1, 1, 8, -8};
    
    Rook(int piecePosition, Alliance pieceAlliance, boolean isFisrtMove) {
        super(piecePosition, pieceAlliance, isFisrtMove);
    }

    @Override
    public Collection<Move> possibleMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();
        for (int possibleMoveDirection: POSSIBLE_MOVES_VECTOR ){
            int candidatePosition = this.piecePosition;
            while (BoardUtils.isValidTileOffset(candidatePosition)){

                if (isFirstColumnExclusion(candidatePosition, possibleMoveDirection)
                        || isEighthColumnExclusion(candidatePosition, possibleMoveDirection))
                    break;

                candidatePosition += possibleMoveDirection;
                if (BoardUtils.isValidTileOffset(candidatePosition)){

                    final Tile possibleDistTile = board.getTile(candidatePosition);
                    if (!possibleDistTile.isOccupied()){
                        legalMoves.add(new MajorMove(board, candidatePosition, this ));

                    }else {

                        final Piece possibleDistPiece = possibleDistTile.getPiece();
                        final Alliance possibleDistPieceAlliance = possibleDistPiece.getAlliance();
                        if (possibleDistPieceAlliance != this.pieceAlliance){
                            legalMoves.add(new AttackMove(board, candidatePosition, this, possibleDistPiece ));
                        }
                        break;
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion (int piecePosition, int moveOffset){
        return (BoardUtils.FIRST_COL[piecePosition] && ( moveOffset == -1 ));
    }
    private static boolean isEighthColumnExclusion (int piecePosition, int moveOffset){
        return (BoardUtils.EIGHTH_COL[piecePosition] && ( moveOffset == 1) );
    }

}
