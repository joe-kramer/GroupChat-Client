package com.kramer;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 8000)) {

            socket.setSoTimeout(5000);
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);

            Scanner userInput = new Scanner(System.in);
            String message;
            String response;

            do {
                System.out.println("Type message:");
                message = userInput.nextLine();

                toServer.println(message);

                if (!message.equalsIgnoreCase("exit")) {
                    response = fromServer.readLine();
                    System.out.println("Response: " + response);
                }

            } while (!message.equalsIgnoreCase("exit"));

        } catch (SocketTimeoutException e) {
            System.out.println("Socket has timed out");
        } catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
        }
    }
}
