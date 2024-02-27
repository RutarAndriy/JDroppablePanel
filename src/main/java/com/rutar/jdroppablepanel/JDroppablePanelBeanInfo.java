package com.rutar.jdroppablepanel;

import java.io.*;
import java.awt.*;
import java.util.*;
import java.beans.*;
import java.awt.dnd.*;
import javax.imageio.*;

import static java.beans.BeanInfo.*;

// ............................................................................

/**
 * Клас JDroppablePanelBeanInfo
 * @author Rutar_Andriy
 * 16.02.2024
 */

public class JDroppablePanelBeanInfo extends SimpleBeanInfo {

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод повертає список доступних властивостей
 * @return список доступних властивостей
 */
@Override
public PropertyDescriptor[] getPropertyDescriptors() {

PropertyDescriptor property;
ArrayList <PropertyDescriptor> properties = new ArrayList<>();

try {

// DaDActive
property = new PropertyDescriptor("active", JDroppablePanel.class,
                                  "isDaDActive", "setDaDActive");
property.setPreferred(true);
properties.add(property);

// FirstLineDraw
property = new PropertyDescriptor("firstLineDraw", JDroppablePanel.class,
                                  "isFirstLineDraw", "setFirstLineDraw");
property.setPreferred(true);
properties.add(property);

// SecondLineDraw
property = new PropertyDescriptor("secondLineDraw", JDroppablePanel.class,
                                  "isSecondLineDraw", "setSecondLineDraw");
property.setPreferred(true);
properties.add(property);

// FirstLineColor
property = new PropertyDescriptor("firstLineColor", JDroppablePanel.class,
                                  "getFirstLineColor", "setFirstLineColor");
property.setPreferred(true);
properties.add(property);

// SecondLineColor
property = new PropertyDescriptor("secondLineColor", JDroppablePanel.class,
                                  "getSecondLineColor", "setSecondLineColor");
property.setPreferred(true);
properties.add(property);

// FirstLineStroke
property = new PropertyDescriptor("firstLineStroke", JDroppablePanel.class,
                                  "getFirstLineStroke", "setFirstLineStroke");
property.setPreferred(true);
properties.add(property);

// SecondLineStroke
property = new PropertyDescriptor("secondLineStroke", JDroppablePanel.class,
                                  "getSecondLineStroke", "setSecondLineStroke");
property.setPreferred(true);
properties.add(property);

// ActiveBorder
property = new PropertyDescriptor("activeBorder", JDroppablePanel.class,
                                  "getActiveBorder", "setActiveBorder");
property.setPreferred(true);
properties.add(property);

// PassiveBorder
property = new PropertyDescriptor("passiveBorder", JDroppablePanel.class,
                                  "getPassiveBorder", "setPassiveBorder");
property.setPreferred(true);
properties.add(property);

// LineStep
property = new PropertyDescriptor("lineStep", JDroppablePanel.class,
                                  "getLineStep", "setLineStep");
property.setPreferred(true);
properties.add(property);

// LineIndent
property = new PropertyDescriptor("lineIndent", JDroppablePanel.class,
                                  "getLineIndent", "setLineIndent");
property.setPreferred(true);
properties.add(property);

}

catch (IntrospectionException e) { }

return properties.toArray(PropertyDescriptor[]::new);

}

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод повертає список доступних прослуховувачів
 * @return список доступних прослуховувачів
 */
@Override
public EventSetDescriptor[] getEventSetDescriptors() {

String[] methods;
EventSetDescriptor eventSet;
ArrayList <EventSetDescriptor> descriptors = new ArrayList<>();

try {

// ............................................................................
// JDroppablePanelListener

methods = new String[] { "activeChange",
                         "firstLineDrawChange",
                         "secondtLineDrawChange",
                         "firstLineColorChange",
                         "secondLineColorChange",
                         "firstLineStrokeChange",
                         "secondLineStrokeChange",
                         "activeBorderChange",
                         "passiveBorderChange",
                         "lineStepChange",
                         "lineIndentChange" };

eventSet = new EventSetDescriptor(JDroppablePanel.class,
                                  "JDroppablePanelListener",
                                  JDroppablePanelAdapter.class, methods,
                                  "addJDroppablePanelListener",
                                  "removeJDroppablePanelListener");

descriptors.add(eventSet);

// ............................................................................
// DropTargetListener

methods = new String[] { "dragEnter",
                         "dragExit",
                         "dragOver",
                         "drop" };

eventSet = new EventSetDescriptor(JDroppablePanel.class,
                                  "DropTargetListener",
                                  DropTargetAdapter.class, methods,
                                  "addDropTargetListener",
                                  "removeDropTargetListener");

descriptors.add(eventSet);

// ............................................................................
// PropertyChangeListener

methods = new String[] { "propertyChange" };

eventSet = new EventSetDescriptor(JDroppablePanel.class,
                                  "PropertyChangeListener",
                                  PropertyChangeListener.class, methods,
                                  "addPropertyChangeListener",
                                  "removePropertyChangeListener");

descriptors.add(eventSet);
  
}

catch (IntrospectionException e) { }

return descriptors.toArray(EventSetDescriptor[]::new);

}

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод повертає об'єкт зображення типу Image
 * @param iconType Тип іконки - константа класу BeanInfo
 * @return об'єкт типу Image
 */
@Override
public Image getIcon (int iconType) {

return switch (iconType) {

    case ICON_MONO_16x16, ICON_COLOR_16x16 -> loadIcon(false);
    case ICON_MONO_32x32, ICON_COLOR_32x32 -> loadIcon(true);

    default -> null;

};
}

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод завантажує зображення різного розміру
 * @param large Розмір іконки (true - великий, false - малий)
 * @return об'єкт типу Image
 */
private Image loadIcon (boolean large) {

    Image image;
    String res = "/com/rutar/jdroppablepanel/icons/"
               + "icon_" + (large ? "32" : "16") + ".png";
    
    try (InputStream stream = JDroppablePanelBeanInfo.class
                             .getResourceAsStream(res)) {
            
        image = ImageIO.read(stream);
        return image;
    
    }
    
    catch (IOException ex) { return null; }

}

// Кінець класу JDroppablePanelBeanInfo ///////////////////////////////////////

}
