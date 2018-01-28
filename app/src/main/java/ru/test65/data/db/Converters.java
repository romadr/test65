package ru.test65.data.db;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.Arrays;
import java.util.List;

import ru.test65.utils.AppConstants;

public class Converters {


    public static class ListStringsConverter implements PropertyConverter<List<String>, String> {

        @Override
        public List<String> convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) return null;
            else {
                return Arrays.asList(databaseValue.split(AppConstants.LIST_DB_DELIMITER));
            }
        }

        @Override
        public String convertToDatabaseValue(List<String> entityProperty) {
            if (entityProperty == null) return null;
            else {
                final StringBuilder sb = new StringBuilder();
                for (String link : entityProperty) {
                    sb.append(link);
                    sb.append(AppConstants.LIST_DB_DELIMITER);
                }
                return sb.toString();
            }
        }
    }
}
