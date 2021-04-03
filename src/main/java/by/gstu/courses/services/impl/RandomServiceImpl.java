package by.gstu.courses.services.impl;

import by.gstu.courses.services.RandomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * createdAt: 11/23/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Service
public class RandomServiceImpl implements RandomService {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";

    @Override
    public String randomPassword(int len) {
        return randomPassword(len, false, false, false, false);
    }

    @Override
    public String randomPasswordUseAll(int len) {
        return randomPassword(len, true, true, true, true);
    }

    @Override
    public String randomPassword(int len, boolean useLower) {
        return randomPassword(len, useLower, false, false, false);
    }

    @Override
    public String randomPassword(int len, boolean useLower, boolean useUpper) {
        return randomPassword(len, useLower, useUpper, false, false);
    }

    @Override
    public String randomPassword(int len, boolean useLower, boolean useUpper, boolean useDigits) {
        return randomPassword(len, useLower, useUpper, useDigits, false);
    }

    @Override
    public String randomPassword(int len, boolean useLower, boolean useUpper, boolean useDigits, boolean usePunctuation) {
        return generatePassword(len, useLower, useUpper, useDigits, usePunctuation);
    }

    @Override
    public String randomLogin() {
        return randomLogin("User");
    }

    @Override
    public String randomLogin(String prefix) {
        return generateLogin(prefix, 10);
    }

    private String generateLogin(String prefix, int len) {
        List<String> charCategories = Arrays.asList(LOWER, UPPER, DIGITS);
        Random random = new Random(System.nanoTime());
        StringBuilder login = new StringBuilder(len);
        login.append(prefix).append("_");

        appendWithCategories(login, len, charCategories, random);

        return new String(login);
    }

    private String generatePassword(int length,
                                   boolean useLower,
                                   boolean useUpper,
                                   boolean useDigits,
                                   boolean usePunctuation) {

        if (length <= 0) {
            return "";
        }

        StringBuilder password = new StringBuilder(length);
        Random random = new Random(System.nanoTime());

        List<String> charCategories = new ArrayList<>(4);
        if (useLower) {
            charCategories.add(LOWER);
        }
        if (useUpper) {
            charCategories.add(UPPER);
        }
        if (useDigits) {
            charCategories.add(DIGITS);
        }
        if (usePunctuation) {
            charCategories.add(PUNCTUATION);
        }

        appendWithCategories(password, length, charCategories, random);

        return new String(password);
    }

    private void appendWithCategories(StringBuilder login, int len, List<String> charCategories, Random random) {
        for (int i = 0; i < len; i++) {
            String charCategory = charCategories.get(random.nextInt(charCategories.size()));
            int position = random.nextInt(charCategory.length());
            login.append(charCategory.charAt(position));
        }
    }
}
