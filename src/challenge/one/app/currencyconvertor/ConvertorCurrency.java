package challenge.one.app.currencyconvertor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ConvertorCurrency extends JPanel implements ActionListener {
    String[] currenciesOptions = {"Real para Dólar", "Real para Euro", "Real para Libra", "Real para Yenes"};
    JComboBox comboBox;
    JTextField amountTextField;
    JButton convertButton;
    GridBagConstraints gbc = new GridBagConstraints();
    ResultPanel resultPanel;

    public ConvertorCurrency(ResultPanel resultPanel) {
        this.resultPanel = resultPanel;


        this.setLayout(new GridBagLayout());

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel convertLabel = new JLabel("Qual moeda deseja converter:");
        comboBox= new JComboBox(currenciesOptions);
        panel1.add(convertLabel);
        panel1.add(comboBox);

        //
        JLabel amountLabel = new JLabel("Insira o valor desejado:");
        amountTextField = new JTextField(20);
        //
        convertButton = new JButton("Converter");
        convertButton.addActionListener(this);
        panel2.add(amountLabel);
        panel2.add(amountTextField);
        panel2.add(convertButton);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.gridx = 0;
        this.add(panel1, gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;

        this.add(panel2, gbc);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == convertButton ) {
            int option = comboBox.getSelectedIndex();
            String valueString = amountTextField.getText();
            double valueDouble = convertStringToDouble(valueString);

            if (valueDouble > 0) {
                String result = calculateConvertValue(valueDouble, option);
                resultPanel.setResult(result);
            }
        }


    }

    private double convertStringToDouble(String valueAsString) {
        valueAsString = valueAsString.replace(",", ".");
        double amount = 0;
        try {
            amount = Double.parseDouble(valueAsString);
        } catch (Exception ex) {
            new MessageWindow("Preencha um valor válido", JOptionPane.ERROR_MESSAGE);
        } finally {
            return amount;
        }


    }

    private String calculateConvertValue(double value, int option) {
        double currentPrice;
        String stringPrice;
        double convertedValue;
        switch (option) {
            case 0:
                currentPrice = new APIValue("USD-BRL").getPrice();
                convertedValue = value  / currentPrice;
                stringPrice = new DecimalFormat("#.0#").format(convertedValue);
                break;
            case 1:
                currentPrice = new APIValue("EUR-BRL").getPrice();
                convertedValue = value  / currentPrice;
                stringPrice = new DecimalFormat("#.0#").format(convertedValue);
                break;
            case 2:
                currentPrice = new APIValue("GBP-BRL").getPrice();
                convertedValue = value  / currentPrice;
                stringPrice = new DecimalFormat("#.0#").format(convertedValue);
                break;
            case 3:
                currentPrice = new APIValue("JPY-BRL").getPrice();
                convertedValue = value / currentPrice;
                stringPrice = new DecimalFormat("#.0#").format(convertedValue);
                break;
            default:
                 convertedValue = 1000000;
                 stringPrice = String.valueOf(convertedValue);
                 break;
        }
        if (convertedValue < 1) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("0");
            stringBuffer.append(stringPrice);
            stringPrice = stringBuffer.toString();
        }
        String str = stringPrice.replace(".", ",");
        return  str;
    }

}
