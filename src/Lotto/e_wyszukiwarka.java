package Lotto;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class e_wyszukiwarka {
    public static void main(String[] args) {


        File plik = new File("popular_words.txt");
        try {
            if (plik.exists() && plik.isFile()) {
                FileWriter kasownik = new FileWriter(plik,false);
                kasownik.append("");
                kasownik.close();
            } else {
                plik.createNewFile();
            }
//        -----====zasysamy dane z portali=====-----

            try {
                zrzucZPortalu("http://www.wp.pl", "div._3I9ewz");
            } catch (IOException e) {
                System.out.println("WP nie zadziałało");
            }

            try {
                zrzucZPortalu("http://joemonster.org/", "a.title");
            } catch (IOException e) {
                System.out.println("Joe nie zadziałało");
            }

            try {
                zrzucZPortalu("https://www.wykop.pl/", "h2 a");
            } catch (IOException e) {
                System.out.println("wykop nie zadzialal");
            }

        }catch(IOException e){
            System.out.println("Coś nie tak z systemem plików - nie udało się, może następnym razem.");
            e.printStackTrace();
        }


//        ---------======usuwamy duble=======-------
        Path sciezka =Paths.get(plik.toString());


        ArrayList<String> out= new ArrayList<>();
        try {
            List<String> listaDoPosprzatania = Files.readAllLines(sciezka);
            for(int i=0; i<listaDoPosprzatania.size();i++){
                if(listaDoPosprzatania.lastIndexOf(listaDoPosprzatania.get(i))==i){
                    out.add(listaDoPosprzatania.get(i));
                }
            }
            Files.write(sciezka, out);
            out.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        ------=====zapisujemy plik wynikowy=====--------

        String[] cenzor={"oraz", "ponieważ","wśród","kilka","każdego"};
        List<String> cenzorList = Arrays.asList(cenzor);



        Path sciezkaCenz = Paths.get("filtered_popular_words.txt");
        try {
            Files.deleteIfExists(sciezkaCenz);

            for(String slowo :Files.readAllLines(plik.toPath())){
                if(slowo.length()<=3 ||cenzorList.contains(slowo)){
                    System.out.println("Wypadło: "+slowo);
                }else{
                    out.add(slowo);
                }
            }
            Files.write(sciezkaCenz,out);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void zrzucZPortalu(String portal, String selector)
            throws IOException {
        Connection connect = Jsoup.connect(portal);
        Document doc = null;
        File plik = new File("popular_words.txt");

        doc = connect.get();
        Elements naglowki= doc.select(selector);

        try{
            FileWriter pisak=new FileWriter(plik, true);


            for(Element naglowek: naglowki) {
                StringTokenizer token = new StringTokenizer(naglowek.text().toLowerCase(), " ,.-?!:()\n1234567890/\"\'#");
                while (token.hasMoreElements()) {
                    String slowko =token.nextToken();
                        pisak.append(slowko + "\n");
                }
            }

            pisak.close();
        }catch(IOException e){
                System.out.println("Zapis do pliku sie nie udał (portal:" + portal);
        }
    }


}
