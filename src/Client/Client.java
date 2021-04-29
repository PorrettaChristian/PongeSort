/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author porretta.christian
 */
public class Client {
    
 public static void main(String[] args){
        // TODO code application logic here
        char [] lista = new char[10];
        for(int i=0;i<10;i++){
            lista[i] = (char)(int)(65+Math.random()*23);
            System.out.print(lista[i]);  
        }
     try {    
         Socket server = new Socket("127.0.0.1", 6666);
          OutputStream out = server.getOutputStream();
          ObjectOutputStream o = new ObjectOutputStream(out);
          o.writeObject(lista);
          o.flush();
          out.flush();
          InputStream  is = server.getInputStream();
          ObjectInputStream ois = new ObjectInputStream(is);
                char [] c = (char[])ois.readObject();
                System.out.println(c);
          server.close();
          out.close();
     } catch (IOException ex) {
         Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
}
