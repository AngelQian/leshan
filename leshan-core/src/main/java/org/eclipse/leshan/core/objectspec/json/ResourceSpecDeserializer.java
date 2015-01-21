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
package org.eclipse.leshan.core.objectspec.json;

import org.eclipse.leshan.core.objectspec.ResourceSpec;
import org.eclipse.leshan.core.objectspec.ResourceSpec.Operations;
import org.eclipse.leshan.core.objectspec.ResourceSpec.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ResourceSpecDeserializer implements JsonDeserializer<ResourceSpec> {

    @Override
    public ResourceSpec deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        if (json == null)
            return null;

        if (!json.isJsonObject())
            return null;

        JsonObject jsonObject = json.getAsJsonObject();
        if (!jsonObject.has("id"))
            return null;

        int id = jsonObject.get("id").getAsInt();
        String name = jsonObject.get("name").getAsString();
        Operations operations = Operations.valueOf(jsonObject.get("operations").getAsString());
        String instancetype = jsonObject.get("instancetype").getAsString();
        boolean mandatory = jsonObject.get("mandatory").getAsBoolean();
        Type type = Type.valueOf(jsonObject.get("type").getAsString().toUpperCase());
        String range = jsonObject.get("range").getAsString();
        String units = jsonObject.get("units").getAsString();
        String description = jsonObject.get("description").getAsString();

        return new ResourceSpec(id, name, operations, "multiple".equals(instancetype), mandatory, type, range, units,
                description);
    }
}
