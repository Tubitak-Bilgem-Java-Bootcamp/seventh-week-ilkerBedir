package com.patika.chat_app.client;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread extends Thread {
  private PrintWriter writer;
  private Socket socket;
  private ChatClient client;

  public WriteThread(Socket socket, ChatClient client) {
    this.socket = socket;
    this.client = client;
    try {
      OutputStream output = socket.getOutputStream();
      writer = new PrintWriter(output, true);
    } catch (IOException ex) {
      System.out.println("Error getting output stream: " + ex.getMessage());
      ex.printStackTrace();
    }
  }

  public void run() {
    Scanner input=new Scanner(System.in);
    System.out.println("Enter your name: ");
    String userName = input.nextLine();
    client.setUserName(userName);
    writer.println(userName);
    String text;
    do {
      text =("[" + userName + "]: ");
      writer.println(text);
    } while (!text.equals("bye"));
    try {
      socket.close();
    } catch (IOException ex) {
      System.out.println("Error writing to server: " + ex.getMessage());
    }
  }
}