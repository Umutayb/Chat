package bora;

import gpt.api.GPT;
import gpt.chat.Chat;
import utils.Printer;
import utils.PropertyUtility;

import java.util.Scanner;

/**
 * Chat!
 *
 */
public class App {
    static Printer log = new Printer(App.class);
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        PropertyUtility.loadProperties("src/test/resources/test.properties");
        try{
            String token = PropertyUtility.properties.getProperty("gpt-token", "");
            if (token.trim().isEmpty()) token = getToken(scanner);
            GPT gpt = new GPT(token);
            Chat chat = new Chat(gpt);
            chat.startChat(scanner);
            scanner.close();
        }
        catch (Exception e){e.printStackTrace();}
    }

    static String getToken(Scanner scanner){
        log.new Info("Please enter your token:");
        String token = scanner.nextLine();
        log.new Info("Thanks!");
        return token;
    }
}
