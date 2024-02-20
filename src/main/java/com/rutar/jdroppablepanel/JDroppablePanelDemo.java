package com.rutar.jdroppablepanel;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;

// ............................................................................

/**
 * Клас JDroppablePanelDemo
 * @author Rutar_Andriy
 * 16.02.2024
 */

public class JDroppablePanelDemo extends JFrame {

///////////////////////////////////////////////////////////////////////////////

public JDroppablePanelDemo() {

super();
initComponents();
initAppIcons();

setLocationRelativeTo(null);

}

///////////////////////////////////////////////////////////////////////////////
                       
private void initComponents() {

label_drop = new JLabel();
label_step = new JLabel();
label_indent = new JLabel();

slider_step = new JSlider();
slider_indent = new JSlider();

btn_fl_draw = new JToggleButton();
btn_sl_draw = new JToggleButton();

btn_fl_color = new JButton();
btn_sl_color = new JButton();

btn_fl_stroke = new JButton();
btn_sl_stroke = new JButton();

btn_a_border = new JButton();
btn_p_border = new JButton();

droppable_panel = new JDroppablePanel();

panel_01 = new JPanel();
panel_02 = new JPanel();
panel_03 = new JPanel();
panel_04 = new JPanel();

setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
setTitle("JDroppablePanel Demo");
setResizable(false);

label_drop.setHorizontalAlignment(SwingConstants.CENTER);
label_drop.setText("<html><body style='text-align: center'>Спробуйте перетягнути сюди будь-які файли");
label_drop.setToolTipText("");
label_drop.setHorizontalTextPosition(SwingConstants.LEADING);

GroupLayout droppable_panelLayout = new GroupLayout(droppable_panel);
droppable_panel.setLayout(droppable_panelLayout);
droppable_panelLayout.setHorizontalGroup(
    droppable_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    .addGroup(droppable_panelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(label_drop, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
        .addContainerGap())
);
droppable_panelLayout.setVerticalGroup(
    droppable_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
    .addGroup(droppable_panelLayout.createSequentialGroup()
        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(label_drop, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);

label_step.setHorizontalAlignment(SwingConstants.CENTER);
label_step.setText("Крок ліній");

slider_step.setMaximum(7);
slider_step.setMinimum(3);
slider_step.setToolTipText("Крок ліній: 5");
slider_step.setValue(5);
slider_step.addChangeListener(this::onSliderChange);

label_indent.setHorizontalAlignment(SwingConstants.CENTER);
label_indent.setText("Відступ по краях елемента");

slider_indent.setMaximum(30);
slider_indent.setToolTipText("Відступ по краях елемента: 5");
slider_indent.setValue(5);
slider_indent.addChangeListener(this::onSliderChange);

panel_01.setLayout(new GridLayout(1, 0));

btn_fl_draw.setText("I візерунок");
btn_fl_draw.setToolTipText("Видимий");
btn_fl_draw.setActionCommand("btn_fl_draw");
btn_fl_draw.addActionListener(this::onButtonClick);
panel_01.add(btn_fl_draw);

btn_sl_draw.setText("II візерунок");
btn_sl_draw.setToolTipText("Видимий");
btn_sl_draw.setActionCommand("btn_sl_draw");
btn_sl_draw.addActionListener(this::onButtonClick);
panel_01.add(btn_sl_draw);

panel_02.setLayout(new GridLayout(1, 0));

btn_fl_color.setText("Колір ліній I візерунка");
btn_fl_color.setToolTipText("Колір: 0x6666ff");
btn_fl_color.setActionCommand("btn_fl_color");
btn_fl_color.addActionListener(this::onButtonClick);
panel_02.add(btn_fl_color);

btn_sl_color.setText("Колір ліній II візерунка");
btn_sl_color.setToolTipText("Колір: 0x6666ff");
btn_sl_color.setActionCommand("btn_sl_color");
btn_sl_color.addActionListener(this::onButtonClick);
panel_02.add(btn_sl_color);

panel_03.setLayout(new GridLayout(1, 0));

btn_fl_stroke.setText("Штрихування ліній I візерунка");
btn_fl_stroke.setActionCommand("btn_fl_stroke");
btn_fl_stroke.setToolTipText(Arrays.toString(((BasicStroke)(droppable_panel
             .getFirstLineStroke())).getDashArray()));
btn_fl_stroke.addActionListener(this::onButtonClick);
panel_03.add(btn_fl_stroke);

btn_sl_stroke.setText("Штрихування ліній II візерунка");
btn_sl_stroke.setActionCommand("btn_sl_stroke");
btn_sl_stroke.setToolTipText(Arrays.toString(((BasicStroke)(droppable_panel
             .getSecondLineStroke())).getDashArray()));
btn_sl_stroke.addActionListener(this::onButtonClick);
panel_03.add(btn_sl_stroke);

panel_04.setLayout(new GridLayout(1, 0));

btn_a_border.setText("Активна рамка");
btn_a_border.setToolTipText("Колір: 0x6666ff");
btn_a_border.setActionCommand("btn_a_border");
btn_a_border.addActionListener(this::onButtonClick);
panel_04.add(btn_a_border);

btn_p_border.setText("Неактивна рамка");
btn_p_border.setToolTipText("Колір: 0x7d7d7d");
btn_p_border.setActionCommand("btn_p_border");
btn_p_border.addActionListener(this::onButtonClick);
panel_04.add(btn_p_border);

GroupLayout layout = new GroupLayout(getContentPane());
getContentPane().setLayout(layout);
layout.setHorizontalGroup(
    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(droppable_panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(label_step, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(slider_step, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(label_indent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(slider_indent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_02, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_03, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_04, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_01, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
);
layout.setVerticalGroup(
    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(droppable_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(label_step)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slider_step, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_indent)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slider_indent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_01, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_02, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_03, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_04, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
);

pack();

}                               

///////////////////////////////////////////////////////////////////////////////

private void onButtonClick (ActionEvent evt) {                               
     
switch (evt.getActionCommand()) {

case "btn_fl_draw"   ->
    { droppable_panel.setFirstLineDraw(!btn_fl_draw.isSelected());
      btn_fl_draw.setToolTipText(!btn_fl_draw.isSelected() ? "Видимий" :
                                                             "Невидимий"); }

case "btn_sl_draw"   ->
    { droppable_panel.setSecondLineDraw(!btn_sl_draw.isSelected());
      btn_sl_draw.setToolTipText(!btn_sl_draw.isSelected() ? "Видимий" :
                                                             "Невидимий"); }
     
case "btn_fl_color"  ->
    { Color color = getRandomColor();
      droppable_panel.setFirstLineColor(color);
      btn_fl_color.setToolTipText(getColorValue(color)); }

case "btn_sl_color"  ->
    { Color color = getRandomColor();
      droppable_panel.setSecondLineColor(color);
      btn_sl_color.setToolTipText(getColorValue(color)); }

case "btn_fl_stroke" ->
    { BasicStroke stroke = getRandomStroke((int)(Math.random()*7)+2);
      droppable_panel.setFirstLineStroke(stroke);
      btn_fl_stroke.setToolTipText(Arrays.toString(stroke.getDashArray())); }

case "btn_sl_stroke" ->
    { BasicStroke stroke = getRandomStroke((int)(Math.random()*7)+2);
      droppable_panel.setSecondLineStroke(stroke);
      btn_sl_stroke.setToolTipText(Arrays.toString(stroke.getDashArray())); }
        
case "btn_a_border"  ->
    { Color color = null;
      Border border = getRandomBorder();
      if (border instanceof LineBorder lb)   { color = lb.getLineColor();    }
      if (border instanceof StrokeBorder sb) { color = (Color)sb.getPaint(); }
      droppable_panel.setActiveBorder(border);
      btn_a_border.setToolTipText(getColorValue(color)); }

case "btn_p_border"  ->
    { Color color = null;
      Border border = getRandomBorder();
      if (border instanceof LineBorder lb)   { color = lb.getLineColor();    }
      if (border instanceof StrokeBorder sb) { color = (Color)sb.getPaint(); }
      droppable_panel.setPassiveBorder(border);
      btn_p_border.setToolTipText(getColorValue(color)); }

}
}

///////////////////////////////////////////////////////////////////////////////

private void onSliderChange (ChangeEvent evt) {                                

Object source = evt.getSource();

if (source.equals(slider_step))
    { droppable_panel.setLineStep(slider_step.getValue()); }
if (source.equals(slider_indent))
    { droppable_panel.setLineIndent(slider_indent.getValue()); }

}

///////////////////////////////////////////////////////////////////////////////

private Color getRandomColor() {

return new Color((int)(Math.random() * 255),
                 (int)(Math.random() * 255),
                 (int)(Math.random() * 255));

}

///////////////////////////////////////////////////////////////////////////////

private Border getRandomBorder() {

int width = (int)(Math.random()*5)+2;

Border strokeBorder = new StrokeBorder(getRandomStroke(width),
                                       getRandomColor());

Border lineBorder = new LineBorder(getRandomColor(),
                                   width, Math.random() < 0.5);

return Math.random() < 0.5 ? lineBorder : strokeBorder;

}

///////////////////////////////////////////////////////////////////////////////

private BasicStroke getRandomStroke (int width) {

float[] dashing_pattern = { (int)(Math.random()*25) + 5,
                            (int)(Math.random()*25) + 5,
                            (int)(Math.random()*10) + 3,
                            (int)(Math.random()*25) + 5};

return new BasicStroke(width, BasicStroke.CAP_SQUARE,
                              BasicStroke.JOIN_MITER,
                              1.0f, dashing_pattern, 0.0f);

}

///////////////////////////////////////////////////////////////////////////////

private String getColorValue (Color color) {

int r = color.getRed();
int g = color.getGreen();
int b = color.getBlue();

r = (r << 16) & 0xFF0000;
g = (g << 8)  & 0x00FF00;
b =  b        & 0x0000FF;

int intColor = r | g | b;

return String.format("Колір: 0x%06x", intColor);

}

///////////////////////////////////////////////////////////////////////////////

public static void main (String args[]) {
    
    EventQueue.invokeLater(() -> {
        new JDroppablePanelDemo().setVisible(true);
    });
}

///////////////////////////////////////////////////////////////////////////////

private void initAppIcons() {

String path;
ImageIcon icon;
ArrayList<Image> icons = new ArrayList<>();

for (String name : new String[]{ "16", "32" }) {
    path = String.format("/com/rutar/jdroppablepanel/icons/"
                       + "jdroppablepanel/icon_%s.png", name);
    icon = new ImageIcon(getClass().getResource(path));
    icons.add(icon.getImage());
}

setIconImages(icons);

}

///////////////////////////////////////////////////////////////////////////////

private JLabel label_drop;
private JLabel label_step;
private JLabel label_indent;

private JSlider slider_step;
private JSlider slider_indent;

private JToggleButton btn_fl_draw;
private JToggleButton btn_sl_draw;

private JButton btn_fl_color;
private JButton btn_sl_color;

private JButton btn_fl_stroke;
private JButton btn_sl_stroke;

private JButton btn_a_border;
private JButton btn_p_border;

private JDroppablePanel droppable_panel;

private JPanel panel_01;
private JPanel panel_02;
private JPanel panel_03;
private JPanel panel_04;

///////////////////////////////////////////////////////////////////////////////

private void printComponentChange (String eventText, JDroppablePanelEvent evt) {
    System.out.println(eventText + " was changed" +
                                   " from " + evt.getOldValue() +
                                   " to "   + evt.getNewValue());
}

///////////////////////////////////////////////////////////////////////////////

JDroppablePanelListener JDroppablePanelListener = new JDroppablePanelListener() {

    @Override
    public void firstLineDrawChange(JDroppablePanelEvent evt)
        { printComponentChange("firstLineDraw", evt); }

    @Override
    public void secondtLineDrawChange(JDroppablePanelEvent evt)
        { printComponentChange("secondLineDraw", evt); }

    @Override
    public void firstLineColorChange(JDroppablePanelEvent evt)
        { printComponentChange("firstLineColor", evt); }

    @Override
    public void secondLineColorChange(JDroppablePanelEvent evt)
        { printComponentChange("secondLineColor", evt); }

    @Override
    public void firstLineStrokeChange(JDroppablePanelEvent evt)
        { printComponentChange("firstLineStroke", evt); }

    @Override
    public void secondLineStrokeChange(JDroppablePanelEvent evt)
        { printComponentChange("secondLineStroke", evt); }

    @Override
    public void activeBorderChange(JDroppablePanelEvent evt)
        { printComponentChange("activeBorder", evt); }

    @Override
    public void passiveBorderChange(JDroppablePanelEvent evt)
        { printComponentChange("passiveBorder", evt); }

    @Override
    public void lineStepChange(JDroppablePanelEvent evt)
        { printComponentChange("lineStep", evt); }

    @Override
    public void lineIndentChange(JDroppablePanelEvent evt)
        { printComponentChange("lineIndent", evt); }
};

// Кінець класу JDroppablePanelDemo ///////////////////////////////////////////

}