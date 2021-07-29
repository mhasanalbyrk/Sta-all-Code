package day4.generics;

public class Util {

    public static <K extends Product> boolean compare(Favorites<K> p1, Favorites<K> p2){
        return true;
    }
}
