package org.academiadecodigo.bootcamp;


import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Server {

    //private int port = 8080;
    //private List<Client> clientList = Collections.synchronizedList(new ArrayList<Client>());

    public static void main(String args[]) throws Exception {


        String responseClient1 = " ";
        String responseClient2 = " ";
        String inputClient1;
        String inputClient2;

        // new server socket
        ServerSocket initialSocket = new ServerSocket(8080);
        System.out.println("Waiting for players");

        while (!initialSocket.isClosed()) {

            // Player 1
            Socket client1 = initialSocket.accept();
            System.out.println("Player 1 is connected");

            DataOutputStream outClient1 = new DataOutputStream(client1.getOutputStream());
            BufferedReader inClient1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));

            // Player 2
            Socket client2 = initialSocket.accept();
            System.out.println("Player 2 is connected");

            DataOutputStream outClient2 = new DataOutputStream(client2.getOutputStream());
            BufferedReader inClient2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));


            String handClient1 = inClient1.readLine();
            inputClient1 = handClient1.toUpperCase();
            System.out.println("Player 1 plays:"  + inputClient1);

            String handClient2 = inClient2.readLine();
            inputClient2 = handClient2.toUpperCase();
            System.out.println("Player 2 plays:" + inputClient2);



            if (inputClient1.equals(inputClient2)) {
                responseClient1 = "Draw";
                responseClient2 = "Draw";
                System.out.println("It's a draw.");

            } else if (inputClient1.equals("R") && inputClient2.equals("S")) {
                responseClient1 = "You win";
                responseClient2 = "You lose";
                System.out.println("Rock beats Scissors, Player one wins.");

            } else if (inputClient1.equals("S") && inputClient2.equals("R")) {
                responseClient1 = "You lose";
                responseClient2 = "You win";
                System.out.println("Rock beats Scissors, Player two wins.");

            } else if (inputClient1.equals("R") && inputClient2.equals("P")) {
                responseClient1 = "You lose";
                responseClient2 = "You win";
                System.out.println("Paper beats Rock, Player two wins.");

            } else if (inputClient1.equals("P") && inputClient2.equals("R")) {
                responseClient1 = "You win";
                responseClient2 = "You lose";
                System.out.println("Paper beats Rock, Player one wins.");

            } else if (inputClient1.equals("S") && inputClient2.equals("P")) {
                responseClient1 = "You win";
                responseClient2 = "You lose";
                System.out.println("Scissors beats Paper, Player one wins.");

            } else if (inputClient1.equals("P") && inputClient2.equals("S")) {
                responseClient1 = "You lose";
                responseClient2 = "You win";
                System.out.println("Scissors beats Paper, Player two wins.");
            }







            // Send responses and close sockets
            outClient1.writeBytes(responseClient1);
            outClient2.writeBytes(responseClient2);
            client1.close();
            client2.close();


            System.out.println("Waiting for new players ...");

        }

    }
}

