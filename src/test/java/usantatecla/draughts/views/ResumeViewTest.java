package usantatecla.draughts.views;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import usantatecla.draughts.controllers.ResumeController;
import usantatecla.draughts.utils.YesNoDialog;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResumeViewTest extends SubViewTest {

    @Mock
    private YesNoDialog yesNoDialog;

    @Mock
    private ResumeController resumeController;

    @InjectMocks
    private ResumeView resumeView;

    @Test
    public void testResetGame() {

        when(this.yesNoDialog.read(anyString())).thenReturn(true);
        resumeView.interact(resumeController);
        verify(resumeController).reset();

    }

    @Test
    public void testFinishGame() {

        when(this.yesNoDialog.read(anyString())).thenReturn(false);
        resumeView.interact(resumeController);
        verify(resumeController).next();

    }

}
