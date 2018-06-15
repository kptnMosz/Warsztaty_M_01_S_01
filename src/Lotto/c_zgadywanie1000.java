package Lotto;

import java.util.Scanner;

public class c_zgadywanie1000 {

    public static void main(String[] args) {
        int min=1, max=1000, gues, count=0;


        boolean zwyciestwo = false;
        System.out.println("Wymyśl liczbę od 1 do 1000");
        while(!zwyciestwo) {
            Scanner scan = new Scanner(System.in);
            gues = (max - min) / 2 + min;
            System.out.println("Proba " + ++count + ": Czy twoja liczba to:" + gues + "?");
            String odp = "";

            while (!"t".equals(odp) && !"w".equals(odp) && !"m".equals(odp)) {
                System.out.println("Odpowiedz << t >>jeśli zgadłem, << m >> jeśli Twoja liczba jest mniejsza, << w >>, jeśli Twoja liczba jest większa");
                odp = scan.nextLine();
            }

            switch (odp) {
                case "t":
                    System.out.println("Wygrałem!");
                    return;

                case "w":
                    min = gues;
                    break;
                case "m":
                    max = gues;
                    break;
            }
        }







    }


}
