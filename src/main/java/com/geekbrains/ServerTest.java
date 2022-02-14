package com.geekbrains;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
    private static Socket clientSocket; // соккет для общения
    private static ServerSocket serverSocket; // сервер
    private static BufferedReader reader;
    private static BufferedReader in; // поток чтения
    private static BufferedWriter out; // поток отправки


    public static void main(String[] args) {
       // try {

            try {
                serverSocket = new ServerSocket(8081); // создали сервер

                System.out.println("Сервер запущен");
                //Ожидаем клиента
                clientSocket = serverSocket.accept();
                reader = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Клиент подключился");
               // try {

                in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
// ждем псообщения от клиента
                Thread t = new Thread(()-> {

                        try {
                            while (true) {

                                String message = in.readLine();
                                System.out.println("Сообщение от клиента: " + message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                });
                t.start();

                    // отправляем клиенту
                Thread tWrite = new Thread(()->{

                        try {
                            while (true) {

                                System.out.println(" Напишите сообщение ");
                                String wordClient = reader.readLine();// ждем сообщения от сервера
                                out.write(wordClient + "\n");// отправляем клиету
                                // очищаем все
                                out.flush();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
                tWrite.start();
//
        } catch (IOException exception) {
            exception.printStackTrace();

        }
    }
}
