package com.example.ticktacktoe.model;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket ss;
    private Socket socket;
    private DataOutputStream dout;
    private DataInputStream din;

    private String message;

    public void create() throws IOException {
        ss=new ServerSocket(6666);
        socket = ss.accept();
        din = new DataInputStream(socket.getInputStream());
        dout = new DataOutputStream(socket.getOutputStream());
    }

    public void receiveLabel() throws IOException {

        String text  ="";

        text = din.readUTF();
        System.out.println("Server says: " + text);

        this.message = text;

        sendLabel();

    }

    private void sendLabel() throws IOException {
        dout.writeUTF(this.message);
        dout.flush();
    }

    public void quit() throws IOException {
        din.close();
        socket.close();
        ss.close();
    }

}
