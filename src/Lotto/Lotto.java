package Lotto;

import java.util.Random;
import java.util.Scanner;

public class Lotto {
    public static void main(String[] args) {
        Random rand = new Random();
        int liczba = rand.nextInt(100)+1;
        int input;
        boolean zwyciestwo=false;

        while(zwyciestwo==false) {

            System.out.println("Zgadnij liczbę:");
            Scanner scan = new Scanner(System.in);

            while (!scan.hasNextInt()) {
                System.out.println("To nie jest liczba");
                System.out.println("Zgadnij liczbę:");
                scan = new Scanner(System.in);
            }

            input = scan.nextInt();

            if (input < liczba) {
                System.out.println("za mało");
            } else if (input > liczba) {
                System.out.println("za dużo");
            } else {
                System.out.println("Zgadłeś");
                zwyciestwo=true;
            }
        }


    }
}
