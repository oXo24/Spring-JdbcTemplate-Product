package org.example.app.utils.validator;

public class EmailValidator {

    private final static String EMAIL_RGX =
            "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    private EmailValidator() {
    }

    public static boolean isEmailValid(String email) {
        return email.isEmpty() || !email.matches(EMAIL_RGX);
    }
}
