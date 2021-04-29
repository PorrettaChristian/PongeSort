/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author porretta.christian
 */
public class server {
    
    private int porta;
    private ServerSocket ss;
             
    public server(int porta) {
        this.porta = porta;
        if(!startServer())
            System.out.println("errore in fase di creazione");
    }
    
    private boolean startServer(){
        try {
            ss = new ServerSocket(porta);
        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("server creato con successo");
        return true;
    }
    
    public void runServer(){
        try {
            while(true){
                System.out.println("server in attesa di richiesta...");
                
                Socket client = ss.accept();
                System.out.println("un client connesso!!!!!!!!!!");
                
                InputStream  is = client.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                char [] c = (char[])ois.readObject();
                System.out.println(c[0]);
                ois.close();
                client.close();
                System.out.println("chiusura connessione effettuata.");
            }
        }
        catch (IOException ex) {
                Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        server server = new server(6666);
        server.runServer();
    }
}
