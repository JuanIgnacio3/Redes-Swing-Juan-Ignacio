/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.server;

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
        ServerSocket s = null;
        try {
            s = new ServerSocket(5432);
        } catch (IOException e) {
        }
        while (true) {
            try {
                Socket s1 = s.accept();

                DataOutputStream dos = new DataOutputStream(s1.getOutputStream());
                dos.writeUTF("Hello Net World!\n");
                dos.close();
                s1.close();
            } catch (IOException e) {
            }
        }
    }
}
