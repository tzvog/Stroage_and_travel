import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * test suits class to run our tests
 */

@RunWith(Suite.class)

@Suite.SuiteClasses({
    LockerTest.class,
    LongTermTest.class
})

public class SpaceshipDepositoryTest {
}
