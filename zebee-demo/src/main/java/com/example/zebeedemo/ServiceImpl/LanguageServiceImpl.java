package com.example.zebeedemo.ServiceImpl;

import com.example.zebeedemo.Services.LanguageService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Override
    public String getResourceBundleValue(String lang, String key) {
        List<String> items = new ArrayList<>();
        String language = "";
        String country = "";
        Locale locale = null;
        if (lang.contains("-")) {
            items = Arrays.asList(lang.split("\\s*-\\s*"));
            language = items.get(0);
            country = items.get(1);
            locale = new Locale(language, country);
        } else {
            items.add(lang);
            language = items.get(0);
            locale = new Locale(language);
        }

        ResourceBundle exampleBundle = ResourceBundle.getBundle("i18n/messages", locale);
        return exampleBundle.getString(key);
    }
}
