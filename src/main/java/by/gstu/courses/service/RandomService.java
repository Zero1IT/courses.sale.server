package by.gstu.courses.service;

/**
 * createdAt: 11/23/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
public interface RandomService {
    String randomPassword(int len);
    String randomPasswordUseAll(int len);
    String randomPassword(int len, boolean useLower);
    String randomPassword(int len, boolean useLower, boolean useUpper);
    String randomPassword(int len, boolean useLower, boolean useUpper, boolean useDigits);
    String randomPassword(int len, boolean useLower, boolean useUpper, boolean useDigits, boolean usePunctuation);

    String randomLogin();
    String randomLogin(String prefix);
}
