package Lotto;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class b_Lotto {
    public static void main(String[] args) {
        //------=====przyjmujemy zakłady======-------
        int[] kupon = new int[6];
        int[] wylosowano = new int[6];
        for(int i=0;i<6;i++) {
            boolean unique=false;
            while(!unique) {
                kupon[i] = podajLiczbe(i + 1);
                unique=true;
                for (int j = 0; j < i; j++) {
                    if (kupon[j] == kupon[i]){
                        unique=false;
                        System.out.println("Liczba powtórzona, spróbuj jeszcze raz.");
                    }
                }
            }
        }

        Arrays.sort(kupon);

        System.out.println("Wybrałeś liczby:\n"+Arrays.toString(kupon));

        wylosowano=totalizator();
        System.out.println("W losowaniu padły liczby:");
        System.out.println(Arrays.toString(wylosowano));

        System.out.println("Trafiłeś "+ porownaj(kupon, wylosowano)+ " razy");

    }

    private static int porownaj(int[] kupon, int[] wylosowano) {
        int ileTrafien=0;
        for(int liczba:kupon){
            for(int wyl : wylosowano){
                ileTrafien+=liczba==wyl?1:0;
            }
        }
        return ileTrafien;
    }

    private static int[] totalizator() {
        int[] wynik=new int[6];

        Integer[] arr = new Integer[49];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        Collections.shuffle(Arrays.asList(arr));

        for(int i =0; i<6; i++){
            wynik[i]=arr[i];
        }
        Arrays.sort(wynik);
        return wynik;
    }

    private static int podajLiczbe(int ktoraLiczba) {

        while(true) {
            System.out.println("Podaj liczbę nr.<<" + ktoraLiczba + ">>:");
            Scanner scan = new Scanner(System.in);
            int wybor;
            while (!scan.hasNextInt()) {
                System.out.println("To nie jest liczba naturalna...");
                System.out.println("Podaj liczbę nr.<<" + ktoraLiczba + ">>:");
                scan.nextLine();
            }

            wybor = scan.nextInt();
            if(wybor>0&&wybor<=49) {
                return wybor;
            }else{
                System.out.println("Liczba powinna mieścić się w zakresie 1-49, spóbuj jeszcze raz");
            }
        }

    }



}


