/*****************************************************************************
 * Copyright (C) The Apache Software Foundation. All rights reserved.        *
 * ------------------------------------------------------------------------- * 
 * This software is published under the terms of the Apache Software License * 
 * version 1.1, a copy of which has been included with this distribution in  * 
 * the LICENSE file.                                                         * 
 *****************************************************************************/

package org.apache.batik.gvt;

import java.awt.geom.Rectangle2D;
import java.awt.image.renderable.RenderableImage;

/**
 * Describes a mask.
 *
 * @author <a href="mailto:Thierry.Kormann@sophia.inria.fr">Thierry Kormann</a>
 * @version $Id$
 */
public interface Mask extends RenderableImage {
    /**
     * Returns the bounds of this mask
     */
    public Rectangle2D getBounds2D();
}
