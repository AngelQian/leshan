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

import org.eclipse.leshan.client.resource.multiple.MultipleLwM2mResource;

public class MultipleResourceDefinition implements LwM2mClientResourceDefinition
{
   private final int id;
   private final MultipleLwM2mResource multipleResource;
   private final boolean required;

   public MultipleResourceDefinition(final int id, final MultipleLwM2mResource multipleResource,
                                     final boolean required)
   {
      this.id = id;
      this.multipleResource = multipleResource;
      this.required = required;
   }

   @Override
   public int getId()
   {
      return id;
   }

   @Override
   public boolean isRequired()
   {
      return required;
   }

   @Override
   public LwM2mClientResource createResource()
   {
      return multipleResource;
   }
}
