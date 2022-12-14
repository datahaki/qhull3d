/** Copyright John E. Lloyd, 2004. All rights reserved. Permission to use,
 * copy, modify and redistribute is granted, provided that this copyright
 * notice is retained and the author is given credit whenever appropriate.
 *
 * This software is distributed "as is", without any warranty, including
 * any implied warranty of merchantability or fitness for a particular
 * use. The author assumes no responsibility for, and shall not be liable
 * for, any special, indirect, or consequential damages, or any damages
 * whatsoever, arising out of or in connection with the use of this
 * software. */
package ch.alpine.qhull3d;

/** A three-element vector. This class is actually a reduced version of the
 * Vector3d class contained in the author's matlib package (which was partly
 * inspired by javax.vecmath). Only a minimal number of methods
 * which are relevant to convex hull generation are supplied here.
 *
 * @author John E. Lloyd, Fall 2004 */
class Vector3d {
  /** First element */
  public double x;
  /** Second element */
  public double y;
  /** Third element */
  public double z;

  /** Gets a single element of this vector.
   * Elements 0, 1, and 2 correspond to x, y, and z.
   *
   * @param i element index
   * @return element value throws ArrayIndexOutOfBoundsException
   * if i is not in the range 0 to 2. */
  public double get(int i) {
    return switch (i) {
    case 0 -> x;
    case 1 -> y;
    case 2 -> z;
    default -> throw new ArrayIndexOutOfBoundsException(i);
    };
  }

  /** Sets the values of this vector to those of v1.
   *
   * @param v1 vector whose values are copied */
  public void set(Vector3d v1) {
    x = v1.x;
    y = v1.y;
    z = v1.z;
  }

  /** Adds this vector to v1 and places the result in this vector.
   *
   * @param v1 right-hand vector */
  public void add(Vector3d v1) {
    x += v1.x;
    y += v1.y;
    z += v1.z;
  }

  /** Subtracts vector v1 from v2 and places the result in this vector.
   *
   * @param v1 left-hand vector
   * @param v2 right-hand vector */
  public void sub(Vector3d v1, Vector3d v2) {
    x = v1.x - v2.x;
    y = v1.y - v2.y;
    z = v1.z - v2.z;
  }

  /** Subtracts v1 from this vector and places the result in this vector.
   *
   * @param v1 right-hand vector */
  public void sub(Vector3d v1) {
    x -= v1.x;
    y -= v1.y;
    z -= v1.z;
  }

  /** Scales the elements of this vector by <code>s</code>.
   *
   * @param s scaling factor */
  public void scale(double s) {
    x *= s;
    y *= s;
    z *= s;
  }

  /** Scales the elements of vector v1 by <code>s</code> and places
   * the results in this vector.
   *
   * @param s scaling factor
   * @param v1 vector to be scaled */
  public void scale(double s, Vector3d v1) {
    x = s * v1.x;
    y = s * v1.y;
    z = s * v1.z;
  }

  /** Returns the 2 norm of this vector. This is the square root of the
   * sum of the squares of the elements.
   *
   * @return vector 2 norm */
  public double norm() {
    return Math.sqrt(x * x + y * y + z * z);
  }

  /** Returns the square of the 2 norm of this vector. This
   * is the sum of the squares of the elements.
   *
   * @return square of the 2 norm */
  public double normSquared() {
    return x * x + y * y + z * z;
  }

  /** Returns the dot product of this vector and v1.
   *
   * @param v1 right-hand vector
   * @return dot product */
  public double dot(Vector3d v1) {
    return x * v1.x + y * v1.y + z * v1.z;
  }

  /** Normalizes this vector in place. */
  public void normalize() {
    double lenSqr = x * x + y * y + z * z;
    double err = lenSqr - 1;
    if (err > (2 * StaticHelper.DOUBLE_PREC) || err < -(2 * StaticHelper.DOUBLE_PREC)) {
      double len = Math.sqrt(lenSqr);
      x /= len;
      y /= len;
      z /= len;
    }
  }

  /** Sets the elements of this vector to zero. */
  public void setZero() {
    x = 0;
    y = 0;
    z = 0;
  }

  /** Sets the elements of this vector to the prescribed values.
   * 
   * @param x value for first element
   * @param y value for second element
   * @param z value for third element */
  public void set(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /** Computes the cross product of v1 and v2 and places the result
   * in this vector.
   *
   * @param v1 left-hand vector
   * @param v2 right-hand vector */
  public void cross(Vector3d v1, Vector3d v2) {
    double tmpx = v1.y * v2.z - v1.z * v2.y;
    double tmpy = v1.z * v2.x - v1.x * v2.z;
    double tmpz = v1.x * v2.y - v1.y * v2.x;
    x = tmpx;
    y = tmpy;
    z = tmpz;
  }

  /** Returns a string representation of this vector, consisting
   * of the x, y, and z coordinates.
   *
   * @return string representation */
  @Override
  public String toString() {
    return x + " " + y + " " + z;
  }
}
