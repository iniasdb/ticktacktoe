package com.example.ticktacktoe.controller;

import com.example.ticktacktoe.model.Board;
import com.example.ticktacktoe.model.ClientConnector;
import com.example.ticktacktoe.model.Server;
import com.example.ticktacktoe.model.Tile;
import com.example.ticktacktoe.tictactoeMain;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    @FXML
    private Label label;
    @FXML
    private GridPane grid;
    private Board board;
    private int playerTurn;

    private List<Integer> p1Pos = new ArrayList<Integer>();
    private List<Integer> p2Pos = new ArrayList<Integer>();

    private boolean gameOver = false;

    private static Controller controller = null;

    private ClientConnector connector;
    private Server server;

    public static Controller getInstance()
    {
        if (controller == null)
            controller = new Controller();

        return controller;
    }

    public void initialize() {
        playerTurn = 1;
        board = new Board();

        server = new Server();
        connector = new ClientConnector();

        Thread newThread = new Thread(() -> {
            try {
                server.create();
                connector.create();
                connector.transmitLabel("ja manne");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        initBoard();
    }

    public void clicked(Tile tile) {

        if (!gameOver) {
            if (p1Pos.contains(tile.getPos()) || p2Pos.contains(tile.getPos())) {
            } else {

                if (playerTurn == 1) {
                    p1Pos.add(tile.getPos());
                } else if (playerTurn == 2) {
                    p2Pos.add(tile.getPos());
                }
                fillTile(tile);
                label.setText("player " + Integer.toString(playerTurn) + " turn");

                int state = checkWinConditions();
                gameOver = true;
                switch (state) {
                    case 1:
                        label.setText("player 1 won");

                        break;
                    case 2:
                        label.setText("player 2 won");
                        break;
                    case 3:
                        label.setText("draw");
                        break;
                    default:
                        gameOver = false;
                }

            }
        } else {
        }
    }

    private int checkWinConditions() {
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> midRow = Arrays.asList(4, 5, 6);
        List<Integer> botRow = Arrays.asList(7, 8, 9);
        List<Integer> lefCol = Arrays.asList(1, 4, 7);
        List<Integer> midCol = Arrays.asList(2, 5, 8);
        List<Integer> rigCol = Arrays.asList(3, 6, 9);
        List<Integer> diaOne = Arrays.asList(1, 5, 9);
        List<Integer> diaTwo = Arrays.asList(3, 5, 7);

        List<List> winCond = new ArrayList<List>();
        winCond.add(topRow);
        winCond.add(midRow);
        winCond.add(botRow);
        winCond.add(lefCol);
        winCond.add(midCol);
        winCond.add(rigCol);
        winCond.add(diaOne);
        winCond.add(diaTwo);

        for (List l : winCond) {
            if (p1Pos.containsAll(l)) {
                return 1;
            } else if (p2Pos.containsAll(l)) {
                return 2;
            } else if (p1Pos.size() + p2Pos.size() == 9) {
                return 3;
            }
        }
        return 0;
    }

    private void fillTile(Tile tile) {
        if (playerTurn == 1) {
            tile.setFill(new ImagePattern(new Image(tictactoeMain.class.getResource("images/x.png").toExternalForm()))); //fill rect with x image
            playerTurn = 2;
        } else if (playerTurn == 2) {
            tile.setFill(new ImagePattern(new Image(tictactoeMain.class.getResource("images/o.png").toExternalForm()))); //fill rect with o image
            playerTurn = 1;
        }
    }

    private void initBoard() {
        int pos = 1;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                Tile tile = board.createTile(i, j, pos);
                grid.add(tile, j, i);
                pos++;
            }
        }
    }
}