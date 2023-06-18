package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.appbase.gui.WindowComponent;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class CanvasManagerWindow extends JFrame implements WindowComponent {
    private float width;
    private float height;
    private HashMap<String, Dimension> paperFormatMap;

    public CanvasManagerWindow() {
        initPaperFormatMap();

        setTitle("Canvas Manager");
        setSize(300, 340);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 1));
        add(panel);

        JLabel textWidth = new JLabel("width");
        JTextField widthField = new JTextField(10);
        JLabel textHeight = new JLabel("height");
        JTextField heightField = new JTextField(10);
        JButton swapWidthHeight = new JButton("swap");
        JButton applyWidthHeight = new JButton("apply");

        JLabel textPaperSize = new JLabel("paper size");
        JComboBox<Object> paperSizeComboBox = new JComboBox<>(paperFormatMap.keySet().toArray());
        JButton applyPaperSize = new JButton("apply");

        JLabel textOrientation = new JLabel("orientation");
        JComboBox<Object> orientationComboBox = new JComboBox<>(new String[]{"vertical", "horizontal"});

        panel.add(textWidth);
        panel.add(widthField);
        panel.add(textHeight);
        panel.add(heightField);
        panel.add(swapWidthHeight);
        panel.add(applyWidthHeight);

        panel.add(new JLabel());

        panel.add(textPaperSize);
        panel.add(paperSizeComboBox);
        panel.add(textOrientation);
        panel.add(orientationComboBox);
        panel.add(applyPaperSize);

        applyWidthHeight.addActionListener(e -> {
            try {
                width = Float.parseFloat(widthField.getText().replace(',', '.'));
                height = Float.parseFloat(heightField.getText().replace(',', '.'));
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        });

        swapWidthHeight.addActionListener(e -> {
            String temp = widthField.getText();
            widthField.setText(heightField.getText());
            heightField.setText(temp);
        });

        applyPaperSize.addActionListener(e -> {
            Dimension dimension = paperFormatMap.get((String)paperSizeComboBox.getSelectedItem());
            if(Objects.equals(orientationComboBox.getSelectedItem(), "vertical")) {
                width = dimension.width;
                height = dimension.height;
            } else {
                height = dimension.width;
                width = dimension.height;
            }
        });
    }

    private void initPaperFormatMap() {
        paperFormatMap = new HashMap<>();
        paperFormatMap.put("A6", new Dimension(105, 148));
        paperFormatMap.put("A5", new Dimension(148, 210));
        paperFormatMap.put("A4", new Dimension(210, 297));
        paperFormatMap.put("A3", new Dimension(297, 420));
        paperFormatMap.put("A2", new Dimension(420, 594));
        paperFormatMap.put("A1", new Dimension(594, 841));
    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        this.setVisible(!this.isVisible());
    }
}
