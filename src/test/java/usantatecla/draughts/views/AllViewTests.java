package usantatecla.draughts.views;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        GameViewTest.class,
        PlayViewTest.class,
        ResumeViewTest.class,
        StartViewTest.class,
        ViewTest.class
})
public class AllViewTests {
}
