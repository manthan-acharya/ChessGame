import java.util.Scanner;


public class PlayGame {
    Scanner input = new Scanner(System.in);

    // Returns the next user inputtted line in the terminal with a prompt
    private String getInput(String prompt)
    {
        promptInput(prompt);
        return input.nextLine();
    }

    // Returns the next user inputted line in the terminal without a prompt
    private String getInput()
    {
        return input.nextLine();
    }

    private void promptInput(String prompt)
    {
        System.out.println();
        System.out.print(prompt);
        System.out.println(':');
    }

    public String getNextMove()
    {
        String nextMove = getInput("What is your next move?");
        return nextMove;
    }



}

