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

public JDroppablePanelEvent (Object source, Object oldValue, Object newValue) {

super(source, JDROPPABLEPANEL_EVENT);

this.oldValue = oldValue;
this.newValue = newValue;

}

///////////////////////////////////////////////////////////////////////////////

public Object getOldValue() { return oldValue; }
public Object getNewValue() { return newValue; }

///////////////////////////////////////////////////////////////////////////////

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
