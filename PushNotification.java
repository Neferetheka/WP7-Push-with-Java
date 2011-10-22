package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PushNotificationWP
{
  public static void PushNotif(String titre, String contenu, String page, String uri)
  {

    try
    {
      //On créé un objet URL à partir de l'uri
      URL url = new URL(uri);
      
      //On créé une URLConnection pour la requête en POST
      URLConnection uc = url.openConnection();
      
      //On met les propriétés spécifiques du header de la requête (voir doc msdn)
      uc.setRequestProperty("ContentType", "text/xml");
      uc.setRequestProperty("X-WindowsPhone-Target", "toast");
      uc.setRequestProperty("X-NotificationClass", "2");
      
      //On indique qu'on va écrire des données (/insérer des paramètres POST)
      uc.setDoOutput(true);

      //Contenu XML de la chaine à envoyer (voir doc msdn)
      String toastMessage = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
          "<wp:Notification xmlns:wp=\"WPNotification\">" +
             "<wp:Toast>" +
                  "<wp:Text1>"+titre+"</wp:Text1>" +
                  "<wp:Text2>"+contenu+"</wp:Text2>" +
                  "<wp:Param>"+page+"</wp:Param>" +
             "</wp:Toast> " +
          "</wp:Notification>"; 

      //On créé un OutputStreamWriter pour écrire dans l'URLConnextion
      OutputStreamWriter  writer = new OutputStreamWriter(uc.getOutputStream());
      
      //On envoie le contenu de la requête POST
      writer.write(toastMessage);
      
      //On ferme la connexion
      writer.flush();
      writer.close();
    }
    catch (MalformedURLException e)
    {
      System.out.println(e.getMessage());
    }
    catch (IOException e)
    {
      System.out.println(e.getMessage());
    }
  }
}
