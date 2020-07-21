public class launchPoint {
    public static void main(String[] args) {
        Point p1 = new Point(10, 2);
        Point p2 = new Point(5, 2);
        System.out.println("Расстояние между двумя точками, с координатами: " + p1.toString() + " и " + p2.toString() + " = " +
                p1.distance(p2));
    }
}
