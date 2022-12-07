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
        selectMeaningToComputer(computer);
        chooseMeaningToHuman(human);
    }

    public static void chooseMeaningToHuman(Human human){
        System.out.println("1 - Rock\n2 - Paper\n3 - Scissors");
        System.out.print("Enter the value you want to select : ");
        try {
            int meaning = Integer.parseInt(sc.next());
            checkOutOfRange(meaning);
            takeChosenMeaning(human, meaning);
        } catch (NumberFormatException e){
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void checkOutOfRange(int number) throws Exception{
        if(number < 1 || number > 3){
            throw new OutOfRangeException("You are out of range");
        }
    }


    public static void takeChosenMeaning(Human human, int meaning){
        switch (meaning) {
            case 1 : human.meaning = Meanings.ROCK;
                break;
            case 2 : human.meaning = Meanings.PAPER;
                break;
            case 3 : human.meaning = Meanings.SCISSORS;
        }
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
}