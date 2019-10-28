package com.example.surveymanager.service.impl;

import com.example.surveymanager.model.Poll;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Map;

class PollServiceUtils {


    static String getField(String columnNameFromReq) {
        for (Field field : Poll.class.getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            String columnNameFromDB = column.name();
            if (columnNameFromReq.equals(columnNameFromDB)) {
                return field.getName();
            }
        }
        return null;
    }

    static Specification<Poll> getSpecifications(Map<String, Object> filters) {
        Specification<Poll> specification = null;
        int step = 0;
        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String needName = PollServiceUtils.getField(key);
            if (needName == null) continue;
            if (step == 0) {
                specification = Specification.where(setSpecification(needName, value.toString()));
            } else {
                specification.and(setSpecification(needName, value.toString()));
            }
            step++;
        }
        return specification;
    }

    private static Specification<Poll> setSpecification(String columnName, String expression) {
        return (root, query, builder) -> builder.like(root.get(columnName), contains(expression));
    }

    private static String contains(String expression) {
        return MessageFormat.format("%{0}%", expression);
    }
}
