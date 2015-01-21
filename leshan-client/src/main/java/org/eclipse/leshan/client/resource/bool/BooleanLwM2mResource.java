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
package org.eclipse.leshan.client.resource.bool;

import org.eclipse.leshan.client.exchange.LwM2mExchange;
import org.eclipse.leshan.client.resource.BaseTypedLwM2mResource;

public class BooleanLwM2mResource extends BaseTypedLwM2mResource<BooleanLwM2mExchange> {

    @Override
    protected BooleanLwM2mExchange createSpecificExchange(final LwM2mExchange exchange) {
        return new BooleanLwM2mExchange(exchange);
    }

}
