package bora;

import gpt.api.GPT;
import gpt.chat.ui.ChatGUI;
import gpt.chat.ui.ChatGUIFactory;
import utils.Printer;
import utils.PropertyUtility;

import java.util.Scanner;

import static gpt.chat.ui.ChatGUIFactory.Theme.light;

/**
 * Chat!
 *
 */
public class App {
    static Printer log = new Printer(App.class);
    public static void main( String[] args ) {

        PropertyUtility.loadProperties("src/test/resources/test.properties");
        try{
            String token = PropertyUtility.getProperty("gpt-token", "");
            GPT gpt = new GPT(token);
            ChatGUI chat = ChatGUIFactory.getChatGUI(
                    light,
                    gpt
            );
            chat.setTemperature(Double.parseDouble(PropertyUtility.getProperty("temperature", "0.5")));
            chat.start();
        }
        catch (Exception e){e.printStackTrace();}
    }


}
