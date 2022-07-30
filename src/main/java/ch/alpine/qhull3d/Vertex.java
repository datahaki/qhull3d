package ch.alpine.qhull3d;

/** Represents vertices of the hull, as well as the points from
 * which it is formed.
 *
 * @author John E. Lloyd, Fall 2004 */
class Vertex {
  /** Spatial point associated with this vertex. */
  final Vector3d pnt;
  /** Back index into an array. */
  int index;
  /** List forward link. */
  Vertex prev;
  /** List backward link. */
  Vertex next;
  /** Current face that this vertex is outside of. */
  Face face;

  /** Constructs a vertex and sets its coordinates to 0. */
  public Vertex() {
    pnt = new Vector3d();
  }
}
