package org.example.app.utils.validator;

// Валидация телефона
public class PriceValidator {

    // Regex for phone number xxx xxx-xxxx
    private final static String PHONE_RGX = "^\\d+(\\.\\d{1,2})?$";

    private PriceValidator() {
    }

    public static boolean isPhoneValid(String phone) {
        return phone.isEmpty() || !phone.matches(PHONE_RGX);
    }
}
