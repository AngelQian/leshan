/*******************************************************************************
 * Copyright (c) 2013-2015 Sierra Wireless and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Zebra Technologies - initial API and implementation
 *******************************************************************************/
package org.eclipse.leshan.client.resource;

import org.eclipse.californium.core.CoapResource;

public abstract class ClientResourceBase extends CoapResource implements LinkFormattable, ClientObservable {
    protected static final int IS_OBSERVE = 0;

    public ClientResourceBase(final Integer id) {
        super(Integer.toString(id));
        setObservable(true);
    }

    @Override
    public void notifyObserverRelations() {
        super.notifyObserverRelations();
    }

}
