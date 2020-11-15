package usantatecla.draughts;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import usantatecla.draughts.controllers.AllControllerTests;
import usantatecla.draughts.models.AllModelTests;
import usantatecla.draughts.views.AllViewTests;

@RunWith(Suite.class)
@SuiteClasses({
        AllControllerTests.class,
        AllModelTests.class,
        AllViewTests.class
})
public class AllTests {
}
