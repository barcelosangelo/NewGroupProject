package org.academiadecodigo.bootcamp;


import java.io.*;
import java.net.*;

class Client {


    private static String host = "localhost";
    private static int port = 8080;



    public static void main(String args[]) throws Exception {

        String response;

        Socket clientSocket = new Socket(host, port);
        PrintWriter out = new PrintWriter (new OutputStreamWriter(clientSocket.getOutputStream()), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader inFromTerminal = new BufferedReader(new InputStreamReader(System.in));  //

        System.out.println("Welcome to Rock, Paper; Scissors");
        System.out.println("Make your move: (P)aper, (R)ock, (S)cissors");
        System.out.println("You play: ");

        String messageFromTerminal = inFromTerminal.readLine();
        out.println(messageFromTerminal);
        System.out.println(messageFromTerminal + " printed move");



        // responses
        response = in.readLine();


        System.out.println("Result: " + response);

        clientSocket.close();


    }
}