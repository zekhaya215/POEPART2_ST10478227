/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;
import javax.swing.*;
import java.util.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Chatapp {
    private static int totalMessages = 0;
    private static int messageCounter = 0;
    private static JSONArray messageStorage = new JSONArray();

    public static void main(String[] args) {
        if (!login()) return;

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat.");

        int maxMessages = 0;
        while (true) {
            try {
                String input = JOptionPane.showInputDialog("How many messages would you like to enter?");
                maxMessages = Integer.parseInt(input);
                if (maxMessages > 0) break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid positive number.");
            }
        }

        boolean exit = false;
        while (!exit) {
            String[] options = {"Send Message", "Show Recently Sent Messages", "Quit"};
            int choice = JOptionPane.showOptionDialog(null, "Choose an option:", "QuickChat Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0: // Send Message
                    if (messageCounter < maxMessages) {
                        sendMessage();
                    } else {
                        JOptionPane.showMessageDialog(null, "Message limit reached.");
                    }
                    break;
                case 1: // Show messages
                    JOptionPane.showMessageDialog(null, "Total messages sent: " + totalMessages);
                    break;
                case 2: // Quit
                    exit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option.");
            }
        }

        saveMessagesToJSON();
    }

    private static void sendMessage() {
        long messageId = 1000000000L + new Random().nextInt(900000000);

        String recipient = JOptionPane.showInputDialog("Enter recipient number (+CCXXXXXXXXXX):");
        if (recipient == null || !recipient.matches("\\+\\d{9,12}")) {
            JOptionPane.showMessageDialog(null, "Invalid number. Must include international code and be 9-12 digits.");
            return;
        }

        String message = JOptionPane.showInputDialog("Enter your message (max 400 chars):");
        if (message == null || message.length() > 250) {
            JOptionPane.showMessageDialog(null, "Please enter a message of less than 400 characters.");
            return;
        }

        String[] words = message.trim().split("\\s+");
        String hash = String.format("%02d:%02d:%s:%s",
                Long.parseLong(Long.toString(messageId).substring(0, 2)),
                words.length,
                words[0].toUpperCase(),
                words.length > 1 ? words[words.length - 1].toUpperCase() : "");

        String[] actions = {"Send", "Disregard", "Store"};
        int action = JOptionPane.showOptionDialog(null, "Choose what to do with this message:",
                "Message Options", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, actions, actions[0]);

        if (action == 1) {
            JOptionPane.showMessageDialog(null, "Message disregarded.");
            return;
        }

        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put("MessageID", messageId);
        jsonMessage.put("Recipient", recipient);
        jsonMessage.put("Message", message);
        messageStorage.add(jsonMessage);

        if (action == 2) {
            JOptionPane.showMessageDialog(null, "Message stored.");
            return;
        }

        totalMessages++;
        messageCounter++;

        JOptionPane.showMessageDialog(null, "Message Sent!\n" +
                "Message ID: " + messageId + "\n" +
                "Message Hash: " + hash + "\n" +
                "Recipient: " + recipient + "\n" +
                "Message: " + message);
    }

    private static void saveMessagesToJSON() {
        try (FileWriter file = new FileWriter("storedMessages.json")) {
            file.write(messageStorage.toJSONString());
            file.flush();
            System.out.println("Stored messages saved to storedMessages.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean login() {
        String user = JOptionPane.showInputDialog("Enter username:");
        String pass = JOptionPane.showInputDialog("Enter password:");
        if ("Zekhaya".equals(user) && "Kungawo2010@".equals(pass)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Login failed.");
            return false;
        }
    }
}