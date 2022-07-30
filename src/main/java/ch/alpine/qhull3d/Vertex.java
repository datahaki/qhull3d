package ch.alpine.qhull3d;

/** Represents vertices of the hull, as well as the points from
 * which it is formed.
 *
 * @author John E. Lloyd, Fall 2004 */
class Vertex {
  /** Spatial point associated with this vertex. */
  final Vector3d pnt = new Vector3d();
  /** Back index into an array. */
  final int index;
  /** List forward link. */
  Vertex prev;
  /** List backward link. */
  Vertex next;
  /** Current face that this vertex is outside of. */
  Face face;
  boolean marked = false;

  public Vertex(int index) {
    this.index = index;
  }
}
