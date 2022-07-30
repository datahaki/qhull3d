package ch.alpine.qhull3d;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/** some test cases furnished by Mariano Zelke, Berlin */
class QuickHull3DTest {
  @Test
  void testSimple1() {
    double[] coords = new double[] { //
        21, 0, 0, //
        0, 21, 0, //
        0, 0, 0, //
        18, 2, 6, //
        1, 18, 5, //
        2, 1, 3, //
        14, 3, 10, //
        4, 14, 14, //
        3, 4, 10, //
        10, 6, 12, //
        5, 10, 15, };
    QuickHull3DHelper tester = new QuickHull3DHelper();
    tester.debugEnable = true;
    tester.test(coords);
  }

  @Test
  void testSimple2() {
    double[] coords = new double[] { 0.0, 0.0, 0.0, 21.0, 0.0, 0.0, 0.0, 21.0, 0.0, 2.0, 1.0, 2.0, 17.0, 2.0, 3.0, 1.0, 19.0, 6.0, 4.0, 3.0, 5.0, 13.0, 4.0,
        5.0, 3.0, 15.0, 8.0, 6.0, 5.0, 6.0, 9.0, 6.0, 11.0, };
    QuickHull3DHelper tester = new QuickHull3DHelper();
    tester.test(coords);
  }

  @Test
  void testRandom() {
    System.out.println("Testing 20 to 200 random points ...");
    for (int n = 20; n < 200; n += 10) { // System.out.println (n);
      for (int i = 0; i < 10; i++) {
        QuickHull3DHelper tester = new QuickHull3DHelper();
        double[] coords = TestHelper.randomPoints(n, 1.0);
        tester.test(coords);
      }
    }
  }

  @Test
  void testSpherical() {
    System.out.println("Testing 20 to 200 random points in a sphere ...");
    for (int n = 20; n < 200; n += 10) { // System.out.println (n);
      for (int i = 0; i < 10; i++) {
        QuickHull3DHelper tester = new QuickHull3DHelper();
        double[] coords = TestHelper.randomSphericalPoints(n, 1.0);
        tester.test(coords);
      }
    }
  }

  @Test
  void testCubed() {
    System.out.println("Testing 20 to 200 random points clipped to a cube ...");
    for (int n = 20; n < 200; n += 10) { // System.out.println (n);
      for (int i = 0; i < 10; i++) {
        QuickHull3DHelper tester = new QuickHull3DHelper();
        double[] coords = TestHelper.randomCubedPoints(n, 1.0, 0.5);
        tester.test(coords);
      }
    }
  }

  @Test
  void testGrid() {
    System.out.println("Testing 8 to 1000 randomly shuffled points on a grid ...");
    for (int n = 2; n <= 10; n++) { // System.out.println (n*n*n);
      for (int i = 0; i < 10; i++) {
        QuickHull3DHelper tester = new QuickHull3DHelper();
        double[] coords = TestHelper.randomGridPoints(n, 4.0);
        tester.test(coords);
      }
    }
  }

  @RepeatedTest(10)
  void testFails0() {
    QuickHull3DHelper tester = new QuickHull3DHelper();
    double[] coords = TestHelper.randomDegeneratePoints(10, 0);
    QuickHull3D hull = new QuickHull3D(coords);
    Exception exception = assertThrows(Exception.class, () -> hull.buildHull());
    assertEquals(exception.getMessage(), "Input points appear to be coincident");
  }

  @RepeatedTest(10)
  void testFails1() {
    QuickHull3DHelper tester = new QuickHull3DHelper();
    double[] coords = TestHelper.randomDegeneratePoints(10, 1);
    QuickHull3D hull = new QuickHull3D(coords);
    Exception exception = assertThrows(Exception.class, () -> hull.buildHull());
    assertEquals(exception.getMessage(), "Input points appear to be colinear");
  }

  @RepeatedTest(10)
  void testFails2() {
    QuickHull3DHelper tester = new QuickHull3DHelper();
    double[] coords = TestHelper.randomDegeneratePoints(10, 2);
    QuickHull3D hull = new QuickHull3D(coords);
    Exception exception = assertThrows(Exception.class, () -> hull.buildHull());
    assertEquals(exception.getMessage(), "Input points appear to be coplanar");
  }

  void testFailsN() {
    assertThrows(Exception.class, () -> new QuickHull3D(new double[3 * 10 + 1]));
    assertThrows(Exception.class, () -> new QuickHull3D(new double[3 * 10 + 2]));
  }
}
