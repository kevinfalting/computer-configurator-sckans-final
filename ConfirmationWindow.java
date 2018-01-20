import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

public class ConfirmationWindow extends JFrame {

    String orderSummary = "";
    int price;

    private JPanel westPanel, eastPanel, southPanel;

    private JTextArea orderSummaryTA;

    private JLabel priceLabel, taxLabel, shippingLabel, totalLabel;

    private JTextField priceTF, taxTF, shippingTF, totalTF;

    private JButton cancelButton, confirmButton;

    private final double TAX_RATE = 0.078, SHIPPING_RATE = 0.025;

    private ConfiguratorWindow window;

    public ConfirmationWindow(String orderSummary, int price, ConfiguratorWindow window) {

        this.orderSummary = orderSummary;
        this.price = price;
        this.window = window;

        // set the title
        setTitle("Confirmation Window");

        setLayout(new BorderLayout());

        // set what happens when you hit the exit button
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // create panels
        buildWestPanel();
        buildEastPanel();
        buildSouthPanel();

        // add panels
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);

        // pack and display the window
        pack();
        setVisible(true);
    }

    private void buildWestPanel() {
        westPanel = new JPanel();
        orderSummaryTA = new JTextArea(20, 30);
        orderSummaryTA.setText(orderSummary);
        orderSummaryTA.setEditable(false);
        westPanel.add(orderSummaryTA);
    }

    private void buildEastPanel() {
        eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
        priceLabel = new JLabel("Price");
        taxLabel = new JLabel("Tax");
        shippingLabel = new JLabel("Shipping and Handling");
        totalLabel = new JLabel("Total");
        priceTF = new JTextField(10);
        taxTF = new JTextField(10);
        shippingTF = new JTextField(10);
        totalTF = new JTextField(10);
        priceTF.setEditable(false);
        taxTF.setEditable(false);
        shippingTF.setEditable(false);
        totalTF.setEditable(false);
        priceTF.setText("$" + price + ".00");
        String taxFormatted = String.format("$%.2f", (price * TAX_RATE));
        taxTF.setText(taxFormatted);
        String shippingFormatted = String.format("$%.2f", (price * SHIPPING_RATE));
        shippingTF.setText(shippingFormatted);
        String totalFormatted = String.format("$%.2f", (price * (1 + TAX_RATE + SHIPPING_RATE)));
        totalTF.setText(totalFormatted);
        eastPanel.add(priceLabel);
        eastPanel.add(priceTF);
        eastPanel.add(taxLabel);
        eastPanel.add(taxTF);
        eastPanel.add(shippingLabel);
        eastPanel.add(shippingTF);
        eastPanel.add(totalLabel);
        eastPanel.add(totalTF);
    }

    private void buildSouthPanel() {
        southPanel = new JPanel();
        cancelButton = new JButton("Cancel");
        confirmButton = new JButton("Confirm");
        cancelButton.addActionListener(new ButtonActionListener());
        confirmButton.addActionListener(new ButtonActionListener());
        southPanel.add(cancelButton);
        southPanel.add(confirmButton);
    }

    private class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cancelButton) {
                ConfirmationWindow.this.dispose();
            } else {
                window.setStatus("Processed!");
                ConfirmationWindow.this.dispose();
            }
        }
    }
}
