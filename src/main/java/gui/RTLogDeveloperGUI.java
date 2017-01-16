package gui;

/**
 * Created by david on 15/01/17.
 */


import dto.SearchResult;
import model.RTLogRecord;
import util.RTLogUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

public class RTLogDeveloperGUI extends JFrame {

    private final RTLogUtil util;
    private JTextArea output;
    private JTextArea input;
    private JTextArea searchInput;
    private JTextArea diffInput;


    public static void main(String[] args) {
        new RTLogDeveloperGUI(args);
    }

    public RTLogDeveloperGUI() {
        this.setVisible(true);
        this.util = new RTLogUtil();
        initUI();
    }

    public RTLogDeveloperGUI(String[] args) {
        this();
    }

    private void initUI() {

        this.setResizable(false);





        JPanel upperPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        this.getContentPane().add(upperPanel, "North");
        this.getContentPane().add(searchPanel, "Center");
        this.getContentPane().add(lowerPanel, "South");

        try {
            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource("rtlog_dev.png"));
            JLabel picLabel = new JLabel(new ImageIcon(image));
            picLabel.setBorder (new TitledBorder(new EtchedBorder(), "version 0.1.0"));
            upperPanel.add(picLabel);
            upperPanel.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.input = new JTextArea(SampleData.example1, 30, 35);
        this.input.setBorder(new TitledBorder(new EtchedBorder(), "Primary input field"));
        this.searchInput = new JTextArea("Search field...", 1, 15);

        this.diffInput = new JTextArea(SampleData.example2, 30, 35);
        this.diffInput.setBorder (new TitledBorder(new EtchedBorder(), "Secondary input field"));
        this.output = new JTextArea("", 30, 70);
        this.output.setBorder(new TitledBorder(new EtchedBorder(), "Output"));


        createTextArea(lowerPanel, input);
        createTextArea(lowerPanel, diffInput);
        createTextArea(lowerPanel, output);


        addAvailableRecordsButton(searchPanel);
        addAvailableFieldsButton(searchPanel);
        addSwitchButton(searchPanel);
        addPrintButton(searchPanel);
        addDiffButton(searchPanel);
        addFindContentButton(searchPanel);
        addFieldsButton(searchPanel);
        createTextArea(searchPanel, searchInput);
        addCleanButton(searchPanel);
        addHelpButton(searchPanel);

        this.pack();
        this.setVisible(true);



    }

    private void addFontButton(JPanel panel) {
        JButton button = new JButton("Change Font");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
                diffInput.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
                output.setFont(new Font("Comic Sans MS", Font.BOLD, 13));
            }
        });
        panel.add(button);
    }

    private void addSwitchButton(JPanel panel) {
        JButton button = new JButton("Switch input fields");
        //button.setFont(new Font("Comic", Font.BOLD, 13));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = input.getText();
                input.setText(diffInput.getText());
                diffInput.setText(tmp);
            }
        });
        panel.add(button);
    }

    private void addHelpButton(JPanel panel) {
        JButton button = new JButton("Help");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText(Help.text);
            }
        });
        panel.add(button);
    }

    private void addCleanButton(JPanel panel) {
        JButton button = new JButton("Clear");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diffInput.setText("");
                input.setText("");
                searchInput.setText("");
                output.setText("");
            }
        });
        panel.add(button);
    }

    private void addAvailableRecordsButton(JPanel panel) {
        JButton button = new JButton("Supported Records");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText(util.toJson(util.supportedRecordTypes()));
            }
        });
        panel.add(button);
    }

    private void addAvailableFieldsButton(JPanel panel) {
        JButton button = new JButton("List fields for Record");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText(util.toJson(util.getFields(searchInput.getText())));
            }
        });
        panel.add(button);
    }

    private void addDiffButton(JPanel panel) {
        JButton button = new JButton("Diff");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText(util.toJson(
                        util.calculateDiff(
                                util.parseString(input.getText()),
                                util.parseString(diffInput.getText()))));
            }
        });
        panel.add(button);
    }


    private void addFindContentButton(JPanel panel) {
        JButton button = new JButton("Find Content");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<RTLogRecord> records = util.parseString(input.getText());
                output.setText(util.toJson(util.findContent(searchInput.getText(), records)));
            }
        });
        panel.add(button);
    }

    private void createTextArea(JPanel lowerPanel, JTextArea ta) {
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        lowerPanel.add(new JScrollPane(ta));
    }

    private void addPrintButton(JPanel upperPanel) {
        JButton button = new JButton("Print Json");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<RTLogRecord> records = util.parseString(input.getText());
                output.setText(util.toJson(records));
            }
        });
        upperPanel.add(button);
    }

    private void addFieldsButton(JPanel upperPanel) {
        JButton button = new JButton("Find field");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<RTLogRecord> records = util.parseString(input.getText());
                List<SearchResult> field = util.findField(searchInput.getText(), records);

                output.setText(util.toJson(field));
            }
        });
        upperPanel.add(button);
    }


}