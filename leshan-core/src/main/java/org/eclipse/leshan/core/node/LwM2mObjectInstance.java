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
package org.eclipse.leshan.core.node;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.leshan.util.Validate;

/**
 * An instance of {@link LwM2mObject}.
 */
public class LwM2mObjectInstance implements LwM2mNode {

    private final int id;

    private final Map<Integer, LwM2mResource> resources;

    public LwM2mObjectInstance(int id, LwM2mResource[] resources) {
        Validate.notNull(resources);

        this.id = id;
        this.resources = new HashMap<>(resources.length);
        for (LwM2mResource resource : resources) {
            this.resources.put(resource.getId(), resource);
        }
    }

    @Override
    public void accept(LwM2mNodeVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Returns a map of resources by id.
     *
     * @return the resources
     */
    public Map<Integer, LwM2mResource> getResources() {
        return Collections.unmodifiableMap(resources);
    }

    @Override
    public String toString() {
        return String.format("LwM2mObjectInstance [id=%s, resources=%s]", id, resources);
    }

    public String prettyPrint() {
        StringBuilder builder = new StringBuilder();
        builder.append("LwM2mObjectInstance [id=").append(id).append("]");
        for (LwM2mResource r : resources.values()) {
            builder.append("\n\t").append(r);
        }
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((resources == null) ? 0 : resources.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LwM2mObjectInstance other = (LwM2mObjectInstance) obj;
        if (id != other.id) {
            return false;
        }
        if (resources == null) {
            if (other.resources != null) {
                return false;
            }
        } else if (!resources.equals(other.resources)) {
            return false;
        }
        return true;
    }

}
