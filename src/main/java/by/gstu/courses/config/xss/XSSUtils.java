package by.gstu.courses.config.xss;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.owasp.esapi.ESAPI;

/**
 * createdAt: 4/4/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public final class XSSUtils {

    private XSSUtils() { }

    public static String stripXSS(String value) {
        if (value == null) {
            return null;
        }

        final String encoded = ESAPI.encoder()
                .canonicalize(value)
                .replace("\0", "");
        return Jsoup.clean(encoded, Whitelist.none());
    }
}
