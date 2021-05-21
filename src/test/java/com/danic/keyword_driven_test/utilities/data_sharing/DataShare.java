package com.danic.keyword_driven_test.utilities.data_sharing;

import java.util.Map;

public interface DataShare {
    String getResult(String key);
    void addOperationResult(String objectName, String result);
}
