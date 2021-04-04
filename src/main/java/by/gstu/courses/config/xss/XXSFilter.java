package by.gstu.courses.config.xss;

import com.mysql.cj.util.StringUtils;
import org.apache.logging.log4j.core.util.IOUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * createdAt: 4/4/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class XXSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final XSSRequestWrapper wrappedRequest = new XSSRequestWrapper((HttpServletRequest)servletRequest);
        final String body = IOUtils.toString(wrappedRequest.getReader());
        if (!StringUtils.isEmptyOrWhitespaceOnly(body)) {
            wrappedRequest.resetInputStream(XSSUtils.stripXSS(body).getBytes());
        }

        filterChain.doFilter(wrappedRequest, servletResponse);
    }
}
