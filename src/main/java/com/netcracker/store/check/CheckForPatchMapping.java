package com.netcracker.store.check;

import com.netcracker.store.dto.ProductDto;
import com.netcracker.store.exeption.NotFoundException;
import com.netcracker.store.exeption.ResponseInputException;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CheckForPatchMapping {

    public Map<String, String> validateObject(Object object) throws IllegalAccessException, NotFoundException, ResponseInputException {
        Field[] fields = object.getClass().getDeclaredFields();
        Map<String, String> mapResult = new HashMap<>();
        //fields[0].setAccessible(true);
        //String id=fields[0].get(object).toString();
        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Object obj = fields[i].get(object);
            Object validNull=0;
            String s="";
            if (fields[i].get(object) == null)
                continue;
            if((fields[i].get(object).equals(validNull)))
                continue;
            mapResult.put(fields[i].getName(), obj.toString());
        }

        return mapResult;
    }
}
