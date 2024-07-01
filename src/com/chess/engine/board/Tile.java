package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {
    // this makes it only accessible by its subclasses and final means that it cannot be further modified
    protected final int tilecoordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {

        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for (int i = 0;i<64;i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }

        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createtile(final int tilecoordinate, final Piece piece){
        return piece != null ? new OccupiedTile(tilecoordinate, piece) : EMPTY_TILES.get(tilecoordinate);
    }

    private Tile(int tilecoordinate){
        this.tilecoordinate = tilecoordinate;
    }
    public abstract boolean isTileoccupied();

    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile {
        EmptyTile(final int coordinate) {
            super(coordinate);
        }

        @Override
        public boolean isTileoccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }
    public static final class OccupiedTile extends Tile{
        private final Piece pieceOnTile;
        OccupiedTile(final int tilecoordinate, Piece pieceOnTile){
            super(tilecoordinate);
            this.pieceOnTile=pieceOnTile;
        }
        @Override
        public boolean isTileoccupied(){
            return true;
        }
        public Piece getPiece(){
            return this.pieceOnTile;
        }

    }
}
