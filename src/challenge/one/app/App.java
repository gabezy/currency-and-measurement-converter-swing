package challenge.one.app;
import challenge.one.app.currencyconvertor.ConvertorCurrency;
import challenge.one.app.currencyconvertor.ResultPanel;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private String[] initOptions = {"Conversor de moeda", "Conversor de temperatura"};

    public App() {
        ResultPanel resultPanel = new ResultPanel();

        Object options = JOptionPane.showInputDialog(
                null, "Escolha uma opção:", "", JOptionPane.INFORMATION_MESSAGE,
                null, initOptions, "");
        if (options.toString() == initOptions[0]) {
            this.setTitle(initOptions[0]);
            ConvertorCurrency convertor = new ConvertorCurrency(resultPanel);
            convertor.setPreferredSize(new Dimension(100, 200));

            this.add(convertor, BorderLayout.NORTH);



        }
        this.add(resultPanel, BorderLayout.CENTER);


        this.setVisible(true);
        this.setSize(800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
