package com.example.ticktacktoe.model;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class Board {

    List<Tile> tileList = new ArrayList<>();

    public Board() {
    }

    public Tile createTile(int x, int y, int pos) {
        //create tile
        Tile tile = new Tile(x, y, pos);
        tileList.add(tile);

        return tile;
    }
}
