package ui;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class PreciseJSpinner extends JSpinner {
    
    public PreciseJSpinner(SpinnerNumberModel spinnerNumberModel) {
        super(spinnerNumberModel);
    }

    /*
     * Code modified from
     * http://stackoverflow.com/a/8634459
     */
    @Override
    protected JComponent createEditor( SpinnerModel model ) {
      return new NumberEditor(this, "0.000000");//needed decimal format
    }
}
