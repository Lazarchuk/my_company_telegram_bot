package ua.company.static_data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Affiliates {
    private static Map<String, String> affiliates = new LinkedHashMap<>();

    static {
        affiliates.put("affiliate1", "Філія1. Контакти філії1...");
        affiliates.put("affiliate2", "Філія2. Контакти філії2...");
        affiliates.put("affiliate3", "Філія3. Контакти філії3...");
        affiliates.put("affiliate4", "Філія4. Контакти філії4...");
    }

    public static Map<String, String> getAffiliates(){
        return affiliates;
    }
}
