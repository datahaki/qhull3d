package ch.alpine.qhull3d;

import java.util.Random;

enum TestHelper {
  ;
  /** Returns the coordinates for <code>num</code> points whose x, y, and
   * z values are randomly chosen within a given range.
   *
   * @param num number of points to produce
   * @param range coordinate values will lie between -range and range
   * @return array of coordinate values */
  public static double[] randomPoints(int num, double range) {
    double[] coords = new double[num * 3];
    for (int i = 0; i < num; i++) {
      for (int k = 0; k < 3; k++) {
        coords[i * 3 + k] = 2 * range * (QuickHull3DHelper.RANDOM.nextDouble() - 0.5);
      }
    }
    return coords;
  }

  static void randomlyPerturb(Vector3d pnt, double tol) {
    pnt.x += tol * (QuickHull3DHelper.RANDOM.nextDouble() - 0.5);
    pnt.y += tol * (QuickHull3DHelper.RANDOM.nextDouble() - 0.5);
    pnt.z += tol * (QuickHull3DHelper.RANDOM.nextDouble() - 0.5);
  }

  /** Returns the coordinates for <code>num</code> randomly
   * chosen points which are degenerate which respect
   * to the specified dimensionality.
   *
   * @param num number of points to produce
   * @param dimen dimensionality of degeneracy: 0 = coincident,
   * 1 = colinear, 2 = coplaner.
   * @return array of coordinate values */
  public static double[] randomDegeneratePoints(int num, int dimen) {
    double[] coords = new double[num * 3];
    Vector3d pnt = new Vector3d();
    Vector3d base = new Vector3d();
    TestHelper.setRandom(base, -1, 1, QuickHull3DHelper.RANDOM);
    double tol = StaticHelper.DOUBLE_PREC;
    if (dimen == 0) {
      for (int i = 0; i < num; i++) {
        pnt.set(base);
        randomlyPerturb(pnt, tol);
        coords[i * 3 + 0] = pnt.x;
        coords[i * 3 + 1] = pnt.y;
        coords[i * 3 + 2] = pnt.z;
      }
    } else if (dimen == 1) {
      Vector3d u = new Vector3d();
      TestHelper.setRandom(u, -1, 1, QuickHull3DHelper.RANDOM);
      u.normalize();
      for (int i = 0; i < num; i++) {
        double a = 2 * (QuickHull3DHelper.RANDOM.nextDouble() - 0.5);
        pnt.scale(a, u);
        pnt.add(base);
        randomlyPerturb(pnt, tol);
        coords[i * 3 + 0] = pnt.x;
        coords[i * 3 + 1] = pnt.y;
        coords[i * 3 + 2] = pnt.z;
      }
    } else // dimen == 2
    {
      Vector3d nrm = new Vector3d();
      TestHelper.setRandom(nrm, -1, 1, QuickHull3DHelper.RANDOM);
      nrm.normalize();
      for (int i = 0; i < num; i++) { // compute a random point and project it to the plane
        Vector3d perp = new Vector3d();
        TestHelper.setRandom(pnt, -1, 1, QuickHull3DHelper.RANDOM);
        perp.scale(pnt.dot(nrm), nrm);
        pnt.sub(perp);
        pnt.add(base);
        randomlyPerturb(pnt, tol);
        coords[i * 3 + 0] = pnt.x;
        coords[i * 3 + 1] = pnt.y;
        coords[i * 3 + 2] = pnt.z;
      }
    }
    return coords;
  }

  /** Returns the coordinates for <code>num</code> points whose x, y, and
   * z values are randomly chosen to lie within a sphere.
   *
   * @param num number of points to produce
   * @param radius radius of the sphere
   * @return array of coordinate values */
  public static double[] randomSphericalPoints(int num, double radius) {
    double[] coords = new double[num * 3];
    Vector3d pnt = new Vector3d();
    for (int i = 0; i < num;) {
      TestHelper.setRandom(pnt, -radius, radius, QuickHull3DHelper.RANDOM);
      if (pnt.norm() <= radius) {
        coords[i * 3 + 0] = pnt.x;
        coords[i * 3 + 1] = pnt.y;
        coords[i * 3 + 2] = pnt.z;
        i++;
      }
    }
    return coords;
  }

  /** Returns the coordinates for <code>num</code> points whose x, y, and
   * z values are each randomly chosen to lie within a specified
   * range, and then clipped to a maximum absolute
   * value. This means a large number of points
   * may lie on the surface of cube, which is useful
   * for creating degenerate convex hull situations.
   *
   * @param num number of points to produce
   * @param range coordinate values will lie between -range and
   * range, before clipping
   * @param max maximum absolute value to which the coordinates
   * are clipped
   * @return array of coordinate values */
  public static double[] randomCubedPoints(int num, double range, double max) {
    double[] coords = new double[num * 3];
    for (int i = 0; i < num; i++) {
      for (int k = 0; k < 3; k++) {
        double x = 2 * range * (QuickHull3DHelper.RANDOM.nextDouble() - 0.5);
        if (x > max) {
          x = max;
        } else if (x < -max) {
          x = -max;
        }
        coords[i * 3 + k] = x;
      }
    }
    return coords;
  }

  static void shuffleCoords(double[] coords) {
    int num = coords.length / 3;
    for (int i = 0; i < num; i++) {
      int i1 = QuickHull3DHelper.RANDOM.nextInt(num);
      int i2 = QuickHull3DHelper.RANDOM.nextInt(num);
      for (int k = 0; k < 3; k++) {
        double tmp = coords[i1 * 3 + k];
        coords[i1 * 3 + k] = coords[i2 * 3 + k];
        coords[i2 * 3 + k] = tmp;
      }
    }
  }

  /** Returns randomly shuffled coordinates for points on a
   * three-dimensional grid, with a presecribed width between each point.
   *
   * @param gridSize number of points in each direction,
   * so that the total number of points produced is the cube of
   * gridSize.
   * @param width distance between each point along a particular
   * direction
   * @return array of coordinate values */
  public static double[] randomGridPoints(int gridSize, double width) {
    // gridSize gives the number of points across a given dimension
    // any given coordinate indexed by i has value
    // (i/(gridSize-1) - 0.5)*width
    int num = gridSize * gridSize * gridSize;
    double[] coords = new double[num * 3];
    int idx = 0;
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        for (int k = 0; k < gridSize; k++) {
          coords[idx * 3 + 0] = (i / (double) (gridSize - 1) - 0.5) * width;
          coords[idx * 3 + 1] = (j / (double) (gridSize - 1) - 0.5) * width;
          coords[idx * 3 + 2] = (k / (double) (gridSize - 1) - 0.5) * width;
          idx++;
        }
      }
    }
    shuffleCoords(coords);
    return coords;
  }

  static double[] addDegeneracy(int type, double[] coords, QuickHull3D hull) {
    int numv = coords.length / 3;
    int[][] faces = hull.getFaces();
    double[] coordsx = new double[coords.length + faces.length * 3];
    System.arraycopy(coords, 0, coordsx, 0, coords.length);
    double[] lam = new double[3];
    double eps = hull.getDistanceTolerance();
    for (int i = 0; i < faces.length; i++) {
      // random point on an edge
      lam[0] = QuickHull3DHelper.RANDOM.nextDouble();
      lam[1] = 1 - lam[0];
      lam[2] = 0.0;
      if (type == QuickHull3DHelper.VERTEX_DEGENERACY && (i % 2 == 0)) {
        lam[0] = 1.0;
        lam[1] = lam[2] = 0;
      }
      for (int j = 0; j < 3; j++) {
        int vtxi = faces[i][j];
        for (int k = 0; k < 3; k++) {
          coordsx[numv * 3 + k] += lam[j] * coords[vtxi * 3 + k] + QuickHull3DHelper.epsScale * eps * (QuickHull3DHelper.RANDOM.nextDouble() - 0.5);
        }
      }
      numv++;
    }
    shuffleCoords(coordsx);
    return coordsx;
  }

  static void rotateCoords(double[] res, double[] xyz, double roll, double pitch, double yaw) {
    double sroll = Math.sin(roll);
    double croll = Math.cos(roll);
    double spitch = Math.sin(pitch);
    double cpitch = Math.cos(pitch);
    double syaw = Math.sin(yaw);
    double cyaw = Math.cos(yaw);
    double m00 = croll * cpitch;
    double m10 = sroll * cpitch;
    double m20 = -spitch;
    double m01 = croll * spitch * syaw - sroll * cyaw;
    double m11 = sroll * spitch * syaw + croll * cyaw;
    double m21 = cpitch * syaw;
    double m02 = croll * spitch * cyaw + sroll * syaw;
    double m12 = sroll * spitch * cyaw - croll * syaw;
    double m22 = cpitch * cyaw;
    for (int i = 0; i < xyz.length - 2; i += 3) {
      res[i + 0] = m00 * xyz[i + 0] + m01 * xyz[i + 1] + m02 * xyz[i + 2];
      res[i + 1] = m10 * xyz[i + 0] + m11 * xyz[i + 1] + m12 * xyz[i + 2];
      res[i + 2] = m20 * xyz[i + 0] + m21 * xyz[i + 1] + m22 * xyz[i + 2];
    }
  }

  /** Sets the elements of this vector to uniformly distributed
   * random values in a specified range, using a supplied
   * random number generator.
   *
   * @param lower lower random value (inclusive)
   * @param upper upper random value (exclusive)
   * @param generator random number generator */
  public static void setRandom(Vector3d v, double lower, double upper, Random generator) {
    double range = upper - lower;
    v.x = generator.nextDouble() * range + lower;
    v.y = generator.nextDouble() * range + lower;
    v.z = generator.nextDouble() * range + lower;
  }
}
