package com.geekbrains;

import java.io.*;
import java.net.Socket;

public class ClientTest {

    private final static String SERVER_ADDRESS = "localhost";
    private final static int SERVER_PORT = 8081;
    private static Socket socket;// сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) throws IOException {

        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);// запрашиваем у сервера доступ на соеденение
            reader = new BufferedReader(new InputStreamReader(System.in));


            // читаем сообщение с сервера
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // пишем туда же
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // while (true) {

            Thread t = new Thread(() -> {

                    try {

                        while (true) {

                            System.out.println(" напишите сообщение :");
                            long time = System.currentTimeMillis();

                            // если соединение произошло и потоки успешно созданы - мы можем
                            //  работать дальше и предложить клиенту что то ввести
                            // если нет - вылетит исключение
                            String word = reader.readLine();
                            out.write(word + "\n"); // отправляем сообщение на сервер
                            out.flush();

                            // не напишет в консоль
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            });
            t.start();
        }
             catch (IOException e) {
                    e.printStackTrace();
                };

                 Thread tRead = new Thread(()-> {

                         try {

                             while (true) {

                                 String serverWord = in.readLine(); // читаем сообщение от сервера
                                 System.out.println("Ответ сервера: " + serverWord); // получив - выводим на экран
                             }
                         }
                      catch(IOException e){
                         e.printStackTrace();
                     }

    });
                 tRead.start();
}
}




