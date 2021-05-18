package com.danic.keyword_driven_test.operations;

import java.io.*;
import java.util.Properties;

public class ReadObjects {
    private Properties prop;

    public Properties getObjects() throws IOException {
        prop = new Properties();
        InputStream stream =
                new FileInputStream(new File("com/danic/keyword_driven_test/objects/object.properties"));

        prop.load(stream);
        return prop;
    }
}
