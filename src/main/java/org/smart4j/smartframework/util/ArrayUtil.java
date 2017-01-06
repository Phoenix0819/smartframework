package org.smart4j.smartframework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by lenovo on 2016-12-29.
 */
public final class ArrayUtil {
    public static boolean isNotEmpty(Object[] array) {
        return !ArrayUtils.isEmpty(array);
    }

    private static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }
}
