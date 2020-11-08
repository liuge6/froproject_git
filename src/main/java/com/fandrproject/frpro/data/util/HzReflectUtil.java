package com.fandrproject.frpro.data.util;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by sml
 * 2020/09/19 23:33
 */
public class HzReflectUtil {
    public static String CACHE_REFLECT_NAME = "reflectClass";

    public HzReflectUtil() {
    }

    public static Field[] fieldFormat(Class<?> cls, String[] fieldList) throws NoSuchFieldException, SecurityException {
        if (fieldList != null && fieldList.length != 0) {
            int _length = 0;
            String[] var3 = fieldList;
            int _index = fieldList.length;

            for(int var5 = 0; var5 < _index; ++var5) {
                String fieldName = var3[var5];
                if (fieldName != null && fieldName.trim().length() > 0) {
                    ++_length;
                }
            }

            Field[] result = new Field[_length];
            _index = 0;
            String[] var11 = fieldList;
            int var12 = fieldList.length;

            for(int var7 = 0; var7 < var12; ++var7) {
                String fieldName = var11[var7];
                if (fieldName != null && fieldName.trim().length() != 0) {
                    if (fieldName.contains(".")) {
                        Class<?> clsType = cls.getDeclaredField(fieldName.split("\\.")[0]).getType();
                        result[_index] = clsType.getDeclaredField(fieldName.split("\\.")[1]);
                        ++_index;
                    } else {
                        result[_index] = cls.getDeclaredField(fieldName);
                        ++_index;
                    }
                }
            }

            return result;
        } else {
            return null;
        }
    }

    public static Class<?> getFieldType(Field field) {
        String typeName = field.getType().getSimpleName();
        if (typeName.equalsIgnoreCase("string")) {
            return String.class;
        } else if (typeName.equalsIgnoreCase("int")) {
            return Integer.TYPE;
        } else if (typeName.equals("Integer")) {
            return Integer.class;
        } else if (typeName.equalsIgnoreCase("double")) {
            return Double.class;
        } else if (typeName.equalsIgnoreCase("float")) {
            return Float.class;
        } else if (typeName.equals("long")) {
            return Long.TYPE;
        } else if (typeName.equals("Long")) {
            return Long.class;
        } else if (typeName.equalsIgnoreCase("timestamp")) {
            return Timestamp.class;
        } else if (typeName.equalsIgnoreCase("date")) {
            return Date.class;
        } else if (typeName.equalsIgnoreCase("byte[]")) {
            return byte[].class;
        } else if (typeName.equalsIgnoreCase("boolean")) {
            return Boolean.TYPE;
        } else {
            return typeName.equalsIgnoreCase("list") ? List.class : null;
        }
    }
}
