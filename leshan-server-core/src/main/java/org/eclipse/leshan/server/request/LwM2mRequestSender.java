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
package org.eclipse.leshan.server.request;

import org.eclipse.leshan.core.request.DownlinkRequest;
import org.eclipse.leshan.core.response.ExceptionConsumer;
import org.eclipse.leshan.core.response.LwM2mResponse;
import org.eclipse.leshan.core.response.ResponseConsumer;
import org.eclipse.leshan.server.client.Client;

public interface LwM2mRequestSender {
    /**
     * Send a Lightweight M2M request synchronously. Will block until a response is received from the remote client.
     */
    <T extends LwM2mResponse> T send(Client destination, DownlinkRequest<T> request);

    /**
     * Send a Lightweight M2M request asynchronously.
     */
    <T extends LwM2mResponse> void send(Client destination, DownlinkRequest<T> request,
            ResponseConsumer<T> responseCallback, ExceptionConsumer errorCallback);
}
