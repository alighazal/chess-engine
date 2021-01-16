package  com.chess.engine.board;
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

    protected  final int tileCoordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILE_CACHE = createAllEmptyTiles();

    private static Map<Integer, EmptyTile> createAllEmptyTiles() {

        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for(int i = 0; i < 64; i++){
            emptyTileMap.put(i, new EmptyTile (i));
        }

        return ImmutableMap.copyOf(emptyTileMap);

    }

    private Tile(int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    public static Tile createTile(final int tileCoordinate, final Piece piece ){
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILE_CACHE.get(tileCoordinate);
    }

    public abstract boolean isOccupied();

    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile {

        private EmptyTile(final int tileCoordinate) {
            super(tileCoordinate);
        }

        @Override
        public boolean isOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    public static final class OccupiedTile extends Tile {
        
        private final Piece pieceOnTile;

        private OccupiedTile(int tileCoordinate, Piece pieceOnTile ) {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return pieceOnTile;
        }
    }

}
