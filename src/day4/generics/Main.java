package day4.generics;

public class Main {
    public static void main(String[] args) {

        Product product = new Product();
        Computer computer = new Computer();
        Dress dress = new Dress();

        Favorites<Product> favorites = new Favorites<Product>();
        favorites.addToFavorites(product);
        favorites.addToFavorites(computer);
        favorites.addToFavorites(dress);
    }
}
