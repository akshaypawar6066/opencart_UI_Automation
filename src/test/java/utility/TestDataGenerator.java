package utility;

import java.util.Random;

public class TestDataGenerator {

    private static final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final Random random = new Random();

    // Generate random alphabetic string
    private static String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        }
        return sb.toString();
    }

    // Random First Name
    public static String getFirstName() {
        return randomString(6); // e.g., "mavito"
    }

    // Random Last Name
    public static String getLastName() {
        return randomString(7); // e.g., "saperni"
    }

    // Random Email
    public static String getEmail() {
        return randomString(8) + random.nextInt(9999) + "@gmail.com";
    }

    // Random 10-digit Phone Number
    public static String getPhone() {
        StringBuilder phone = new StringBuilder("9"); // start with 9
        for (int i = 0; i < 9; i++) {
            phone.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        }
        return phone.toString();
    }

    // Random Password (Uppercase + lowercase + digits)
    public static String getPassword() {
        return ALPHABETS.charAt(random.nextInt(ALPHABETS.length())) +   // Uppercase
               randomString(5) +                                       // Lowercase
               NUMBERS.charAt(random.nextInt(NUMBERS.length()));       // Number
    }
}
