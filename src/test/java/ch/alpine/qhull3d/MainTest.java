package ch.alpine.qhull3d;

import org.junit.jupiter.api.Test;

class MainTest {
  @Test
  void test() {
    QuickHull3DTest tester = new QuickHull3DTest();
    // for (int i = 0; i < args.length; i++) {
    // if (args[i].equals("-timing")) {
    // doTiming = true;
    // doTesting = false;
    // } else {
    // System.out.println("Usage: java quickhull3d.QuickHull3DTest [-timing]");
    // throw new RuntimeException();
    // }
    // }
    // if (doTesting) {
    tester.explicitAndRandomTests();
    // if (doTiming) {
    // tester.timingTests();
    // }
  }
}
