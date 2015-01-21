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
package org.eclipse.leshan.standalone.servlet.json;

import java.lang.reflect.Type;

import org.eclipse.leshan.core.response.LwM2mResponse;
import org.eclipse.leshan.core.response.ValueResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ResponseSerializer implements JsonSerializer<LwM2mResponse> {

    @Override
    public JsonElement serialize(final LwM2mResponse src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject element = new JsonObject();

        element.addProperty("status", src.getCode().toString());

        if (typeOfSrc == ValueResponse.class) {
            element.add("content", context.serialize(((ValueResponse) src).getContent()));
        }

        return element;
    }
}
