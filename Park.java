public class Park {
    String parkName;
    public class Attraction {
        String attractionName;
        String workingHours;
        int cost;

        public Attraction(String attractionName, String workingHours, int cost) {
            this.attractionName = attractionName;
            this.workingHours = workingHours;
            this.cost = cost;
        }
    }
}
