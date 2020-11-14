package usantatecla.draughts.views;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import usantatecla.draughts.utils.Console;

public class SubViewTest {

    @Mock
    protected Console console;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

}
