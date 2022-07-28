package ch.alpine.qhull3d;

import org.junit.jupiter.api.Test;

class QhullTest {
  static double[] coords = new double[] {};
  static int[][] faces = new int[][] {};

  @Test
  void testSimple() {
    QuickHull3D hull = new QuickHull3D();
    QuickHull3DTest tester = new QuickHull3DTest();
    hull = new QuickHull3D();
    for (int i = 0; i < 100; i++) {
      double[] pnts = tester.randomCubedPoints(100, 1.0, 0.5);
//      hull.setFromQhull(pnts, pnts.length / 3, /* triangulated= */false);
      pnts = tester.addDegeneracy(QuickHull3DTest.VERTEX_DEGENERACY, pnts, hull);
      if (!hull.check(System.out)) {
        System.out.println("failed for qhull triangulated");
      }
      if (!hull.check(System.out)) {
        System.out.println("failed for qhull regular");
      }
      // hull = new QuickHull3D ();
      hull.build(pnts, pnts.length / 3);
      hull.triangulate();
      if (!hull.check(System.out)) {
        System.out.println("failed for QuickHull3D triangulated");
      }
      // hull = new QuickHull3D ();
      hull.build(pnts, pnts.length / 3);
      if (!hull.check(System.out)) {
        System.out.println("failed for QuickHull3D regular");
      }
    }
  }
}
