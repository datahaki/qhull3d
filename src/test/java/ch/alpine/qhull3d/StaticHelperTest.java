package ch.alpine.qhull3d;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StaticHelperTest {
  @Test
  void test() {
    double nextUp = Math.nextUp(1.0) - 1;
    assertEquals(StaticHelper.DOUBLE_PREC, nextUp);
  }
}
