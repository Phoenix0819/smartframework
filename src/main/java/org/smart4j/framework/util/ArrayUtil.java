package org.smart4j.framework.util;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;

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
