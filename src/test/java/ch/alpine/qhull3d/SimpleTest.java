package ch.alpine.qhull3d;

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
    QuickHull3D hull = new QuickHull3D();
    hull.build(points);
    System.out.println("Vertices:");
    Point3d[] vertices = hull.getVertices();
    for (Point3d pnt : vertices) {
      System.out.println(pnt.x + " " + pnt.y + " " + pnt.z);
    }
    System.out.println("Faces:");
    int[][] faceIndices = hull.getFaces(0);
    for (int i = 0; i < vertices.length; i++) {
      for (int k = 0; k < faceIndices[i].length; k++) {
        System.out.print(faceIndices[i][k] + " ");
      }
      System.out.println();
    }
  }
}
