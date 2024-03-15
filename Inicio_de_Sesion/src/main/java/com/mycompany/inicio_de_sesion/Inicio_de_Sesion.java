/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.inicio_de_sesion;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author Ignacio
 */
public class Inicio_de_Sesion extends JFrame {
    private JTextField nomUsu;
    private JPasswordField contraseña;

    public Inicio_de_Sesion() {
        super("Inicio de Sesion");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 8));
        panel.add(new JLabel("Nombre de usuario:"));
        nomUsu = new JTextField();
        panel.add(nomUsu);
        panel.add(new JLabel("Contraseña:"));
        contraseña = new JPasswordField();
        panel.add(contraseña);
        JButton loginButton = new JButton("Iniciar sesiòn");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = nomUsu.getText();
                String password = new String(contraseña.getPassword());
                autentificador(username, password);
            }
        });
        panel.add(loginButton);

        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void autentificador(String username, String password) {
        try {
            Socket socket = new Socket("127.0.0.1", 5432);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(username + "," + password);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String response = dis.readUTF();
            JOptionPane.showMessageDialog(this, response);
            dos.close();
            dis.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error con el server");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Inicio_de_Sesion();
            }
        });
    }
}