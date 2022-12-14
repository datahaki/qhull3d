package ch.alpine.qhull3d;

import org.junit.jupiter.api.RepeatedTest;

class QhullTest {
  @RepeatedTest(100)
  void testSimple() {
    double[] pnts = TestHelper.randomCubedPoints(100, 1.0, 0.5);
    QuickHull3D hull = new QuickHull3D(pnts);
    // hull.buildHull();
    pnts = TestHelper.addDegeneracy(QuickHull3DHelper.VERTEX_DEGENERACY, pnts, hull);
    if (!hull.check(System.out)) {
      System.out.println("failed for qhull triangulated");
    }
    if (!hull.check(System.out)) {
      System.out.println("failed for qhull regular");
    }
    // hull = new QuickHull3D ();
    hull.buildHull();
    // hull.triangulate();
    if (!hull.check(System.out)) {
      System.out.println("failed for QuickHull3D triangulated");
    }
    // hull = new QuickHull3D ();
    hull.buildHull();
    if (!hull.check(System.out)) {
      System.out.println("failed for QuickHull3D regular");
    }
  }
}
