package day4.generics;

import java.util.ArrayList;
import java.util.List;

public class Favorites<T extends Product> {
    List<T> fav = new ArrayList<T>();

    public void addToFavorites(T t){
        fav.add(t);
    }


}
