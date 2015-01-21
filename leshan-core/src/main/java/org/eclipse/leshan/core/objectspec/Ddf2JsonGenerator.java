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
package org.eclipse.leshan.core.objectspec;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.leshan.core.objectspec.json.ObjectSpecSerializer;
import org.eclipse.leshan.core.objectspec.json.ResourceSpecSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Ddf2JsonGenerator {

    private Gson gson;

    public Ddf2JsonGenerator() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ObjectSpec.class, new ObjectSpecSerializer());
        gsonBuilder.registerTypeAdapter(ResourceSpec.class, new ResourceSpecSerializer());
        gsonBuilder.setPrettyPrinting();
        gson = gsonBuilder.create();
    }

    private void generate(Collection<ObjectSpec> objectSpecs, OutputStream output) throws IOException {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output)) {
            gson.toJson(objectSpecs, outputStreamWriter);
        }
    }

    private void generate(File input, OutputStream output) throws IOException {
        // check input exists
        if (!input.exists())
            throw new FileNotFoundException(input.toString());

        // get input files.
        File[] files;
        if (input.isDirectory()) {
            files = input.listFiles();
        } else {
            files = new File[] { input };
        }

        // parse DDF file
        List<ObjectSpec> objectSpecs = new ArrayList<ObjectSpec>();
        DDFFileParser ddfParser = new DDFFileParser();
        for (File f : files) {
            if (f.canRead()) {
                ObjectSpec objectSpec = ddfParser.parse(f);
                if (objectSpec != null) {
                    objectSpecs.add(objectSpec);
                }
            }
        }

        // sort object by id
        Collections.sort(objectSpecs, new Comparator<ObjectSpec>() {
            @Override
            public int compare(ObjectSpec o1, ObjectSpec o2) {
                return o1.id - o2.id;
            }
        });

        // generate json
        generate(objectSpecs, output);
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // default value
        String DDFFilesPath = "ddffiles";
        String outputPath = "src/main/resources/objectspec.json";

        // use arguments if they exit
        if (args.length >= 1)
            DDFFilesPath = args[1]; // the path to a DDF file or a folder which contains DDF files.
        if (args.length >= 2)
            outputPath = args[2]; // the path of the output file.

        // generate object spec file
        Ddf2JsonGenerator ddfJsonGenerator = new Ddf2JsonGenerator();
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputPath)) {
            ddfJsonGenerator.generate(new File(DDFFilesPath), fileOutputStream);
        }
    }
}
