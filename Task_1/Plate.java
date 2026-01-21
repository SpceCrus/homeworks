package Task_1;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = Math.max(food, 0);
    }

    public boolean decreaseFood(int n) {
        if (food >= n) {
            food -= n;
            return true;
        }
        return false;
    }

    public void addFood(int amount) {
        if (amount > 0) {
            this.food += amount;
            System.out.println("В миску добавили " + amount + " еды. Теперь в ней: " + food);
        } else {
            System.out.println("Нельзя добавить отрицательное количество еды!");
        }
    }
}
