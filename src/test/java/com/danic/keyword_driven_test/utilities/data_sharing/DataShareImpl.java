package com.danic.keyword_driven_test.utilities.data_sharing;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to share data between the UIOperations and the test
 * cases in order to make assertions.
 */
public class DataShareImpl implements DataShare{
    Map<String, String> resultMap;

    public DataShareImpl(){
        resultMap = new HashMap<String, String>();
    }

    /**
     * Gets the text value from the web element stored in the map
     * @param key is the name of the web element
     * @return
     */
    @Override
    public String getResult(String key) {
        return resultMap.get(key);
    }

    /**
     * Saves the web element's name and its text value
     * @param objectName the web element
     * @param result its value (from webElement.getText())
     */
    @Override
    public void addOperationResult(String objectName, String result) {
        resultMap.put(objectName, result);
    }
}
