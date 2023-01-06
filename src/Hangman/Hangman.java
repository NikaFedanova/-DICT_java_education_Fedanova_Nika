package org.example.Hangman;

/*
!!!! Сделал через  Array String переменную guessedLetters, но в дальнейшем понял что лучше бы Char использовал -_-
В этой работе я решил работать через инициализированный обьект, в отличии от прошлого проекта. Для разнообразия*/


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    static ArrayList<String> words = new ArrayList<String>();
    ArrayList<String> hiddenWord = new ArrayList<String>();
    ArrayList<String> guessedLetters = new ArrayList<String>();
    private int lives = 8;
    private String word = "";
    static void appendList(){
        words.add("java");
        words.add("python");
        words.add("javascript");
        words.add("kotlin");
    }

    protected void randomWord(){
        String word;
        Random randomizer = new Random();
        word = words.get(randomizer.nextInt(words.size()));
        this.word = word;
    }

    protected void createHiddenLine(String word){
        for (int index=0; index < word.length(); index++){
            hiddenWord.add("-");
        }
    }

    void showWord(){
        System.out.println(String.join("", hiddenWord));
    }

    Hangman(){ // Для тренировки добавил конструктор
        randomWord();
        createHiddenLine(word);  // Создаём скрытое слово, которое будем показывать игроку
        game();
    }

    void game(){
        do{
            System.out.println("Lives: " + lives);
            if (lives < 1){
                System.out.println("You're lost!");
                break;
            } else if (word.equals(String.join("", hiddenWord))) {
                System.out.println("You're won!\nYour word is " + word.toUpperCase());
                break;
            }
            showWord();
            System.out.print("Input a letter:\n>>> ");
            Scanner scanner = new Scanner(System.in);
            String letter = scanner.nextLine().toLowerCase();
            if (letter.length() == 1 && !Character.isLetter(letter.charAt(0))) {
                System.out.println("It is not an English letter or, you wrote more than one letter!");
            }
            else if (guessedLetters.contains(letter)){
                lives--;
                System.out.println("You have already chosen this letter!");
            }
            else if (word.contains(letter)){
                for (int index=0; index<word.length(); index++){
                    if (letter.equals(Character.toString(word.charAt(index)))){
                        hiddenWord.set(index, letter);
                    }
                }
                guessedLetters.add(letter);
            }
            else {
                System.out.println("That letter doesn't appear in the word!");
                lives--;
            }
        }while (true);
    }

    public static void main(String[] args) {
        System.out.println("HANGMAN");
        appendList();
        while (true) {
            System.out.print("Type \"play\" to play the game, \"exit\" to quit: ");
            Scanner scanner = new Scanner(System.in);
            String message = scanner.nextLine().toLowerCase();
            if (message.equals("play")){
                Hangman hangman = new Hangman();
            }
            else if (message.equals("exit")){
                System.out.print("GoodBye:)");
                break;
            }
        }
    }
}

