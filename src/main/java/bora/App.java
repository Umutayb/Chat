package bora;

import gpt.api.GPT;
import gpt.chat.Chat;
import utils.PropertyUtility;

/**
 * Chat!
 *
 */
public class App {
    public static void main( String[] args ) {
        PropertyUtility.loadProperties("src/test/resources/test.properties");
        String token = PropertyUtility.properties.getProperty("gpt-token");
        GPT gpt = new GPT(token);
        Chat chat = new Chat(gpt);
        chat.startChat();
    }
}
