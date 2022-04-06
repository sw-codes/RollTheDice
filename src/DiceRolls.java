import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DiceRolls {
    int numOfDiceToRoll = 0;
    int numOfDiceSides = 0;
    List<Integer> diceRolls = new ArrayList<>();

    public DiceRolls(){}

    public void getInitialUserInput(){
        Scanner scanner = new Scanner(System.in);
        //ask user how many dice they want to roll
        System.out.println("How many dice would you like to roll?");
        numOfDiceToRoll = scanner.nextInt();
        //if user enters 0 then close program
        if (numOfDiceToRoll < 1){
            System.exit(0);
        }
        //minimum number of dice sides is 2
        while (numOfDiceSides <= 1){
            System.out.println("How many sides should the dice have? (Minimum number: 2)");
            numOfDiceSides = scanner.nextInt();
        }
        rollDice();
    }

    public void getRerollUserInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("You can reroll dice up to specified value. Would you like to reroll any of the dice?"
                + " (Enter y/n)");
        String rerollAnswer = scanner.nextLine().toLowerCase();
        if (rerollAnswer.equals("y")){
            System.out.println("Please enter the value of the dice rolls you'd like to reroll up to.\n" +
                    "(For example, entering 4 will reroll all values from 1 to 4 inclusive)");
            int valuesToReroll = scanner.nextInt();
            rerollDice(valuesToReroll);
        } else {
            System.exit(0);
        }
    }

    public int getDiceRollValue(){
        Random randy = new Random();
        return(randy.nextInt(numOfDiceSides) + 1);
    }

    public void rollDice(){
        diceRolls.clear();
        int totalRollsValue = 0;
        //generate the random values for the amount of dice rolls requested
        for (int i = 1; i <= numOfDiceToRoll; i++){
            diceRolls.add(getDiceRollValue());
        }
        //add the dice roll values to a total counter
        for (int num: diceRolls){
            totalRollsValue += num;
        }
        printDiceRolls();
        //if the number of dice rolls is greater than 1, phrase the output different to if the number is 1
        if (diceRolls.size() > 1){
            System.out.println("The total value of these rolls is: " + totalRollsValue + ".");
        }
        getRerollUserInput();
    }

    public void rerollDice(int valuesToReroll){
        for (int i = 0; i < diceRolls.size(); i++){
            if (diceRolls.get(i) <= valuesToReroll){
                //diceRolls.remove(i);
                //diceRolls.add(i, getDiceRollValue());
                diceRolls.set(i, getDiceRollValue());
            }
        }
        printDiceRolls();
        getRerollUserInput();
    }

    public void printDiceRolls(){
        //print different Strings depending on the number of dice that have been rolled
        if (diceRolls.size() > 1){
            System.out.println("Your dice rolls are: ");
            for (int num: diceRolls){
                System.out.println(num);
            }
        } else {
            System.out.println("Your dice roll is: ");
            System.out.println(diceRolls.get(0));
        }
    }
}
