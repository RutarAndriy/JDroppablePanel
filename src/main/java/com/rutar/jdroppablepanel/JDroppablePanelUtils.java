package com.rutar.jdroppablepanel;

import java.io.*;
import java.util.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;

// ............................................................................

/**
 * Клас JDroppablePanelUtils
 * @author Rutar_Andriy
 * 27.02.2024
 */

public class JDroppablePanelUtils {
    
/**
 * Метод дозволяє отримати список файлів із DaD-події
 * @param event подія типу DropTargetDropEvent
 * @return список файлів, які були перетягнуті на панель
 */
public static File[] getDroppableFiles (DropTargetDropEvent event) {

File[] files = null;

event.acceptDrop(DnDConstants.ACTION_COPY);
Transferable transferable = event.getTransferable();
DataFlavor[] flavors = transferable.getTransferDataFlavors();

for (DataFlavor flavor : flavors) {

    try {

        if (flavor.isFlavorJavaFileListType()) {

            files = ((List<File>) transferable
                     .getTransferData(flavor)).toArray(File[]::new);
            
        }
    }
    
    catch (UnsupportedFlavorException | IOException e) { }

}

event.dropComplete(true);
return files;

}

// Кінець класу JDroppablePanelUtils //////////////////////////////////////////

}
