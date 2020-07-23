package ru.stqa.course.sandbox;

import static ru.stqa.course.sandbox.Point.distance;

public class launchPoint
{
    public static void main(String[] args)
    {
        Point p1 = new Point(10, 2);
        Point p2 = new Point(5, 2);

        //Вызов функции distance
        System.out.println("Расстояние между двумя точками, с координатами: "  + p1.toString() + " и " + p2.toString() + " = " +
                distance(p1, p2));

        //Вызов метода distance
        System.out.println("Расстояние между двумя точками, с координатами: " + p1.toString() + " и " + p2.toString() + " = " +
                p1.distance(p2));
    }

}
