/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author chuon
 */
public class Server {
    private final int SERVER_PORT;

    public Server(int port) {
        this.SERVER_PORT = port;
    }
    
    public void serve() throws IOException{
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        while(true){
            System.out.println("Server is running at port " + serverSocket.getLocalPort());
            Socket socket = serverSocket.accept();
            new ServerThread(socket).start();
        }
    }
    
    public static void main(String[] args) {
        Server server = new Server(2600);
        
        try {
            server.serve();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
