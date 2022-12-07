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