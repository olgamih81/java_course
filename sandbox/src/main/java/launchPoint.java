public class launchPoint {
    public static void main(String[] args)
    {
        Point p1 = new Point(10, 5);
        Point p2 = new Point(8, 2);
        System.out.println("Расстояние между двумя точками, с координатами: " + p1.toString() + " и " + p2.toString() + " = "+
                distance(p1, p2));
    }

    public static double distance(Point p1, Point p2)
    {
        return Math.sqrt((p2.x - p1.x)*(p2.x - p1.x) + (p2.y - p1.y)*(p2.y - p1.y));
    }
}
