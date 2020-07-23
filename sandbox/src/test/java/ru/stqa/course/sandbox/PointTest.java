package ru.stqa.course.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
    @Test
    public void testDistance()
    {
        Point p1 = new Point(8, 0);
        Point p2 = new Point(3,0);
        //assert p1.distance(p2) == 5;
        Assert.assertEquals(p1.distance(p2), 5);
    }
}
