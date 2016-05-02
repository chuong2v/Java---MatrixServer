/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import matrix.MatrixException;
import matrix.Matrix;
import matrix.SquareMatrix;

/**
 *
 * @author chuon
 */
public class ServerThread extends Thread {

    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;
    private final DataOutputStream dataOutputStream;
    private final DataInputStream dataInputStream;

    public ServerThread(Socket socket) throws IOException {
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.dataInputStream = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            SquareMatrix m1 = (SquareMatrix) objectInputStream.readObject();
            System.out.println("The first matrix: ");
            m1.print();
            Matrix m2 = (Matrix) objectInputStream.readObject();
            System.out.println("The second matrix: ");
            m2.print();
            Matrix m3 = m1.multify(m2);
            objectOutputStream.writeObject(m3);

            dataOutputStream.writeDouble(m1.determinant());
            objectOutputStream.writeObject(m1.invert());

        } catch (ClassNotFoundException | MatrixException | IOException ex) {
            System.out.println(ex.toString());
            try {
                dataOutputStream.writeUTF(ex.toString());
            } catch (IOException ex1) {
                System.out.println(ex1.toString());
            }
        }

    }
}
