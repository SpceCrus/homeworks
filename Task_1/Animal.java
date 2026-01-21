package Task_1;

public abstract class Animal {
    protected String name;
    protected boolean isMale;
    public static int count = 0;

    public Animal(String name, boolean isMale) {
        this.name = name;
        this.isMale = isMale;
        count++;
    }

    public abstract void run(int distance);
    public abstract void swim(int distance);
}