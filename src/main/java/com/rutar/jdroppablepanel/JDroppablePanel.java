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

private final DropTarget dropTarget;

private static ArrayList <DropTargetListener> DaD_listeners = null;
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
    
    dropTarget = new DropTarget(this, dropTargetListener);
        
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

/**
 * Метод промальовує JDroppablePanel-компонент
 * @param graphics об'єкт типу Graphics
 */
@Override
public void paintComponent (Graphics graphics) {

super.paintComponent(graphics);
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

/**
 * Метод дозволяє визначити чи активна функція DaD
 * @return якщо true - функція DaD активна
 */
public boolean isDaDActive() { return dropTarget.isActive(); }

/**
 * Метод активує/деактивує функцію DaD 
 * @param active якщо true - активувати функцію DaD
 */
public void setDaDActive (boolean active)
    { boolean oldValue = isDaDActive();
      fireEvent("DaDActive", oldValue, active);
      getPropertyChangeSupport().firePropertyChange("DaDActive",
                                                    oldValue, active);
      dropTarget.setActive(active); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод дозволяє визначити чи відображається візерунок I типу
 * @return якщо true - візерунок відображається
 */
public boolean isFirstLineDraw() { return firstLineDraw; }

/**
 * Метод активує/деактивує відображення візерунку I типу
 * @param draw якщо true - відображати візерунок
 */
public void setFirstLineDraw (boolean draw)
    { boolean oldValue = this.firstLineDraw;
      this.firstLineDraw = draw;
      fireEvent("firstLineDraw", oldValue, draw);
      getPropertyChangeSupport().firePropertyChange("firstLineDraw",
                                                    oldValue, draw);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод дозволяє визначити чи відображається візерунок II типу
 * @return якщо true - візерунок відображається
 */
public boolean isSecondLineDraw() { return secondLineDraw; }

/**
 * Метод активує/деактивує відображення візерунку II типу
 * @param draw якщо true - відображати візерунок
 */
public void setSecondLineDraw (boolean draw)
    { boolean oldValue = this.secondLineDraw;
      this.secondLineDraw = draw;
      fireEvent("secondLineDraw", oldValue, draw);
      getPropertyChangeSupport().firePropertyChange("secondLineDraw",
                                                    oldValue, draw);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод дозволяє отримати колір візерунку I типу
 * @return об'єкт класу Color
 */
public Color getFirstLineColor() { return firstLineColor;  }

/**
 * Метод дозволяє задати колір візерунку I типу
 * @param color об'єкт класу Color
 */
public void setFirstLineColor (Color color)
    { Color oldValue = this.firstLineColor;
      this.firstLineColor = color;
      fireEvent("firstLineColor", oldValue, color);
      getPropertyChangeSupport().firePropertyChange("firstLineColor",
                                                    oldValue, color);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод дозволяє отримати колір візерунку II типу
 * @return об'єкт класу Color
 */
public Color getSecondLineColor() { return secondLineColor; }

/**
 * Метод дозволяє задати колір візерунку II типу
 * @param color об'єкт класу Color
 */
public void setSecondLineColor (Color color)
    { Color oldValue = this.secondLineColor;
      this.secondLineColor = color;
      fireEvent("secondLineColor", oldValue, color);
      getPropertyChangeSupport().firePropertyChange("secondLineColor",
                                                    oldValue, color);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод дозволяє отримати штрихування візерунку I типу
 * @return об'єкт класу Stroke
 */
public Stroke getFirstLineStroke() { return firstLineStroke;  }

/**
 * Метод дозволяє задати штрихування візерунку I типу
 * @param stroke об'єкт класу Stroke
 */
public void setFirstLineStroke (Stroke stroke)
    { Stroke oldValue = this.firstLineStroke;
      this.firstLineStroke = stroke;
      fireEvent("firstLineStroke", oldValue, stroke);
      getPropertyChangeSupport().firePropertyChange("firstLineStroke",
                                                    oldValue, stroke);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод дозволяє отримати штрихування візерунку II типу
 * @return об'єкт класу Stroke
 */
public Stroke getSecondLineStroke() { return secondLineStroke; }

/**
 * Метод дозволяє задати штрихування візерунку II типу
 * @param stroke об'єкт класу Stroke
 */
public void setSecondLineStroke (Stroke stroke)
    { Stroke oldValue = this.secondLineStroke;
      this.secondLineStroke = stroke;
      fireEvent("secondLineStroke", oldValue, stroke);
      getPropertyChangeSupport().firePropertyChange("secondLineStroke",
                                                    oldValue, stroke);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод дозволяє отримати рамку, коли функція DaD активна
 * @return об'єкт класу Border
 */
public Border getActiveBorder()  { return activeBorder;  }

/**
 * Метод дозволяє задати рамку, яка відображається коли функція DaD активна
 * @param border об'єкт класу Border
 */
public void setActiveBorder (Border border)
    { Border oldValue = this.activeBorder;
      this.activeBorder = border;
      fireEvent("activeBorder", oldValue, border);
      getPropertyChangeSupport().firePropertyChange("activeBorder",
                                                    oldValue, border);
      setBorder(dragActive ? activeBorder : passiveBorder);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод дозволяє отримати рамку, коли функція DaD неактивна
 * @return об'єкт класу Border
 */
public Border getPassiveBorder() { return passiveBorder; }

/**
 * Метод дозволяє задати рамку, яка відображається коли функція DaD неактивна
 * @param border об'єкт класу Border
 */
public void setPassiveBorder (Border border)
    { Border oldValue = this.passiveBorder;
      this.passiveBorder = border;
      fireEvent("passiveBorder", oldValue, border);
      getPropertyChangeSupport().firePropertyChange("passiveBorder",
                                                    oldValue, border);
      setBorder(dragActive ? activeBorder : passiveBorder);
      repaint(); }

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод дозволяє отримати крок промальовування ліній
 * @return значення кроку промальовування ліній
 */
public int getLineStep() { return lineStep; }

/**
 * Метод дозволяє задати крок промальовування ліній
 * @param lineStep значення кроку промальовування ліній
 */
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

/**
 * Метод дозволяє отримати відступ промальовування ліній
 * @return значення відступу промальовування ліній
 */
public int getLineIndent() { return lineIndent; }

/**
 * Метод дозволяє задати відступ промальовування ліній
 * @param lineIndent значення відступу промальовування ліній
 */
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

private final DropTargetListener dropTargetListener
        = new DropTargetAdapter() {

@Override
public void dragEnter (DropTargetDragEvent e) {
    dragActive = true;
    setBorder(activeBorder);
    fireDaDEvent("dragEnter", e);
}

@Override
public void dragExit (DropTargetEvent e) {
    dragActive = false;
    setBorder(passiveBorder);
    fireDaDEvent("dragExit", e);
}

@Override
public void dragOver (DropTargetDragEvent e) {
    fireDaDEvent("dragOver", e);
}

// ............................................................................

@Override
public void drop (DropTargetDropEvent e) {
    dragActive = false;
    setBorder(passiveBorder);
    fireDaDEvent("drop", e);
}
};

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод додає прослуховувач подій типу PropertyChangeListener
 * @param listener об'єкт класу PropertyChangeListener
 */
@Override
public void addPropertyChangeListener (PropertyChangeListener listener)
    { getPropertyChangeSupport().addPropertyChangeListener(listener); }

// ............................................................................

/**
 * Метод видаляє прослуховувач подій типу PropertyChangeListener
 * @param listener об'єкт класу PropertyChangeListener
 */
@Override
public void removePropertyChangeListener (PropertyChangeListener listener)
    { getPropertyChangeSupport().removePropertyChangeListener(listener); }

// ............................................................................

private PropertyChangeSupport getPropertyChangeSupport() {
    if (propertyChangeSupport == null) {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }
    return propertyChangeSupport;
}

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод додає прослуховувач подій типу JDroppablePanelListener
 * @param listener об'єкт класу JDroppablePanelListener
 */
public void addJDroppablePanelListener (JDroppablePanelListener listener)
    { getListenersArrayList().add(listener); }

// ............................................................................

/**
 * Метод видаляє прослуховувач подій типу JDroppablePanelListener
 * @param listener об'єкт класу JDroppablePanelListener
 */
public void removeJDroppablePanelListener (JDroppablePanelListener listener)
    { getListenersArrayList().remove(listener); }

// ............................................................................

/**
 * Метод певертає масив прослуховувачів типу JDroppablePanelListener
 * @return масив об'єктів класу JDroppablePanelListener
 */
public JDroppablePanelListener[] getJDroppablePanelListeners()
    { return getListenersArrayList().toArray(JDroppablePanelListener[]::new); }

// ............................................................................

private ArrayList <JDroppablePanelListener> getListenersArrayList()
    { if (listeners == null) { listeners = new ArrayList<>(); }
      return listeners; }

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод додає прослуховувач подій типу DropTargetListener
 * @param listener об'єкт класу DropTargetListener
 */
public void addDropTargetListener (DropTargetListener listener)
    { getDaDListenersArrayList().add(listener); }

// ............................................................................

/**
 * Метод видаляє прослуховувач подій типу DropTargetListener
 * @param listener об'єкт класу DropTargetListener
 */
public void removeDropTargetListener (DropTargetListener listener)
    { getDaDListenersArrayList().remove(listener); }

// ............................................................................

/**
 * Метод певертає масив прослуховувачів типу DropTargetListener
 * @return масив об'єктів класу DropTargetListener
 */
public DropTargetListener[] getDaDListeners()
    { return getDaDListenersArrayList().toArray(DropTargetListener[]::new); }

// ............................................................................

private ArrayList <DropTargetListener> getDaDListenersArrayList()
    { if (DaD_listeners == null) { DaD_listeners = new ArrayList<>(); }
      return DaD_listeners; }

///////////////////////////////////////////////////////////////////////////////

private void fireEvent (String type, Object oldValue, Object newValue) {

JDroppablePanelEvent event = new JDroppablePanelEvent(this, oldValue, newValue);

for (JDroppablePanelListener listener : getListenersArrayList()) {

    switch (type) {
        
        case "DaDActive"        -> listener.activeChange            (event);
        case "firstLineDraw"    -> listener.firstLineDrawChange     (event);
        case "secondLineDraw"   -> listener.secondtLineDrawChange   (event);
        case "firstLineColor"   -> listener.firstLineColorChange    (event);
        case "secondLineColor"  -> listener.secondLineColorChange   (event);
        case "firstLineStroke"  -> listener.firstLineStrokeChange   (event);
        case "secondLineStroke" -> listener.secondLineStrokeChange  (event);
        case "activeBorder"     -> listener.activeBorderChange      (event);
        case "passiveBorder"    -> listener.passiveBorderChange     (event);
        case "lineStep"         -> listener.lineStepChange          (event);
        case "lineIndent"       -> listener.lineIndentChange        (event);

    }
}
}

///////////////////////////////////////////////////////////////////////////////

private void fireDaDEvent (String type, Object event) {

for (DropTargetListener listener : getDaDListenersArrayList()) {
    
    switch (type) {
        
        // Вхід у межі прослуховуваного об'єкта
        case "dragEnter"         ->
            listener.dragEnter((DropTargetDragEvent) event);
        // Вихід за межі прослуховуваного об'єкта
        case "dragExit"          ->
            listener.dragExit((DropTargetEvent) event);
        // Переміщення над межею прослуховуваного об'єкта
        case "dragOver"          ->
            listener.dragOver((DropTargetDragEvent) event);
        // Завершення drop-події
        case "drop"              ->
            listener.drop((DropTargetDropEvent) event);
            
    } 
}
}

// Кінець класу JDroppablePanel ///////////////////////////////////////////////

}
