package com.rutar.jdroppablepanel;

import java.io.*;
import java.awt.*;
import java.util.*;
import java.beans.*;
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

// Background
property = new PropertyDescriptor("background", JDroppablePanel.class,
                                  "getBackground", "setBackground");
property.setPreferred(false);
properties.add(property);

// Foreground
property = new PropertyDescriptor("foreground", JDroppablePanel.class,
                                  "getForeground", "setForeground");
property.setPreferred(false);
properties.add(property);

// LineWidth
property = new PropertyDescriptor("lineWidth", JDroppablePanel.class,
                                  "getLineWidth", "setLineWidth");
property.setPreferred(true);
properties.add(property);

// MouthWidth
property = new PropertyDescriptor("mouthWidth", JDroppablePanel.class,
                                  "getMouthWidth", "setMouthWidth");
property.setPreferred(true);
properties.add(property);

// Smile
property = new PropertyDescriptor("smile", JDroppablePanel.class,
                                  "isSmile", "setSmile");
property.setPreferred(true);
properties.add(property);

}

catch (IntrospectionException e) { }

return properties.toArray(new PropertyDescriptor[] {});

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

methods = new String[] { "smileChange",
                         "lineWidthChange",
                         "mouseWidthChange",
                         "backgroundChange",
                         "foregroundChange" };

eventSet = new EventSetDescriptor(JDroppablePanel.class,
                                  "JDroppablePanelListener",
                                  JDroppablePanelListener.class, methods,
                                  "addJDroppablePanelListener",
                                  "removeJDroppablePanelListener");

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

return descriptors.toArray(new EventSetDescriptor[] {});

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
    String res = "/com/rutar/jdroppablepanel/icons/jdroppablepanel/"
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
