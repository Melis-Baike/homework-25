import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        run();
    }

    static Random rnd = new Random();
    static Scanner sc = new Scanner(System.in);

    public static void run(){
        Computer computer = new Computer();
        Human human = new Human();
        computer.setName("Computer");
        human.setName("Person");
        computer.setEnemy(human);
        human.setEnemy(computer);
        int count = 0;
        int variation = selectVariation();
        chooseMeaningToHuman(human, computer, count, variation);
    }

    public static void chooseMeaningToHuman(Human human, Computer computer, int count, int variation){
        selectMeaningToComputer(computer, variation);
        System.out.println("1 - Rock\n2 - Paper\n3 - Scissors");
        if(variation == 2){
            System.out.println("4 - Lizard\n5 - Spock");
        }
        System.out.println("If you want to leave from game - enter 9");
        System.out.print("Enter the value you want to select : ");
        try {
            int meaning = Integer.parseInt(sc.next());
            if(variation == 1) {
                checkOutOfRange(meaning);
            } else {
                thirdCheckOutOfRange(meaning);
            }
            if(meaning == 9){
                if(count!=0) {
                    System.out.println("Total results");
                    printResults(human, computer);
                }
                System.out.println("Good bye!");
            } else {
                count++;
                takeChosenMeaning(human, meaning, computer, count, variation);
            }
        } catch (NumberFormatException e){
            System.out.println("You entered not numeric value or not integer value");
            chooseMeaningToHuman(human, computer, count, variation);
        } catch (Exception e) {
            System.out.println("You entered wrong number!");
            chooseMeaningToHuman(human,computer, count, variation);
        }
    }

    public static void checkOutOfRange(int number) throws Exception{
        if(number < 1 || number > 3 && number != 9){
            throw new OutOfRangeException("You are out of range");
        }
    }

    public static void thirdCheckOutOfRange(int number) throws Exception{
        if(number < 1 || number > 5 && number != 9){
            throw new OutOfRangeException("You are out of range");
        }
    }

    public static void takeChosenMeaning(Human human, int meaning, Computer computer, int count, int variation){
        if(variation == 1) {
            switch (meaning) {
                case 1:
                    human.meaning = Meanings.ROCK;
                    break;
                case 2:
                    human.meaning = Meanings.PAPER;
                    break;
                case 3:
                    human.meaning = Meanings.SCISSORS;
                    break;
            }
        } else {
            switch (meaning) {
                case 1:
                    human.meaning = Meanings.ROCK;
                    break;
                case 2:
                    human.meaning = Meanings.PAPER;
                    break;
                case 3:
                    human.meaning = Meanings.SCISSORS;
                    break;
                case 4:
                    human.meaning = Meanings.LIZARD;
                    break;
                case 5:
                    human.meaning = Meanings.SPOCK;
                    break;
            }
        }
        System.out.println("Person - " + human.meaning);
        System.out.println("Computer - " + computer.meaning);
        if(variation == 1) {
            toCount(human);
            toCount(computer);
        } else if(variation == 2) {
            toCountComplicatedVariation(human);
            toCountComplicatedVariation(computer);
        }
        toShowCurrentResults(human, computer);
        System.out.println("-----------------------------------------------------------");
        chooseMeaningToHuman(human, computer, count, variation);
    }

    public static void selectMeaningToComputer(Computer computer, int variation){
        if(variation == 1) {
            int random = rnd.nextInt(3);
            if (random == 0) {
                computer.meaning = Meanings.ROCK;
            } else if (random == 1) {
                computer.meaning = Meanings.PAPER;
            } else {
                computer.meaning = Meanings.SCISSORS;
            }
        } else {
            int random = rnd.nextInt(5);
            switch (random){
                case 0 : computer.meaning = Meanings.ROCK;
                    break;
                case 1 : computer.meaning = Meanings.PAPER;
                    break;
                case 2 : computer.meaning = Meanings.SCISSORS;
                    break;
                case 3 : computer.meaning = Meanings.LIZARD;
                    break;
                case 4 : computer.meaning = Meanings.SPOCK;
                    break;
            }
        }
    }

    public static int selectVariation(){
        System.out.println("1 - Default game");
        System.out.println("2 - Complicated game");
        System.out.print("Enter variation of game what do you want to play : ");
        try {
            int variation = Integer.parseInt(sc.next());
            checkSecondOutOfRange(variation);
            return variation;
        } catch (NumberFormatException e){
            System.out.println("You entered not numeric value or not integer value");
            run();
        } catch (Exception e){
            System.out.println("You entered wrong number!");
            run();
        }
        return 0;
    }

    public static void checkSecondOutOfRange(int number) throws Exception{
        if(number < 1 || number > 2){
            throw new OutOfRangeException("Out of range");
        }
    }

    public static void toCount(Participants participant){
        if(participant.meaning == Meanings.ROCK && participant.enemy.meaning == Meanings.SCISSORS ||
            participant.meaning == Meanings.PAPER && participant.enemy.meaning == Meanings.ROCK ||
            participant.meaning == Meanings.SCISSORS && participant.enemy.meaning == Meanings.PAPER){
            participant.check++;
        } else if (participant.meaning == participant.enemy.meaning){
            participant.drawCheck++;
        }
        System.out.println("Number wins of " + participant.name + " : " + participant.check);
        System.out.println("Number draws of " + participant.name + " : " + participant.drawCheck);
    }

    public static void toCountComplicatedVariation(Participants participant){
        if((participant.meaning == Meanings.ROCK && (participant.enemy.meaning == Meanings.SCISSORS ||
            participant.enemy.meaning == Meanings.LIZARD)) || (participant.meaning == Meanings.PAPER &&
            (participant.enemy.meaning == Meanings.ROCK || participant.enemy.meaning == Meanings.SPOCK))||
            (participant.meaning == Meanings.SCISSORS && (participant.enemy.meaning == Meanings.PAPER ||
            participant.enemy.meaning == Meanings.LIZARD)) || (participant.meaning == Meanings.LIZARD &&
            (participant.enemy.meaning == Meanings.SPOCK || participant.enemy.meaning == Meanings.PAPER)) ||
            (participant.meaning == Meanings.SPOCK && (participant.enemy.meaning == Meanings.SCISSORS ||
            participant.enemy.meaning == Meanings.ROCK))){
            participant.check++;
        } else if (participant.meaning == participant.enemy.meaning){
            participant.drawCheck++;
        }
        System.out.println("Number wins of " + participant.name + " : " + participant.check);
        System.out.println("Number draws of " + participant.name + " : " + participant.drawCheck);
    }

    public static void toShowCurrentResults(Human human, Computer computer){
        if(human.check > computer.check){
            System.out.println("Person is ahead!");
        } else if (human.check == computer.check){
            System.out.println("Nobody is ahead!");
        } else {
            System.out.println("Computer is ahead!");
        }
    }

    public static void printResults(Human human, Computer computer){
        int result = human.check + human.drawCheck + computer.check;
        double personWinRate = ( (double) human.check / (double) result ) * 100;
        double computerWinRate = ( (double) computer.check / (double) result ) * 100;
        System.out.println("Person's stats : ");
        printTable(result, personWinRate, human);
        System.out.println("\nComputer's stats : ");
        printTable(result, computerWinRate, computer);
        if(personWinRate > computerWinRate){
            System.out.println("\nPerson win!");
        } else if(personWinRate == computerWinRate){
            System.out.println("\nNobody won. Draw!");
        } else {
            System.out.println("\nComputer win!");
        }
    }

    public static void printTable(int result, double winRate, Participants participant){
        String line = "+----------------------------------------------------------------------+";
        String secondLine = "|----------------------------------------------------------------------|";
        String fmt = "|%8s |%10s |%8s |%15s |%20s |\n";
        System.out.println(line);
        System.out.printf(fmt, "Win", "Lose", "Draw", "Total games", "Win rate");
        System.out.println(secondLine);
        System.out.printf(fmt, participant.check, participant.enemy.check, participant.drawCheck, result, winRate);
        System.out.println(line);
    }
}