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
 * Зміна доступності моніторингу DaD-події
 * @param evt Подія типу JDroppablePanelEvent
 */ 
public void activeChange (JDroppablePanelEvent evt);

/**
 * Зміна видимості I візерунку
 * @param evt Подія типу JDroppablePanelEvent
 */
public void firstLineDrawChange (JDroppablePanelEvent evt);

/**
 * Зміна видимості II візерунку
 * @param evt Подія типу JDroppablePanelEvent
 */
public void secondtLineDrawChange (JDroppablePanelEvent evt);
            
/**
 * Зміна кольору I візерунку
 * @param evt Подія типу JDroppablePanelEvent
 */
public void firstLineColorChange (JDroppablePanelEvent evt);

/**
 * Зміна кольору II візерунку
 * @param evt Подія типу JDroppablePanelEvent
 */
public void secondLineColorChange (JDroppablePanelEvent evt);

/**
 * Зміна типу ліній I візерунку
 * @param evt Подія типу JDroppablePanelEvent
 */
public void firstLineStrokeChange (JDroppablePanelEvent evt);

/**
 * Зміна типу ліній II візерунку
 * @param evt Подія типу JDroppablePanelEvent
 */
public void secondLineStrokeChange (JDroppablePanelEvent evt);

/**
 * Зміна активної рамки
 * @param evt Подія типу JDroppablePanelEvent
 */
public void activeBorderChange (JDroppablePanelEvent evt);

/**
 * Зміна неактивної рамки
 * @param evt Подія типу JDroppablePanelEvent
 */
public void passiveBorderChange (JDroppablePanelEvent evt);

/**
 * Зміна кроку ліній
 * @param evt Подія типу JDroppablePanelEvent
 */
public void lineStepChange (JDroppablePanelEvent evt);

/**
 * Зміна відступу по краях елемента
 * @param evt Подія типу JDroppablePanelEvent
 */
public void lineIndentChange (JDroppablePanelEvent evt);

// Кінець класу JDroppablePanelListener ///////////////////////////////////////

}
