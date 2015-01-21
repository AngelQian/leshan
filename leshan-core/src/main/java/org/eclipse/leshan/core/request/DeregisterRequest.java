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
package org.eclipse.leshan.core.request;

import org.eclipse.leshan.core.response.LwM2mResponse;

public class DeregisterRequest implements UplinkRequest<LwM2mResponse> {

    private String registrationID = null;

    public DeregisterRequest(String registrationID) {
        this.registrationID = registrationID;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    @Override
    public void accept(UplinkRequestVisitor visitor) {
        visitor.visit(this);
    }
}
