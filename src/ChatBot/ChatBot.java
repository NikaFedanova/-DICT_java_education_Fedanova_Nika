package org.example.ChatBot;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*Сделал static функции, чтобы не создавать обьект, а обращаться напрямую к классу и пользоваться функциями.
При желании всё-равно можно вызвать через созданный обьект!
Для разнообразия и в некоторой степени для удобства добавил, функции возвращающие значения, например getPositiveNumber,
которая задействована в трёх других функиях, чтобы не нарушать правило DRY*/
class Bot{
    protected static void yourName(){ // Получаем имя пользователя
        boolean isName = false;
        String name = "";
        do{
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            Pattern pattern = Pattern.compile("^([А-Я]{0,1}[а-яё]{1,23}|[A-Z]{0,1}[a-z]{1,23})$");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                name = line.substring(matcher.start(), matcher.end());
            }
            if (name.length() > 0) {
                isName = true;
                System.out.println("What a great name you have, " + name + "!");
            }
            else {
                System.out.print("I don't think it's a name, try again. (A name always begins with a capital letter.)\n>>> ");
            }
        }while (!isName);
    }

    protected static double yourAge (){ // Рассчитываем примерный возвраст пользователя
        System.out.print("Let me guess your age. Enter remainders of dividing your age by 3, 5 and 7.\n");
        double age;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        do {
            numbers.add(getPositiveNumber());
        }while (numbers.size() < 3);

        age = (numbers.get(0) * 70 + numbers.get(1) * 21 + numbers.get(2) * 15) % 105;
        return age;
    }


    protected static void counter (){ // Печать чисел, от 0 до желаемого
        System.out.println("Now I will prove to you that I can count to any number you want.");
        int number = getPositiveNumber();
        for (int index=0; index <= number; index++){
            System.out.println(index + " !");
        }
    }

    protected static int getPositiveNumber(){ // Функция получения положительного числа. Используем в counter и yourAge
        int number;
        do {
            System.out.print("Enter number > 0: ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            try {
                number = Integer.parseInt(line);
                if (number > 0){
                    break;
                }else {
                    System.out.print("You should write positive number!\n");
                }
            }
            catch (NumberFormatException ex){
                System.out.print("It's not a number!\n");
            }

        }while (true);
        return number;
    }

    protected static void test(){
        System.out.println("С помощью какой комбинации клавиш можно дать компьютеру команду \"вырезать\"? (Пример ответа: 3)");
        boolean isRight = false;
        System.out.println("1. Ctrl + C\n2. Ctrl + X\n3. Ctrl + V\n4. Ctrl + T");
        do {
            int answer = getPositiveNumber();
            if (answer == 2){
                isRight = true;
                System.out.println("Great, you right!");
            }else {
                System.out.println("Please, try again.");
            }
        }while (!isRight);

    }

}


public class ChatBot {
    public static void main(String[] args) { // Начало программы
        System.out.print("Hello! My name is ALFREDO.\nI was created in 1995.\nPlease, remind me your name:\n>>> ");
        Bot.yourName();
        System.out.println("--------------------------------------------");
        System.out.println("Your age is " + Bot.yourAge() + "; that's a good time to start programming!");
        System.out.println("--------------------------------------------");
        Bot.counter();
        System.out.println("--------------------------------------------");
        Bot.test();
        System.out.println("Goodbye, have a nice day!");
    }
}
