package Lotto;

import java.util.Random;

public class d_rzutyKostka {
    public static void main(String[] args) {
        System.out.println("Wynik: "+rzucKostka("20d10-3"));
        System.out.println("Wynik: "+rzucKostka("20d10-3"));
        System.out.println("Wynik: "+rzucKostka("20d10-3"));
        System.out.println("Wynik: "+rzucKostka("20d10-3"));
        System.out.println("Wynik: "+rzucKostka("20d10-3"));
        System.out.println("Wynik: "+rzucKostka("20D103"));
        System.out.println("Wynik: "+rzucKostka("20d9-30"));
    }

    private static int rzucKostka(String typ) {
        System.out.println("Kość: "+typ);
     int  iloscRzutow=0, indexD=0, indexZnaku=0, modyfikator=0, kostka, wynik=0;
     String bufor, dostepne="D3, D4, D6, D8, D10, D12, D20, D100";


     try{
         indexD=typ.indexOf("D")>typ.indexOf("d")?typ.indexOf("D"):typ.indexOf("d");
         iloscRzutow = Integer.parseInt(typ.substring(0,indexD));
     }catch (StringIndexOutOfBoundsException e){
         System.out.println("Nieprawidłowy format rzutu");
         return -1000000;
     }

     indexZnaku=typ.indexOf("-")>typ.indexOf("+")?typ.indexOf("-"):typ.indexOf("+");
     try{
         modyfikator=Integer.parseInt(typ.substring(indexZnaku));
     }catch (StringIndexOutOfBoundsException e){
         indexZnaku=typ.length();
         modyfikator=0;
     }

     bufor=typ.substring(indexD,indexZnaku).toUpperCase();
     if(dostepne.indexOf(" "+bufor+",")<0){
         System.out.println("Nielegalna kostka!");
         return -1000000;
     }

     kostka=Integer.parseInt(typ.substring(indexD+1,indexZnaku));


     for(int i=0;i<iloscRzutow;i++){
         Random rand=new Random();
         wynik+=rand.nextInt(kostka)+1;
     }
     return wynik+modyfikator;
    }


}
