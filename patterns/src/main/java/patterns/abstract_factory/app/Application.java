package patterns.abstract_factory.app;

import patterns.abstract_factory.buttons.Button;
import patterns.abstract_factory.checkboxes.Checkbox;
import patterns.abstract_factory.factories.GUIFactory;

/**
 * Factory users don't care which concrete factory they use since they work with
 * factories and products through abstract interfaces.
 */
public class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}
