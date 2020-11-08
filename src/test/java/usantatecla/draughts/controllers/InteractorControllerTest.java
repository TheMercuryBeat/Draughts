package usantatecla.draughts.controllers;

import org.junit.Before;
import org.mockito.Mockito;

public abstract class InteractorControllerTest extends ControllerTest {

    protected InteractorControllersVisitor interactorControllersVisitor = Mockito.mock(InteractorControllersVisitor.class);

    @Before
    public void beforeInteractorController() {
        Mockito.reset(interactorControllersVisitor);
    }

}
