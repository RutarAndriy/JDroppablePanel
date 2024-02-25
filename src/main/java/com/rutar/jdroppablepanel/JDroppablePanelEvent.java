package com.rutar.jdroppablepanel;

import java.awt.*;

// ............................................................................

/**
 * Клас JDroppablePanelEvent
 * @author Rutar_Andriy
 * 16.02.2024
 */

public class JDroppablePanelEvent extends AWTEvent {

private final Object oldValue;
private final Object newValue;

/** Ідентифікатор події JDROPPABLEPANEL_EVENT */
public static int JDROPPABLEPANEL_EVENT = AWTEvent.RESERVED_ID_MAX + 333;

///////////////////////////////////////////////////////////////////////////////

/**
 * Констуктор класу JDroppablePanelEvent
 * @param source об'єкт, у якого змінилася властивість
 * @param oldValue старе значення властивості
 * @param newValue нове значення властивості
 */
public JDroppablePanelEvent (Object source, Object oldValue, Object newValue) {

super(source, JDROPPABLEPANEL_EVENT);

this.oldValue = oldValue;
this.newValue = newValue;

}

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод повертає старе значення властивості
 * @return старе значення властивості
 */
public Object getOldValue() { return oldValue; }

/**
 * Метод повертає нове значення властивості
 * @return нове значення властивості
 */
public Object getNewValue() { return newValue; }

///////////////////////////////////////////////////////////////////////////////

/**
 * Метод повертає строкове значення даного об'єкту
 * @return строкове значення об'єкту
 */
@Override
public String toString() {

    return getClass().getName() +
        "[" +
            "oldValue=" + oldValue + "; " +
            "newValue=" + newValue +
        "]";
}

// Кінець класу JDroppablePanelEvent //////////////////////////////////////////

}
