package ru.stqa.course.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.stqa.course.sandbox.Point.distance;

public class PointTest {
    @Test
    public void testDistance()
    {
        Point p1 = new Point(10, 5);
        Point p2 = new Point(3,5);

        Assert.assertEquals(distance(p1, p2), 10);
        Assert.assertEquals(p1.distance(p2), 7);
    }

    @Test
    public void testDistanceNull()
    {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0,0);

        Assert.assertEquals(distance(p1, p2), 0);
        Assert.assertEquals(p1.distance(p2), 0);
    }

    @Test
    public void testDistanceNegative()
    {
        Point p1 = new Point(-10, -1);
        Point p2 = new Point(-5,-1);

        Assert.assertEquals(distance(p1, p2), 5);
        Assert.assertEquals(p1.distance(p2), 5);
    }

    @Test
    public void testDistanceZero()
    {
        Point p1 = new Point(5, 5);
        Point p2 = new Point(5,5);

        Assert.assertEquals(distance(p1, p2), 0);
        Assert.assertEquals(p1.distance(p2), 0);
    }

    @Test
    public void testDistanceOnePoint()
    {
        Point p1 = new Point(1, 1);

        Assert.assertEquals(distance(p1, p1), 0);
        Assert.assertEquals(p1.distance(p1), 0);
    }
    @Test
    public void testDistanceSymmetry()
    {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(5,3);

        Assert.assertEquals(distance(p1, p2), distance(p2, p1));
        Assert.assertEquals(p1.distance(p2), p2.distance(p1));
    }

}

