package Task_1;

public class MainTask1 {
    public static void main(String[] args) {
        Plate plate = new Plate(50);

        Cat[] cats = {
                new Cat("Бустер", true),
                new Cat("Юка", false),
                new Cat("Стёпа", true),
                new Cat("Мурка", false)
        };

        Dog[] dogs = {
                new Dog("Бобик", true),
                new Dog("Буся", false),
                new Dog("Шарик", true),
                new Dog("Моська", false)
        };

        System.out.println("--- Время обеда ---");
        for (Cat cat : cats) {
            cat.eat(plate, 15);
        }

        System.out.println("\n--- Статус сытости ---");
        for (Cat cat : cats) {
            cat.infoFullness();
        }

        System.out.println("\n--- Физкультура ---");
        cats[0].run(200);
        dogs[2].run(234);
        dogs[1].swim(7);
        cats[3].swim(6);

        System.out.println("\n--- Итоги по зоопарку ---");
        System.out.println("Всего животных создано: " + Animal.count);
        System.out.println("Из них котов: " + Cat.count);
        System.out.println("Из них собак: " + Dog.count);
    }
}