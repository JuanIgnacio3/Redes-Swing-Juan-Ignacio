/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author LABORATORIO 04
 */
public class Client {

    public static void main(String[] args) {
        Socket s1 = null;
        try {
            s1 = new Socket("127.0.0.1",5432);
        } catch (IOException e) {
        }
        while (true) {
            try {
                DataInputStream dis = new DataInputStream(s1.getInputStream());
                System.out.println(dis.readUTF());
                dis.close();
                s1.close();
            } catch (IOException e) {
            }
        }
    }
}