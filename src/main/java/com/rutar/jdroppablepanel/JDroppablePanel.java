package com.rutar.jdroppablepanel;

import java.awt.*;
import java.util.*;
import java.beans.*;
import javax.swing.*;
import java.awt.dnd.*;
import javax.swing.border.*;

// ............................................................................

/**
 * Клас JDroppablePanel
 * @author Rutar_Andriy
 * 16.02.2024
 */

public class JDroppablePanel extends JPanel {

private int W;                                                 // Ширина панелі
private int H;                                                 // Висота панелі
private int lineStep;                                             // Крок ліній
private int lineIndent;                            // Відступ по краях елемента

private Graphics2D g = null;

// ............................................................................

private boolean dragActive;           // Вказує чи активний режим перетягування

private Border activeBorder;        // Рамка, коли режим перетягування активний
private Border passiveBorder;     // Рамка, коли режим перетягування неактивний

private boolean firstLineDraw;                // Вказує чи малювати I візерунок
private Color   firstLineColor;                      // Колір ліній I візерунка
private Stroke  firstLineStroke;                 // Штриховка ліній I візерунка

private boolean secondLineDraw;              // Вказує чи малювати II візерунок
private Color   secondLineColor;                    // Колір ліній II візерунка
private Stroke  secondLineStroke;               // Штриховка ліній II візерунка

// ............................................................................

private final DropTarget drop_target;

private static ArrayList <JDroppablePanelListener> listeners = null;
private static transient PropertyChangeSupport propertyChangeSupport = null;

///////////////////////////////////////////////////////////////////////////////

public JDroppablePanel() {

    super();

    lineStep = 5;
    lineIndent = 5;
    
    firstLineDraw = true;
    secondLineDraw = true;
    
    initDefaultLineColor();
    initDefaultLineStroke();
    
    initDefaultBorders();
    setBorder(passiveBorder);
    
    drop_target = new DropTarget(this, drop_target_listener);
    
}

///////////////////////////////////////////////////////////////////////////////

private final DropTargetListener drop_target_listener
        = new DropTargetAdapter() {

@Override
public void drop (DropTargetDropEvent e) {
    //Processing.drag_And_Drop_Files(e);
    dragActive = false;
    setBorder(passiveBorder);
}

// ............................................................................

@Override
public void dragEnter (DropTargetDragEvent e) {
    dragActive = true;
    setBorder(activeBorder);
}

// ............................................................................

@Override
public void dragExit (DropTargetEvent e) {
    dragActive = false;
    setBorder(passiveBorder);
}
             
};

///////////////////////////////////////////////////////////////////////////////

@Override
public void paint (Graphics graphics) {

super.paint(graphics);
if (!dragActive) { return; }

W = getWidth();
H = getHeight();
g = (Graphics2D) graphics;

Shape old_clip = g.getClip();
g.setClip(lineIndent, lineIndent, W-lineIndent*2, H-lineIndent*2);

// ............................................................................

g.setColor(getFirstLineColor());
g.setStroke(getFirstLineStroke());

if (firstLineDraw) {
    for (int z = 0; z < H + W; z+=lineStep*2)
        { g.drawLine(0, z*lineStep, z*lineStep, 0); }
}

g.setColor(getSecondLineColor());
g.setStroke(getSecondLineStroke());

if (secondLineDraw) {
    for (int z = 0; z < H + W; z+=lineStep*2)
        { g.drawLine(W, z*lineStep, W-z*lineStep, 0); }
}

// ............................................................................

g.setClip(old_clip);

}

///////////////////////////////////////////////////////////////////////////////

private void initDefaultLineColor()
    { setFirstLineColor (new Color(0x6666ff));
      setSecondLineColor(new Color(0x6666ff)); }

///////////////////////////////////////////////////////////////////////////////

private void initDefaultLineStroke() {

float[] dashing_pattern = { 10f, 10f, 1f, 10f };

Stroke stroke = new BasicStroke(4f, BasicStroke.CAP_SQUARE,
                                    BasicStroke.JOIN_MITER,
                                    1.0f, dashing_pattern, 0.0f);

setFirstLineStroke(stroke);
setSecondLineStroke(stroke);

}

///////////////////////////////////////////////////////////////////////////////

private void initDefaultBorders() {

    // Активна рамка
    setActiveBorder(new LineBorder(getFirstLineColor(), 3));
    
    // Неактивна рамка
    setPassiveBorder(new LineBorder(Color.GRAY, 3));

}

///////////////////////////////////////////////////////////////////////////////

public Color getFirstLineColor()  { return firstLineColor;  }
public Color getSecondLineColor() { return secondLineColor; }

public void setFirstLineColor  (Color color) { firstLineColor  = color; }
public void setSecondLineColor (Color color) { secondLineColor = color; }

///////////////////////////////////////////////////////////////////////////////

public Stroke getFirstLineStroke()  { return firstLineStroke;  }
public Stroke getSecondLineStroke() { return secondLineStroke; }

public void setFirstLineStroke  (Stroke stroke) { firstLineStroke  = stroke; }
public void setSecondLineStroke (Stroke stroke) { secondLineStroke = stroke; }

///////////////////////////////////////////////////////////////////////////////

public Border getActiveBorder()  { return activeBorder;  }
public Border getPassiveBorder() { return passiveBorder; }

public void setActiveBorder  (Border border) { activeBorder  = border; }
public void setPassiveBorder (Border border) { passiveBorder = border; }

///////////////////////////////////////////////////////////////////////////////

public boolean isFirstLineDraw() { return firstLineDraw; }

public void setFirstLineDraw (boolean draw)
    { boolean oldValue = this.firstLineDraw;
      this.firstLineDraw = draw;
      fireEvent("firstLineDraw", oldValue, draw);
      getPropertyChangeSupport().firePropertyChange("firstLineDraw",
                                                    oldValue, draw);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

public boolean isSecondLineDraw() { return secondLineDraw; }

public void setSecondLineDraw (boolean draw)
    { boolean oldValue = this.secondLineDraw;
      this.secondLineDraw = draw;
      fireEvent("secondLineDraw", oldValue, draw);
      getPropertyChangeSupport().firePropertyChange("secondLineDraw",
                                                    oldValue, draw);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

public int getLineStep() { return lineStep; }

public void setLineStep (int lineStep)
    { if (lineStep > 7) { lineStep = 7; }
      if (lineStep < 3) { lineStep = 3; }
      int oldValue = this.lineStep;
      this.lineStep = lineStep;
      fireEvent("lineStep", oldValue, lineStep);
      getPropertyChangeSupport().firePropertyChange("lineStep",
                                                    oldValue, lineStep);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

public int getLineIndent() { return lineIndent; }

public void setLineIndent (int lineIndent)
    { if (lineIndent > 30) { lineIndent = 30; }
      if (lineIndent < 0)  { lineIndent = 0;  }
      int oldValue = this.lineIndent;
      this.lineIndent = lineIndent;
      fireEvent("lineIndent", oldValue, lineIndent);
      getPropertyChangeSupport().firePropertyChange("lineIndent",
                                                    oldValue, lineIndent);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

//@Override
//public void setBackground (Color bg)
//    { Color oldValue = getBackground();
//      fireEvent("background", oldValue, bg);
//      getPropertyChangeSupport().firePropertyChange("background",
//                                                    oldValue, bg);
//      super.setBackground(bg); }

///////////////////////////////////////////////////////////////////////////////

@Override
public void addPropertyChangeListener (PropertyChangeListener listener)
    { getPropertyChangeSupport().addPropertyChangeListener(listener); }

///////////////////////////////////////////////////////////////////////////////

@Override
public void removePropertyChangeListener (PropertyChangeListener listener)
    { getPropertyChangeSupport().removePropertyChangeListener(listener); }

///////////////////////////////////////////////////////////////////////////////

private PropertyChangeSupport getPropertyChangeSupport() {
    if (propertyChangeSupport == null) {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }
    return propertyChangeSupport;
}

///////////////////////////////////////////////////////////////////////////////

public void addJDroppablePanelListener (JDroppablePanelListener listener)
    { getListeners().add(listener); }

///////////////////////////////////////////////////////////////////////////////

public void removeJDroppablePanelListener (JDroppablePanelListener listener)
    { getListeners().remove(listener); }

///////////////////////////////////////////////////////////////////////////////

private ArrayList <JDroppablePanelListener> getListeners()
    { if (listeners == null) { listeners = new ArrayList<>(); }
      return listeners; }

///////////////////////////////////////////////////////////////////////////////

private void fireEvent (String type, Object oldValue, Object newValue) {

JDroppablePanelEvent event = new JDroppablePanelEvent(this, oldValue, newValue);

for (JDroppablePanelListener listener : getListeners()) {

    switch (type) {
        case "smile"      -> listener.smileChange(event);
        case "lineWidth"  -> listener.lineWidthChange(event);
        case "mouthWidth" -> listener.mouthWidthChange(event);
        case "background" -> listener.backgroundChange(event);
        case "foreground" -> listener.foregroundChange(event);
    }
}
}

// Кінець класу JDroppablePanel ///////////////////////////////////////////////

}
