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
package org.eclipse.leshan.server.bootstrap;

import org.eclipse.leshan.server.security.SecurityStore;

/**
 * 
 * A Lightweight M2M server in charge of handling device bootstrap on the /bs resource.
 *
 */
public interface LwM2mBootstrapServer {

    /**
     * Access to the bootstrap configuration store. It's used for sending configuration to the devices initiating a
     * bootstrap.
     */
    BootstrapStore getBoostrapStore();

    /**
     * security store used for DTLS authentication on the bootstrap ressource.
     */
    SecurityStore getSecurityStore();

    void start();

    void stop();

}
