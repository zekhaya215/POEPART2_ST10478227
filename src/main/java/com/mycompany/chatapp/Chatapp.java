/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp;
import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 *
 * @author RC_Student_lab
 */
public class Chatapp {

    private static int choice;
    private static int messageCounter;
    private static int totalMessage;
    private static int totalMessages;

    public static void main(String[] args) {
        String[] options ={"Send Message", "Show Recently Sent Messages", "Quit"};
        int choise =JOptionPane.showOptionDialog(null, "Choose an option:", "QuickChat Menu",
                 JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
      
                switch (choice){
                    
                    case 1://coming soon
                      JOptionPane. showMessageDialog(null, "Coming Soon.");
                      break;
                    case 2: //Quit
            boolean exit = true;
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalind option.");}
    }
                        
    static boolean login(){
    String user = JOptionPane.showInputDialog("Enter username:");
    String pass = JOptionPane.showInputDialog("Enter your password:");
    if ("admin" .equals(user)&& "1234".equals(pass)){
     } else{           
      JOptionPane. showMessageDialog( null, "Login failed.");
    }   return false;
}
      static void sendMessage(){
     long messageId = 10000000000L + new Random().nextInt(900000000);
     messageCounter++;
     String recipient= JOptionPane.showInputDialog("Enter recipient number (+ccxxxxxxxxxx):");
    if (recipient== null|| !recipient.matches("\\+\\d{9,12}")){
         JOptionPane.showMessageDialog(null,"Invalid number.Must include international code and be<=12 digits.");
         return;
         
         
    }
    String message = JOptionPane.showInputDialog("Enter your message (max 250 charts):");
     if (recipient ==null || message.length() >250){
       JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters");
       return;
     }

       String[] words= message.trim().split("\\s+");
       String hash= String.format("%02d:%:%8%5",
        Long.parseLong(Long.toString(messageId).substring(0,2)),
        messageCounter,
        words[0].toUpperCase(),
        words.length>1? words[words.length - 1].toUpperCase():"");
       String[]actions= {"Send","Disregard", "Store"};
       int action= JOptionPane.showOptionDialog(null,
        "Choose what to do with this message:",
        "Message Options",
        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, actions, actions[0]);
       
      if (action==1){
          JOptionPane.showMessageDialog(null,"Message disregarded.");
          return;
          
      }
      
      JSONObject jsonMessage =new JSONObject();
      jsonMessage.put ("MessageID",messageId);
      //jsonMessage.put ("MessageHash", hash);
      jsonMessage.put("Recipient", recipient);
      jsonMessage.put("Message",message);
      
      if (action ==2){
          messageStorage.add(jsonMessage);
          JOptionPane.showMessageDialog(null,"Message stored.");
          return;
      }
          totalMessages++;
          JOptionPane.showMessageDialog(null,
                  "Message Sent!\n"+
                          "Message ID: " + messageId + "\n"+
                    //"Message Hash:" + hash + "\n" +
                          "Recipient:" + recipient + "\n" +
                          "Message:" + message);
      }
      static void saveMeasssageToJSON() throws IOException{
          try (FileWriter file = new FileWriter("storagedMessages.json")){
              file.write(messageStorage.toJSONString());
              file.flush();
              System.out.println("Stored message saved to storedMessasge.json");
          } catch (IOException e) {
              e. printStackTrace();
              
              if (!login()) return;
              JOptionPane.showMessageDialog(null,"Welcone to QuickChat.");
              
              int maxMessages = 0;
              while (true) {
                  String input = JOptionPane.showInputDialog ("How messages would you like to enter?");
                  try {
                      maxMessages = Integer. parseInt(input);
                      if (maxMessages >0) break;
                      
                  } catch (Exception ell) {
                      JOptionPane.showMessageDialog(null,"Please enter a valid positive number.");
                  }
              }
              
              boolean exit = false;
              while (!exit){
                  String[] options = {"Send Message", "Show Recently Sent Messages", "Quit"};
                  int choice = JOptionPane.showOptionDialog(null,"Choose an option:", "QuickChat menu",
                      JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                  
                  switch (choice){
                      case 0://Send Message
                          if (messageCounter < maxMessages){
                              sendMessage();
                          }else {
                               JOptionPane.showMessageDialog(null,"Message limit reached.");
                               
                          }
                          break;
                      case 1://coming soon
                           JOptionPane.showMessageDialog(null,"Inalid option.");
                           
                  }      
              }
               JOptionPane.showMessageDialog(null,"Total messages sent:" + totalMessages);
               saveMessagesToJSON();
               
          }
          
      
      }

    private static void saveMessagesToJSON() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    }

               
              
              
              
              
          
              
          
          
          
      
     
       
        
      
     
      
                      


    




                        
                                
                
                
    
               
    
                                 
                        
                  
                
        
   
    
