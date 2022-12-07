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
        chooseMeaningToHuman(human, computer, count);
    }

    public static void chooseMeaningToHuman(Human human, Computer computer, int count){
        selectMeaningToComputer(computer);
        System.out.println("1 - Rock\n2 - Paper\n3 - Scissors");
        System.out.println("If you want to leave from game - enter 4");
        System.out.print("Enter the value you want to select : ");
        try {
            int meaning = Integer.parseInt(sc.next());
            checkOutOfRange(meaning);
            if(meaning == 4){
                if(count!=0) {
                    System.out.println("Total results");
                    printResults(human, computer);
                }
                System.out.println("Good bye!");
            } else {
                count++;
                takeChosenMeaning(human, meaning, computer, count);
            }
        } catch (NumberFormatException e){
            System.out.println("You entered not numeric value");
            chooseMeaningToHuman(human, computer, count);
        } catch (Exception e) {
            System.out.println("You entered wrong number!");
            chooseMeaningToHuman(human,computer, count);
        }
    }

    public static void checkOutOfRange(int number) throws Exception{
        if(number < 1 || number > 4){
            throw new OutOfRangeException("You are out of range");
        }
    }


    public static void takeChosenMeaning(Human human, int meaning, Computer computer, int count){
        switch (meaning) {
            case 1 : human.meaning = Meanings.ROCK;
                break;
            case 2 : human.meaning = Meanings.PAPER;
                break;
            case 3 : human.meaning = Meanings.SCISSORS;
                break;
        }
        System.out.println("Person - " + human.meaning);
        System.out.println("Computer - " + computer.meaning);
        toCount(human);
        toCount(computer);
        System.out.println("-----------------------------------------------------------");
        chooseMeaningToHuman(human, computer, count);
    }

    public static void selectMeaningToComputer(Computer computer){
        int random = rnd.nextInt(3);
        if(random == 0){
            computer.meaning = Meanings.ROCK;
        } else if (random == 1){
            computer.meaning = Meanings.PAPER;
        } else {
            computer.meaning = Meanings.SCISSORS;
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