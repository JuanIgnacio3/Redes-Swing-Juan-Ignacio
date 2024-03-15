/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author LABORATORIO 04
 */
public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5432);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                String[] credentials = dis.readUTF().split(",");
                String nombre = credentials[0];
                String contraseña = credentials[1];
                String respuesta = autentificador(nombre, contraseña);
                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                dos.writeUTF(respuesta);
                dos.close();
                dis.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String autentificador(String username, String password) {
            if (username.equals("Ignacio") && password.equals("Heredia")) {
                return "Inicio de sesión correcto";
            } else {
                return "Error en los datos";
            }
        }
    }
}