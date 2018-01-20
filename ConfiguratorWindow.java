import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

public class ConfiguratorWindow extends JFrame {

    // variables
    private int orderTotal;
    private String orderSummaryText = "";

    // panels
    private JPanel northPanel, southPanel, eastPanel, westPanel, centerPanel;

    // images
    ImageIcon image1, image2;

    // radio buttons
    private JRadioButton intel, amd, os1, os2, os3;

    // check boxes
    private JCheckBox app1, app2, app3, app4;

    // Buttons
    private JButton orderButton, clearButton;

    // text fields
    private JTextArea orderSummary;
    private JTextField total, status;

    // combo boxes
    private JComboBox processors, memory, hdd, optical, graphics;

    // labels
    private JLabel osLabel, appLabel, statusLabel, totalLabel, westPanelLabel;

    // data
    private String[]
            intelProcessorsList = { "Processor",
                                    "G1620 2.70GHz",
                                    "G1630 2.80GHz",
                                    "G1820 2.70GHz",
                                    "G1830 2.80GHz"},
            amdProcessorsList = {   "Processor",
                                    "FX-8350",
                                    "FX-9590",
                                    "FX-4100",
                                    "FX-4300"},
            memoryList = {  "Memory",
                            "6 GB",
                            "8 GB",
                            "12 GB",
                            "16 GB"},
            hddList = { "Hard Drive",
                        "250 GB",
                        "500 GB",
                        "1 TB"},
            opticalList = { "Optical Drive",
                            "DVD Drive",
                            "Combo DVD/CDRW",
                            "DVD and CDRW"},
            graphicsList = {"Graphics Card",
                            "NVIDA GeForce G310 512MB DDR3",
                            "NVIDA GeForce GT620 1GB DDR3",
                            "NVIDA GeForce GT640 1GB GDDR5"};


    // constructor

    public ConfiguratorWindow() {

        // set the title
        setTitle("Configurator");

        // set what happens when you hit the exit button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set layout
        setLayout(new BorderLayout());

        // Create Panels
        buildNorthPanel();
        buildSouthPanel();
        buildEastPanel();
        buildWestPanel();
        buildCenterPanel();

        // Add panels
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
        add(eastPanel, BorderLayout.EAST);
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);


        // pack and display the window
        pack();
        setVisible(true);

        // trigger startup event
        intel.doClick();
    }

    public void setStatus(String status) {
        this.status.setText(status);
    }

    private void buildNorthPanel() {
        // holds amd vs intel radio selector

        northPanel = new JPanel();

        intel = new JRadioButton("Intel", true);
        amd = new JRadioButton("AMD");

        // create a button group
        ButtonGroup group = new ButtonGroup();
        group.add(intel);
        group.add(amd);

        // add running total action listener
        intel.addActionListener(new RunningTotalListener());
        amd.addActionListener(new RunningTotalListener());

        // add buttons to panel
        northPanel.add(intel);
        northPanel.add(amd);

        // styling
        northPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void buildSouthPanel() {
        // holds text area for results and order summary

        southPanel = new JPanel();

        orderSummary = new JTextArea(20, 60);
        orderSummary.setLineWrap(true);
        orderSummary.setEditable(false);
        southPanel.add(orderSummary);
    }

    private void buildWestPanel() {
        // holds combo boxes for different selectors
        westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));

        westPanelLabel = new JLabel("Additional Hardware Options");

        // create combo boxes
        // intel is selected by default
        processors = new JComboBox(intelProcessorsList);
        memory = new JComboBox(memoryList);
        hdd = new JComboBox(hddList);
        optical = new JComboBox(opticalList);
        graphics = new JComboBox(graphicsList);

        // add running total action listener
        processors.addActionListener(new RunningTotalListener());
        memory.addActionListener(new RunningTotalListener());
        hdd.addActionListener(new RunningTotalListener());
        optical.addActionListener(new RunningTotalListener());
        graphics.addActionListener(new RunningTotalListener());

        // add components to panel
        westPanel.add(westPanelLabel);
        westPanel.add(processors);
        westPanel.add(memory);
        westPanel.add(hdd);
        westPanel.add(optical);
        westPanel.add(graphics);

        // styling
        westPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void buildCenterPanel() {
        // holds os and application pack selector

        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));

        // create labels
        osLabel = new JLabel("Operating System");
        appLabel = new JLabel("Application Packages");

        // build OS radio selectors
        os1 = new JRadioButton("Windows 8.1", true);
        os2 = new JRadioButton("Windows 8.1 Pro");
        os3 = new JRadioButton("Linux");

        // make radio buttons a group
        ButtonGroup group = new ButtonGroup();
        group.add(os1);
        group.add(os2);
        group.add(os3);

        // build application packs check boxes
        app1 = new JCheckBox("Microsoft Office Home and Student 2013");
        app2 = new JCheckBox("Microsoft Office Home & Business 2013");
        app3 = new JCheckBox("Accounting package");
        app4 = new JCheckBox("Graphics Package");

        // add running total action listener
        os1.addActionListener(new RunningTotalListener());
        os2.addActionListener(new RunningTotalListener());
        os3.addActionListener(new RunningTotalListener());
        app1.addActionListener(new RunningTotalListener());
        app2.addActionListener(new RunningTotalListener());
        app3.addActionListener(new RunningTotalListener());
        app4.addActionListener(new RunningTotalListener());

        // add components to panel
        centerPanel.add(osLabel);
        centerPanel.add(os1);
        centerPanel.add(os2);
        centerPanel.add(os3);
        centerPanel.add(new JSeparator());
        centerPanel.add(Box.createRigidArea(new Dimension(1,5)));
        centerPanel.add(appLabel);
        centerPanel.add(app1);
        centerPanel.add(app2);
        centerPanel.add(app3);
        centerPanel.add(app4);

        // styling
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }

    private void buildEastPanel() {
        // holds status, total, and buttons

        eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));

        statusLabel = new JLabel("Order Status");
        totalLabel = new JLabel("Total");

        status = new JTextField("Waiting", 10);
        total = new JTextField("", 10);
        status.setEditable(false);
        total.setEditable(false);

        orderButton = new JButton("Order");
        clearButton = new JButton("Clear");

        // add button action listener
        orderButton.addActionListener(new ButtonActionListener());
        clearButton.addActionListener(new ButtonActionListener());

        eastPanel.add(statusLabel);
        eastPanel.add(status);
        eastPanel.add(new JSeparator());
        eastPanel.add(Box.createRigidArea(new Dimension(1,5)));
        eastPanel.add(totalLabel);
        eastPanel.add(total);
        eastPanel.add(new JSeparator());
        eastPanel.add(Box.createRigidArea(new Dimension(1,5)));
        eastPanel.add(orderButton);
        eastPanel.add(clearButton);

        // styling
        eastPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private class RunningTotalListener implements ActionListener, ItemListener {

        public void actionPerformed(ActionEvent e) {
            updateProcessorComboBox();
            updateTotal();
        }

        public void itemStateChanged(ItemEvent e) {
            updateTotal();
        }

        private void updateProcessorComboBox() {
            int index = processors.getSelectedIndex();
            processors.removeAllItems();
            if (amd.isSelected()) {
                processors.addItem(amdProcessorsList[0]);
                processors.addItem(amdProcessorsList[1]);
                processors.addItem(amdProcessorsList[2]);
                processors.addItem(amdProcessorsList[3]);
            } else if (intel.isSelected()) {
                processors.addItem(intelProcessorsList[0]);
                processors.addItem(intelProcessorsList[1]);
                processors.addItem(intelProcessorsList[2]);
                processors.addItem(intelProcessorsList[3]);
            }
            processors.setSelectedIndex(index);
        }

        private void updateTotal() {
            int index;
            orderSummaryText = "ORDER SUMMARY";

            // start with the intel vs amd
            if (amd.isSelected()) {
                orderTotal = 599;
                orderSummaryText += "\nBrand: AMD";
                // processor
                index = processors.getSelectedIndex();
                switch (index) {
                    case 0:
                        orderSummaryText += "\nProcessor: FX-2100"; break;
                    case 1:
                        orderTotal += 50; orderSummaryText += "\nProcessor: FX-8350"; break;
                    case 2:
                        orderTotal += 90; orderSummaryText += "\nProcessor: FX-9590"; break;
                    case 3:
                        orderTotal += 105; orderSummaryText += "\nProcessor: FX-4100"; break;
                    case 4:
                        orderTotal += 130; orderSummaryText += "\nProcessor: FX-4300"; break;
                }
            }
            if (intel.isSelected()) {
                orderTotal = 499;
                orderSummaryText += "\nBrand: Intel";
                // processor
                index = processors.getSelectedIndex();
                switch (index) {
                    case 0:
                        orderSummaryText += "\nProcessor: Intel® Celeron® G1610"; break;
                    case 1:
                        orderTotal += 25; orderSummaryText += "\nProcessor: G1620 2.70GHz"; break;
                    case 2:
                        orderTotal += 90; orderSummaryText += "\nProcessor: G1630 2.80GHz"; break;
                    case 3:
                        orderTotal += 187; orderSummaryText += "\nProcessor: G1820 2.70GHz"; break;
                    case 4:
                        orderTotal += 280; orderSummaryText += "\nProcessor: G1830 2.80GHz"; break;
                }
            }

            // memory
            index = memory.getSelectedIndex();
            switch (index) {
                case 0:
                    if (intel.isSelected()) {
                        orderSummaryText += "\nMemory: 4GB Dual Channel DDR3 1600MHz - 1 DIMMs";
                    } else {
                        orderSummaryText += "\nMemory: 4GB";
                    }
                    break;
                case 1:
                    orderTotal += 28; orderSummaryText += "\nMemory: 6GB"; break;
                case 2:
                    orderTotal += 58; orderSummaryText += "\nMemory: 8GB"; break;
                case 3:
                    orderTotal += 108; orderSummaryText += "\nMemory: 12GB"; break;
                case 4:
                    orderTotal += 176; orderSummaryText += "\nMemory: 16GB"; break;
            }

            // hdd
            index = hdd.getSelectedIndex();
            switch (index) {
                case 0:
                    if (intel.isSelected()) {
                        orderSummaryText += "\nHard Drive: 125GB 7200RPM SATA 3.0Gb/s";
                    } else {
                        orderSummaryText += "\nHard Drive: 125GB";
                    }
                    break;
                case 1:
                    orderTotal += 27; orderSummaryText += "\nHard Drive: 250GB"; break;
                case 2:
                    orderTotal += 50; orderSummaryText += "\nHard Drive: 500GB"; break;
                case 3:
                    orderTotal += 89; orderSummaryText += "\nHard Drive: 1TB"; break;
            }

            // optical
            index = optical.getSelectedIndex();
            switch (index) {
                case 0:
                    orderSummaryText += "\nOptical Drive: CD-Rom Drive";
                    break;
                case 1:
                    orderTotal += 17; orderSummaryText += "\nOptical Drive: DVD Drive"; break;
                case 2:
                    orderTotal += 40; orderSummaryText += "\nOptical Drive: Combo DVD/CDRW"; break;
                case 3:
                    orderTotal += 79; orderSummaryText += "\nOptical Drive: DVD and CDRW"; break;
            }

            // graphics
            index = graphics.getSelectedIndex();
            switch (index) {
                case 0:
                    if (amd.isSelected()) {
                        orderSummaryText += "\nGraphics: Integrated 3D Graphics";
                    }
                    break;
                case 1:
                    orderTotal += 80; orderSummaryText += "\nGraphics: NVIDA GeForce G310 512MB DDR3"; break;
                case 2:
                    orderTotal += 169; orderSummaryText += "\nGraphics: NVIDA GeForce GT620 1GB DDR3"; break;
                case 3:
                    orderTotal += 490; orderSummaryText += "\nGraphics: NVIDA GeForce GT640 1GB GDDR5"; break;
            }

            // operating system
            if (os1.isSelected()) {
                orderSummaryText += "\nOperating System: Microsoft Windows 8.1";
            }
            if (os2.isSelected()) {
                orderTotal += 59;
                orderSummaryText += "\nOperating System: Microsoft Windows 8.1 Pro";
            }
            if (os3.isSelected()) {
                orderTotal -= 89;
                orderSummaryText += "\nOperating System: Linux";
            }

            // miscellaneous items part of every base package
            orderSummaryText += "\nAudio: Integrated Audio\nSpeakers: 2 Piece Powered Speaker Set\nKeyboard: USB Wired Entry Keyboard\nMouse: USB Optical Mouse";

            // application packages
            if (app1.isSelected() || app2.isSelected() || app3.isSelected() || app4.isSelected()) {
                orderSummaryText += "\nApplication Package(s):";
            }
            if (app1.isSelected()) {
                orderTotal += 139;
                orderSummaryText += "\n\tMicrosoft Office Home and Student 2013";
            }
            if (app2.isSelected()) {
                orderTotal += 219;
                orderSummaryText += "\n\tMicrosoft Office Home & Business 2013";
            }
            if (app3.isSelected()) {
                orderTotal += 399;
                orderSummaryText += "\n\tAccounting package";
            }
            if (app4.isSelected()) {
                orderTotal += 499;
                orderSummaryText += "\n\tGraphics package";
            }

            // update screen
            status.setText("Waiting");
            total.setText("$" + orderTotal);
            orderSummary.setText(orderSummaryText);
        }
    }

    private class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == clearButton) {
                clear();
            } else {
                new ConfirmationWindow(orderSummaryText, orderTotal, ConfiguratorWindow.this);
            }
        }

        private void clear() {
            intel.doClick();
            os1.doClick();
            app1.setSelected(false);
            app2.setSelected(false);
            app3.setSelected(false);
            app4.setSelected(false);
            processors.setSelectedIndex(0);
            memory.setSelectedIndex(0);
            hdd.setSelectedIndex(0);
            optical.setSelectedIndex(0);
            graphics.setSelectedIndex(0);
            status.setText("Waiting");
        }
    }
}
