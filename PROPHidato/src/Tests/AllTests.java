package Tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({SquareBoardTest.class, TriangleBoardTest.class, HexagonBoardTest.class})
public class AllTests {
	public static void main(String[] args) throws Exception {
		JUnitCore.main();
	}
}
