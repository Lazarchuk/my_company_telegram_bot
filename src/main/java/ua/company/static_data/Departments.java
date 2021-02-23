package ua.company.static_data;

import java.util.LinkedHashMap;
import java.util.Map;

public class Departments {
    private static Map<String, String> departments= new LinkedHashMap<>();
    private static String addressCAU = "м. Київ, вул. Хрещатик, 22 (Головпоштампт). Відділ кадрів знаходиться...";

    static {
        departments.put("department1", "Відділення1. Контакти віддлення1...");
        departments.put("department2", "Відділення2. Контакти віддлення2...");
    }

    public static String getAddressCAU() {
        return addressCAU;
    }

    public static Map<String, String> getDepartments() {
        return departments;
    }
}
