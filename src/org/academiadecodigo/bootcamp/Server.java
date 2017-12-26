package org.academiadecodigo.bootcamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    //Game game = null;

    public static void main(String[] args) {

        Server server = new Server();
        server.start();




    }

    private int port = 8080;
    private List <ClientHandler> clientList = Collections.synchronizedList(new ArrayList<ClientHandler>());
    private ClientHandler clientHandler;
    ExecutorService fixedPool = Executors.newFixedThreadPool(3);

    public void start() {

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                if(clientList.size() == 2) {
                    clientSocket = serverSocket.accept();
                    clientSocket.close();
                    System.out.println("Server is full");
                }
                clientSocket = serverSocket.accept();
                System.out.println("Server is now connected and waiting for requests at port number: " + port);

                clientHandler = new ClientHandler(clientSocket);
                fixedPool.submit(clientHandler);
                clientList.add(clientHandler);
                //game = new Game(3, clientList.get(0), clientList.get(1));
            } catch (IOException e) {
                System.out.println("Missing port number");
            }

            System.out.println(clientList.get(0).toString());

        }
    }

    public void sendAll(String message) {

        if(clientList.size() > 2){
            for (int i = 0; i < clientList.size(); i++) {
                clientList.get(i).send("Too many players");
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            clientList.clear();


        }
        if(clientList.size() > 0) {
            for (int i = 0; i < clientList.size(); i++) {
                clientList.get(i).send(message);
            }
        }

    }


    private class ClientHandler implements Runnable {

        PrintWriter out;
        BufferedReader in;
        String name;

        public ClientHandler(Socket clientSocket) {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {

            try {
                setName();



            while (true) {
                    if(clientList.size() > 0) {
                        String message = in.readLine();
                        sendAll(message);
                    }


            }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void setName() throws IOException {
            System.out.print("username: ");
            name = in.readLine();
        }

        public String getName(){
            return name;
        }

        public void send(String message) {
            out.println(message);
        }

    }

}