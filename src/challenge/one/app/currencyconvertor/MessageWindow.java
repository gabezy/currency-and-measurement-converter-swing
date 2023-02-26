package challenge.one.app.currencyconvertor;

import javax.swing.*;

public class MessageWindow {

    MessageWindow(String message, int type) {
        JOptionPane.showMessageDialog(null, message, "", type);
    }
}
