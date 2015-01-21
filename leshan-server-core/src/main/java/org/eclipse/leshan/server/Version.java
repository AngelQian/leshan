/*******************************************************************************
 * Copyright (c) 2013-2015 Sierra Wireless and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sierra Wireless - initial API and implementation
 *******************************************************************************/
package org.eclipse.leshan.server;

public class Version {
    public static String value;
    
    public final static String getValue(){
        if (value == null)
        {
            Package p = Version.class.getPackage();
            String version = p.getImplementationVersion();
            if (version != null)
                value =  version;
            else
                value =  "unknown";    
        }
        return value;
    }
}
