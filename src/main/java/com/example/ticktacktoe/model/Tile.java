package com.example.ticktacktoe.model;

import com.example.ticktacktoe.controller.Controller;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private int tileSize = 130;
    private int x;
    private int y;
    private int pos;
    private Controller controller;

    public Tile(int x, int y, int pos) {
        this.x = x;
        this.y = y;
        this.setFill(Color.ALICEBLUE);
        this.setHeight(tileSize);
        this.setWidth(tileSize);
        this.pos = pos;
        this.controller = Controller.getInstance();

        this.setOnMouseClicked(e -> {
            System.out.println(pos);
            controller.clicked(this);
        });
    }

    public int getPos() {
        return pos;
    }
}
