package bora;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Scanner;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder inputBuilder = new StringBuilder();
        String line;
        boolean isInputComplete = false;

        System.out.println("Enter multiline input (press ':END!:' to finish):");

        while (!isInputComplete) {
            line = scanner.nextLine();
            if (line.equals(":END!:")) {
                isInputComplete = true;
            } else {
                inputBuilder.append(line).append("\n");
            }
        }

        String input = inputBuilder.toString().trim().replaceAll("(?m)^[ \t]*\r?\n", "");

        System.out.println("Refactored input:");
        System.out.println(input);
    }
}
