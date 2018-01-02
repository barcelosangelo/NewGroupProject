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
        System.out.println("*---------------*---------------*");
        System.out.println("");
        System.out.println("Make your move: (P)aper, (R)ock, (S)cissors");
        System.out.println("You play: ");

        String messageFromTerminal = inFromTerminal.readLine();
        while(!messageFromTerminal.toUpperCase().equals("S") && !messageFromTerminal.toUpperCase().equals("P") &&  !messageFromTerminal.toUpperCase().equals("R")) {
            System.out.println("Please use a valid move...PLease choose one of the valid options(R, S or P):");
            inFromTerminal = new BufferedReader(new InputStreamReader(System.in));
            messageFromTerminal = inFromTerminal.readLine();
    }

        out.println(messageFromTerminal);
        System.out.println(messageFromTerminal + " player choice");





        // responses
        response = in.readLine();


        System.out.println("Result: " + response);

        clientSocket.close();


    }
}