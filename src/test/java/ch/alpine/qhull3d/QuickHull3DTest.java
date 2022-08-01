package ch.alpine.qhull3d;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.RepeatedTest;

/** some test cases furnished by Mariano Zelke, Berlin */
class QuickHull3DTest {
  @RepeatedTest(10)
  void testRandom() {
    for (int n = 20; n < 200; n += 10) {
      double[] coords = TestHelper.randomPoints(n, 1.0);
      QuickHull3DHelper tester = new QuickHull3DHelper();
      tester.test(coords);
    }
  }

  @RepeatedTest(10)
  void testSpherical() {
    for (int n = 20; n < 200; n += 10) {
      double[] coords = TestHelper.randomSphericalPoints(n, 1.0);
      QuickHull3DHelper tester = new QuickHull3DHelper();
      tester.test(coords);
    }
  }

  @RepeatedTest(10)
  void testCubed() {
    for (int n = 20; n < 200; n += 10) {
      double[] coords = TestHelper.randomCubedPoints(n, 1.0, 0.5);
      QuickHull3DHelper tester = new QuickHull3DHelper();
      tester.test(coords);
    }
  }

  @RepeatedTest(10)
  void testGrid() {
    for (int n = 2; n <= 10; n++) {
      double[] coords = TestHelper.randomGridPoints(n, 4.0);
      QuickHull3DHelper tester = new QuickHull3DHelper();
      tester.test(coords);
    }
  }

  @RepeatedTest(10)
  void testFails0() {
    double[] coords = TestHelper.randomDegeneratePoints(10, 0);
    QuickHull3D hull = new QuickHull3D(coords);
    Exception exception = assertThrows(Exception.class, hull::buildHull);
    // jan: from time to time the exception will have a different message with "colinear"
    assertEquals(exception.getMessage(), "Input points appear to be coincident");
  }

  @RepeatedTest(10)
  void testFails1() {
    double[] coords = TestHelper.randomDegeneratePoints(10, 1);
    QuickHull3D hull = new QuickHull3D(coords);
    Exception exception = assertThrows(Exception.class, hull::buildHull);
    assertEquals(exception.getMessage(), "Input points appear to be colinear");
  }

  @RepeatedTest(10)
  void testFails2() {
    double[] coords = TestHelper.randomDegeneratePoints(10, 2);
    QuickHull3D hull = new QuickHull3D(coords);
    Exception exception = assertThrows(Exception.class, hull::buildHull);
    assertEquals(exception.getMessage(), "Input points appear to be coplanar");
  }

  void testFailsN() {
    assertThrows(Exception.class, () -> new QuickHull3D(new double[3 * 10 + 1]));
    assertThrows(Exception.class, () -> new QuickHull3D(new double[3 * 10 + 2]));
  }
}
