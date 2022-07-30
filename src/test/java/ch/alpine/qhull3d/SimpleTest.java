package ch.alpine.qhull3d;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/** Simple example usage of QuickHull3D. Run as the command
 * <pre>
 * java quickhull3d.SimpleExample
 * </pre> */
class SimpleTest {
  /** Run for a simple demonstration of QuickHull3D. */
  @Test
  void testSimple() {
    // x y z coordinates of 6 points
    double[] points = new double[] { //
        0.0, 0.0, 0.0, //
        1.0, 0.5, 0.0, //
        2.0, 0.0, 0.0, //
        0.5, 0.5, 0.5, //
        0.0, 0.0, 2.0, //
        0.1, 0.2, 0.3, //
        0.0, 2.0, 0.0, };
    QuickHull3D hull = new QuickHull3D(points);
    hull.buildHull();
    int[][] faceIndices = hull.getFaces(QuickHull3D.POINT_RELATIVE);
    assertArrayEquals(faceIndices[0], new int[] { 2, 4, 0 });
    assertArrayEquals(faceIndices[1], new int[] { 6, 2, 0 });
    assertArrayEquals(faceIndices[2], new int[] { 6, 0, 4 });
    assertArrayEquals(faceIndices[3], new int[] { 6, 4, 2 });
  }
}
