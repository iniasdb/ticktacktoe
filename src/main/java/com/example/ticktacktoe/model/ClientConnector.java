package com.example.ticktacktoe.model;

import java.io.*;
import java.net.Socket;

public class ClientConnector {

    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dout;

    public void create() throws IOException {
        socket = new Socket("localhost", 6666);
        din = new DataInputStream(socket.getInputStream());
        dout = new DataOutputStream(socket.getOutputStream());
    }

    public void quit() throws IOException{
        dout.close();
        din.close();
        socket.close();
    }

    public void transmitLabel(String text) throws IOException {

        //while (!s1.equals("stop")) {
            dout.writeUTF(text);
            dout.flush();
        //}
    }

    public String receiveLabel() throws IOException {
        String text  ="";

        //while (!text.equals("stop")) {
            text = din.readUTF();
            System.out.println("Server says: " + text);
        //}
        return text;
    }
}
