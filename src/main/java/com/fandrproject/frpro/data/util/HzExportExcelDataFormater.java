package com.fandrproject.frpro.data.util;

import java.lang.reflect.Field;

/**
 * Created by sml
 * 2020/09/19 23:36
 */
public interface HzExportExcelDataFormater {
    Object format(Field filed, Object value, Object rowValue);
}
