public class Product {
    String name;
    String productionDate;
    String manufactured;
    String country;
    int price;
    Boolean isReserved;

    public Product(String name, String productionDate, String manufactured, String country, int price, Boolean isReserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufactured = manufactured;
        this.country = country;
        this.price = price;
        this.isReserved = isReserved;
    }

    public void printInfo() {
        System.out.println("Товар: " + name + " | Дата производства: " + productionDate + " | Производитель: " + manufactured + " | Страна происхождения: " + country + " | Цена: " + price + " | Бронь: " + isReserved);
    }
}
