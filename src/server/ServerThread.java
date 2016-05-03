/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.Socket;
import filehandler.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.MatrixException;
import matrix.SquareMatrix;

/**
 *
 * @author chuon
 */
public class ServerThread extends Thread {

//    private ObjectInputStream objectInputStream;
//    private ObjectOutputStream objectOutputStream;
//    private DataOutputStream dataOutputStream;
//    private DataInputStream dataInputStream;
    private FileHandler fileHandler;

    public ServerThread(Socket socket) throws IOException {
//        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
//        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
//        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.fileHandler = new FileHandler(socket);

    }

    @Override
    public void run() {
        String fileFromClient = "F:\\test\\server.txt";
        String fileToClient = "F:\\test\\result.txt";
        try {
            fileHandler.get(fileFromClient);
            SquareMatrix squareMatrix = SquareMatrix.inputMatrix(fileFromClient);

//            System.out.println("Determinant: " + squareMatrix.determinant());
//
//            SquareMatrix adjugateMatrix = squareMatrix.adjugate();
//            System.out.println("adjugateMatrix");
//            adjugateMatrix.print();
//
//            SquareMatrix transposeMatrix = adjugateMatrix.transpose();
//            System.out.println("transposeMatrix");
//            transposeMatrix.print();
//
//            SquareMatrix invertedMatrix = transposeMatrix.multify(1 / squareMatrix.determinant());
//            System.out.println("invertedMatrix");
//            invertedMatrix.print();

            SquareMatrix invertMatrix = squareMatrix.invert();
            System.out.println("invertMatrix");
            invertMatrix.print();
            invertMatrix.printToFile(fileToClient);
            fileHandler.send(fileToClient);
//        try {
//            SquareMatrix m1 = (SquareMatrix) objectInputStream.readObject();
//            System.out.println("The first matrix: ");
//            m1.print();
//            Matrix m2 = (Matrix) objectInputStream.readObject();
//            System.out.println("The second matrix: ");
//            m2.print();
//            Matrix m3 = m1.multify(m2);
//            objectOutputStream.writeObject(m3);
//
//            dataOutputStream.writeDouble(m1.determinant());
//            objectOutputStream.writeObject(m1.invert());
//
//        } catch (ClassNotFoundException | MatrixException | IOException ex) {
//            System.out.println(ex.toString());
//            try {
//                dataOutputStream.writeUTF(ex.toString());
//            } catch (IOException ex1) {
//                System.out.println(ex1.toString());
//            }
//        }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } catch (MatrixException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
