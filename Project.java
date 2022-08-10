import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Project {
    public static void main(String[] args) {
        //Scanner osztály meghívása a beolvasáshoz
        Scanner scanner = new Scanner(System.in);
        //felvettem egy listát a teszteléshez
        ArrayList<String> countries = new ArrayList<>() {{
            add("Hungary");
            add("Germany");
            add("Russia");
        }};
        //véletlen szám generátor meghívása
        Random r = new Random();
        //random kiválasztás a listából
        String word = countries.get(r.nextInt(countries.size()));
        //a választott szó karakterekre bontása
        char[] wordArray = word.toCharArray();
        System.out.println(word);

        ArrayList<Character> guesses = new ArrayList<>();
        printing(word, guesses);

        int lives = 3;


        while (true) {

            System.out.println("Please enter a letter: ");
            String letterGuess = scanner.nextLine();
            if (letterGuess.contentEquals("quit")){
                System.out.println("Game over!");
                lives=0;
                break;
            }
            //karakter hozzáadása a listához
            guesses.add(letterGuess.charAt(0));
            boolean found = false;
            //megnézzük, hogy a karakter benne van-e a szóban
            for (int i = 0; i < wordArray.length; i++) {
                if (letterGuess.charAt(0) == wordArray[i] || letterGuess.charAt(0)==Character.toLowerCase(wordArray[0])) {
                    found = true;
                }
            }
//            for (int j=0; j < wordArray.length; j++){
//                if (letterGuess.charAt(0)==Character.toLowerCase(wordArray[0])){
//                    System.out.print(letterGuess.charAt(0));
//                }
//            }
            //ha nincs benne csökkentjük az életek számát
            if (!found) {
                lives--;

            }
            //ha elfogy az életünk, vége a játéknak
            if (lives == 0) {
                System.out.println("You lose!");
                break;
            }
            //jelenlegi életszám kiiratása
            System.out.printf("You have %d lives left.\n", lives);
            //ha a printing függvény igaz értékkel tér vissza, véget ér a játék
            if (printing(word, guesses)) {
                break;
            }


        }
        //amennyiben nem haltunk meg, a győztünk felirat jelenik meg
        if (lives != 0) {
            System.out.println("You win!");
        }


    }

    //private static void play(String word, int lives) {
    // TODO Implement the game here!
    // }
    private static boolean printing(String word, ArrayList<Character> guesses) {
        int correctCount = 0;
        //helyes betű esetén kiiratjuk a betűt, a többi helyen pedig marad a kötőjel
        for (int i = 0; i < word.length(); i++) {
            if (guesses.contains(word.charAt(i)) || guesses.contains(Character.toLowerCase(word.charAt(i)))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else {

                System.out.print("-");
            }

        }
        System.out.println();
        //amennyiben a szó hossza és a helyes válaszok száma megegyezik, mi nyertünk
        return word.length() == correctCount;
    }
}
