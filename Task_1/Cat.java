package Task_1;


public class Cat extends Animal {
    public static int count = 0;
    private boolean isFull = false;

    public Cat(String name, boolean isMale) {
        super(name, isMale);
        count++;
    }

    @Override
    public void run(int distance) {
        String action = isMale ? "пробежал" : "пробежала";
        String fail = isMale ? "не смог пробежать" : "не смогла пробежать";

        if (distance <= 200) {
            System.out.println(name + " " + action + " " + distance + " м.");
        } else {
            System.out.println(name + " " + fail + " " + distance + " м. (лимит 200)");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать!");
    }

    public void eat(Plate plate, int amount) {
        if (plate.decreaseFood(amount)) {
            this.isFull = true;
            System.out.println(name + " " + (isMale ? "наелся и спит" : "наелась и спит"));
        } else {
            System.out.println(name + " " + (isMale ? "не тронул" : "не тронула") + " еду, в миске маловато.");
        }
    }

    public void infoFullness() {
        String species = isMale ? "Кот" : "Кошка";

        String status;
        if (isFull) {
            status = isMale ? "сыт" : "сыта";
        } else {
            status = isMale ? "голоден" : "голодна";
        }

        System.out.println(species + " " + name + " сейчас " + status);
    }
}