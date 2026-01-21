package Task_1;

public class Dog extends Animal {
    public static int count = 0;

    public Dog(String name, boolean isMale) {
        super(name, isMale);
        count++;
    }

    @Override
    public void run(int distance) {
        if (distance <= 500) {
            String action = isMale ? "пробежал" : "пробежала";
            System.out.println(name + " " + action + " " + distance + " м.");
        } else {
            String fail = isMale ? "не смог пробежать" : "не смогла пробежать";
            System.out.println(name + " " + fail + " " + distance + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance <= 10) {
            String action = isMale ? "проплыл" : "проплыла";
            System.out.println(name + " " + action + " " + distance + " м.");
        } else {
            String fail = isMale ? "не смог проплыть" : "не смогла проплыть";
            System.out.println(name + " " + fail + " " + distance + " м.");
        }
    }
}
