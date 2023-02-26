package challenge.one.app.currencyconvertor;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultPanel extends JPanel implements ActionListener {
    private JTextField resultTextField;

    public ResultPanel() {
        JLabel label = new JLabel("valor convertido:");
        resultTextField = new JTextField(10);
        JButton copyButton = new JButton("Copiar");
        copyButton.addActionListener(this);

        resultTextField.setEnabled(false);

        this.add(label);
        this.add(resultTextField);
        this.add(copyButton);
    }

    public void setResult(String result) {
        resultTextField.setText(result);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String str = resultTextField.getText();
        StringSelection stringSelection = new StringSelection(str);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        new MessageWindow("Valor copiado para área de transferência", JOptionPane.INFORMATION_MESSAGE);
    }
}
