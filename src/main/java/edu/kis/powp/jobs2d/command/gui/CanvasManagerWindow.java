package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;
import edu.kis.powp.jobs2d.features.CanvasFeature;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class CanvasManagerWindow extends JFrame implements WindowComponent {
    private HashMap<String, Dimension> paperFormatMap;

    private JPanel panel = new JPanel();
    private GridLayout layout = new GridLayout(0, 2, 10, 10);

    private JLabel textWidth = new JLabel("Width");
    private JLabel textHeight = new JLabel("Height");
    private JLabel textOriginX = new JLabel("Origin X");
    private JLabel textOriginY = new JLabel("Origin Y");
    private JLabel textPaperSize = new JLabel("Paper Size");
    private JLabel textOrientation = new JLabel("Orientation");

    private JTextField fieldWidth = new JTextField(Double.toString(CanvasFeature.getWidth()));
    private JTextField fieldHeight = new JTextField(Double.toString(CanvasFeature.getHeight()));
    private JTextField fieldOriginX = new JTextField(Double.toString(CanvasFeature.getOriginX()));
    private JTextField fieldOriginY = new JTextField(Double.toString(CanvasFeature.getOriginY()));

    private JButton applyPaperSize = new JButton("Apply");
    private JButton btnSwapDimensions = new JButton("Swap Dimensions");
    private JButton applyCustomSize = new JButton("Apply");

    private static int BORDER = 10;

    JComboBox<Object> comboPaperSize;

    public CanvasManagerWindow() {
        initPaperFormatMap();

        setTitle("Canvas Manager");
        setMinimumSize(new Dimension(360, 420));
        setLocationRelativeTo(null);

        panel.setLayout(layout);
        panel.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
        add(panel);

        comboPaperSize = new JComboBox<>(paperFormatMap.keySet().toArray());

        JComboBox<Object> orientationComboBox = new JComboBox<>(new String[]{"Vertical", "Horizontal"});

        panel.add(textWidth);
        panel.add(fieldWidth);

        panel.add(textHeight);
        panel.add(fieldHeight);

        panel.add(textOriginX);
        panel.add(fieldOriginX);

        panel.add(textOriginY);
        panel.add(fieldOriginY);

        panel.add(applyCustomSize);
        panel.add(btnSwapDimensions);

        panel.add(new JPanel());
        panel.add(new JPanel());

        panel.add(textPaperSize);
        panel.add(comboPaperSize);
        panel.add(textOrientation);
        panel.add(orientationComboBox);
        panel.add(applyPaperSize);

        pack();

        applyCustomSize.addActionListener(e -> {
            try {
                CanvasFeature.setWidth(Double.parseDouble(fieldWidth.getText().replace(',', '.')));
                CanvasFeature.setHeight(Double.parseDouble(fieldHeight.getText().replace(',', '.')));
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });

        btnSwapDimensions.addActionListener(e -> {
            String temp = fieldWidth.getText();
            fieldWidth.setText(fieldHeight.getText());
            fieldHeight.setText(temp);
        });

        applyPaperSize.addActionListener(e -> {
            Dimension dimension = paperFormatMap.get((String) comboPaperSize.getSelectedItem());
            if(Objects.equals(orientationComboBox.getSelectedItem(), "Vertical")) {
                CanvasFeature.setWidth(dimension.width);
                CanvasFeature.setHeight(dimension.height);
            } else {
                CanvasFeature.setWidth(dimension.height);
                CanvasFeature.setHeight(dimension.width);
            }
        });
    }

    private void initPaperFormatMap() {
        paperFormatMap = new HashMap<>();
        paperFormatMap.put("A1", new Dimension(594, 841));
        paperFormatMap.put("A2", new Dimension(420, 594));
        paperFormatMap.put("A3", new Dimension(297, 420));
        paperFormatMap.put("A4", new Dimension(210, 297));
        paperFormatMap.put("A5", new Dimension(148, 210));
        paperFormatMap.put("A6", new Dimension(105, 148));
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }
}
