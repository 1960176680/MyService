package com.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerClass {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(4000);
        while (true){
            Socket s=ss.accept();
            OutputStream os=s.getOutputStream();
            os.write("zwg！\n".getBytes("GBK"));
            os.close();
            s.close();

        }
    }
}
