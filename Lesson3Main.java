public class Lesson3Main {
    public static void main(String[] args) {
        Product[] productsArray = new Product[5];
        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025",
                "Samsung Corp", "Korea", 5599, true);
        productsArray[1] = new Product("iPhone 16", "10.01.2025",
                "Apple", "USA", 6000, false);
        productsArray[2] = new Product("Sony PS5", "15.12.2024",
                "Sony", "Japan", 3500, true);
        productsArray[3] = new Product("MacBook Pro", "20.11.2024",
                "Apple", "China", 8000, false);
        productsArray[4] = new Product("Xbox Series X", "05.01.2025",
                "Microsoft", "USA", 3200, true);
        for (int i = 0; i < productsArray.length; i++) {
            productsArray[i].printInfo();
        }
        Park myPark = new Park();
        Park.Attraction rollerCoaster = myPark.new Attraction("Американские горки", "10:00 - 20:00", 500);
        System.out.println("Проверка парка: " + rollerCoaster.attractionName + " стоит " + rollerCoaster.cost + " руб.");
        Park.Attraction carousel = myPark.new Attraction("Карусель", "11:00 - 19:00", 200);
        System.out.println("Проверка парка: " + carousel.attractionName + " стоит " + carousel.cost + " руб.");
    }
}
