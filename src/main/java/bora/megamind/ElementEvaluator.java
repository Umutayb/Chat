package bora.megamind;

import bora.App;
import gpt.api.GPT;
import gpt.chat.Chat;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.Printer;
import utils.PropertyUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ElementEvaluator {
    static Printer log = new Printer(App.class);
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        PropertyUtility.loadProperties("src/test/resources/test.properties");
        Document doc;
        String url = getUrl(scanner);
        try{
            doc = Jsoup.connect(url).get();
            Elements body = doc.select("body");
            Elements titles = doc.select("title");
            Elements linksA = doc.select("a");
            Elements buttonsByClass = doc.select("[class=\"button\"]");
            Elements listedElements = doc.select("li");
            Elements inputs = doc.select("input");
            Elements buttons = doc.select("button");
            Elements divs = doc.select("div");

            System.out.println(doc);
            System.out.println("INPUTS:\n" + divs);
            //System.out.println("Titles: " + titles);
            //System.out.println("Links: " + links);
            //System.out.println("Buttons by class: " + buttonsByClass);
            //System.out.println("Buttons: " + buttons);

            List<String> tagNames = new ArrayList<>();
            List<String> texts = new ArrayList<>();
            for (Element element:doc.siblingElements()) {
                tagNames.add(element.tagName());
                texts.add(element.lastElementChild().text());
            }

            System.out.println("TAGS: " + tagNames);
            System.out.println("TEXTS: " + texts);
            String token = PropertyUtility.properties.getProperty("gpt-token", "");
            if (token.trim().isEmpty()) token = getToken(scanner);
            GPT gpt = new GPT(token);
            Chat chat = new Chat(gpt);
            List<String> prompts = new ArrayList<>();
            prompts.add("Lets suppose you are a web scraping bot\n");
            prompts.add("Help evaluate categorising scraped elements on a given url \n");
            prompts.add("Current url is " + url);

            prompts.add("Use the following html to identify the given website and create a list of web element names by this context \n");

            for (Element element: divs) {
                prompts.add(element.toString());
            }
            chat.setPrompts(
                    prompts
            );
            //chat.setPrompts(
            //        Arrays.asList(
            //                "Lets suppose you are an assistant helping perform automated tests by telling me which action to take next",
            //                "Only respond with the name of the action and the name of the target element",
            //                "respond in the following format: {\"action\":\"click\", \"target\":\"loginButton\"}",
            //                "refrain from responding with anything else"
            //        )
            //);
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

    static String getUrl(Scanner scanner){
        log.new Info("Please enter your URL:");
        String url = scanner.nextLine();
        log.new Info("Thanks!");
        return url;
    }
}
