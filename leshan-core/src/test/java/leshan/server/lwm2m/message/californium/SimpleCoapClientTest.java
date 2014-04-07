/*
 * Copyright (c) 2013, Sierra Wireless
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright notice,
 *       this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright notice,
 *       this list of conditions and the following disclaimer in the documentation
 *       and/or other materials provided with the distribution.
 *     * Neither the name of {{ project }} nor the names of its contributors
 *       may be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package leshan.server.lwm2m.message.californium;

import static org.mockito.Mockito.mock;
import leshan.server.lwm2m.message.OperationType;
import leshan.server.lwm2m.message.RequestTimeoutException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.ethz.inf.vs.californium.coap.Request;
import ch.ethz.inf.vs.californium.network.Endpoint;

public class SimpleCoapClientTest {

    SimpleCoapClient endpoint;

    @Before
    public void setUp() throws Exception {

        Endpoint coapEndpoint = mock(Endpoint.class);
        this.endpoint = new SimpleCoapClient(coapEndpoint, 50);
    }

    @Test(expected = RequestTimeoutException.class)
    public void testSendRequestThrowsRequestTimeoutException() throws Exception {
        Request request = Request.newGet();
        this.endpoint.send(request, OperationType.READ);
        Assert.fail("Request should have timed out with exception");
    }

}
