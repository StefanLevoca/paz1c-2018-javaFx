package sk.gursky.paz1c.lambdy;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lambdy {

    private interface Vypisovac {
        public void vypis(String retazec);
    }

    public static void main(String[] args) {
        Vypisovac zlyVypisovac = new Vypisovac() {
            
            @Override
            public void vypis(String retazec) {
                System.out.println("zlo: \'" + retazec + "\'");
            }
        };
        zlyVypisovac.vypis("Trump");
        
        Vypisovac dobryVypisovac = retazec -> System.out.println("super výpis: \"" + retazec + "\"");
        dobryVypisovac.vypis("paráda");

        Consumer<String> vypisovacKravin = s -> System.out.println("kravina: \'" + s + "\'");  // vsetky preddefinované interfejsy: https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
        vypisovacKravin.accept("obladi oblada");
        
        final int[] pocet = {0};
        Vypisovac sPocitadlom = s -> {
            pocet[0]++;
            System.out.println(pocet[0] + "-ty výpis: " + s);
        };
        sPocitadlom.vypis("raz");
        sPocitadlom.vypis("dva");
        sPocitadlom.vypis("tri");
        
        String[] namesArray = {"Jano Pekný", "Hugo Múdry", "Jana Šikovná", "Vilo Vysoký"};
        List<String> names = Arrays.asList(namesArray);

        List<String> zacinajuciNaJ = names.stream()
                .filter(s -> s.startsWith("J")) //vyberame si, ktore chceme prvky cez filter
                .collect(Collectors.toList()); 
        System.out.println("zacinajuci na j: " + zacinajuciNaJ);

        List<String> velkymiPismenami = names.stream()
                .map(s -> s.toUpperCase())      //menime prvky v prude cez map
                .collect(Collectors.toList()); 
        System.out.println("velkymi pismenami: " + velkymiPismenami);

        List<Integer> poctyPismen = names.stream()
                .map(String::length)             // metodu objektu mozme pouzit ako lambda vyraz
                .collect(Collectors.toList());   
        System.out.println("počty písmen: " + poctyPismen);
        
        Comparator<String> podlaPriezviska = (s1,s2) -> {
          String p1 = s1.split(" ")[1];
          String p2 = s2.split(" ")[1];
          Collator skCollator = Collator.getInstance(new Locale("sk"));
          return skCollator.compare(p1, p2);
        };

        List<String> zotriedene = names.stream()
                .sorted(podlaPriezviska)        // prúd zotriedime cez sorted
                .collect(Collectors.toList());    
        System.out.println("podla priezviska: " + zotriedene);
        
        String pospajane = names.stream()
                .reduce((s1,s2)-> "(" + s1 +") "+ s2)   // vezmeme prve dva prvky a nahradime ho jednym, potom ho vezmeme a spolu s dalsim urobime jeden, ...
                .get();
        System.out.println("pospájané :" + pospajane);

        Map<Integer,Integer> poctyVelkosti = names.stream()
                .collect(Collectors.toMap(s -> s.length(), s -> 1, (a,b) -> a + b));   // kluce su dlzka, hodnoty su 1, pre rovnake kluce scitame hodnotu z mapu s novou
        System.out.println("počty velkosti: " + poctyVelkosti);
        
        IntStream.range(90, 100).boxed()                        //vygenerujeme stream Integerov
                .sorted(Comparator.reverseOrder())              //zotriedime ho zostupne
                .map(i -> i + " bottles of beer on the wall")   //namapujeme kazdy integer na string
                .forEach(vypisovacKravin);                      //kazdy string posleme do funkcie vypisovacKravin
    }
}

