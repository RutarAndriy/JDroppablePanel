package com.rutar.jdroppablepanel;

import java.util.*;

// ............................................................................

/**
 * Клас JDroppablePanelListener
 * @author Rutar_Andriy
 * 16.02.2024
 */

public interface JDroppablePanelListener extends EventListener {
    
/**
 * Зміна усмішки
 * @param evt Подія типу JDroppablePanelEvent
 */
public void smileChange (JDroppablePanelEvent evt);
    
/**
 * Зміна ширини ліній
 * @param evt Подія типу JDroppablePanelEvent
 */
public void lineWidthChange (JDroppablePanelEvent evt);

/**
 * Зміна ширини усмішки (в градусах)
 * @param evt Подія типу JDroppablePanelEvent
 */
public void mouthWidthChange (JDroppablePanelEvent evt);

/**
 * Зміна кольору фону
 * @param evt Подія типу JDroppablePanelEvent
 */
public void backgroundChange (JDroppablePanelEvent evt);

/**
 * Зміна кольору ліній
 * @param evt Подія типу JDroppablePanelEvent
 */
public void foregroundChange (JDroppablePanelEvent evt);

// Кінець класу JDroppablePanelListener ///////////////////////////////////////

}
