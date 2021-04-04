package by.gstu.courses.config.xss;

import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * createdAt: 4/4/2021
 * project: CourseSaleServer
 *
 * @author Alexander Petrushkin
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {

    private byte[] cachedBody;

    public XSSRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        final ServletInputStream inputStream = request.getInputStream();
        cachedBody = StreamUtils.copyToByteArray(inputStream);
    }

    @Override
    public ServletInputStream getInputStream() {
        return new CachedBodyServletInputStream(cachedBody);
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(cachedBody)));
    }

    @Override
    public String[] getParameterValues(String name) {
        final String[] parameterValues = super.getParameterValues(name);
        String[] encodedValues = null;

        if (parameterValues != null) {
            final int count = parameterValues.length;
            encodedValues = new String[count];
            for (int i = 0; i < count; i++) {
                encodedValues[i] = stripXSS(parameterValues[i]);
            }
        }

        return encodedValues;
    }

    @Override
    public String getParameter(String name) {
        return stripXSS(super.getParameter(name));
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        final List<String> result = new ArrayList<>();
        final Enumeration<String> headers = super.getHeaders(name);
        while (headers.hasMoreElements()) {
            final String header = headers.nextElement();
            final String[] tokens = header.split(",");
            for (String token : tokens) {
                result.add(stripXSS(token));
            }
        }
        return Collections.enumeration(result);
    }

    public void resetInputStream(byte[] bytes) {
        this.cachedBody = bytes;
    }

    private String stripXSS(String value) {
        return XSSUtils.stripXSS(value);
    }

    public static class CachedBodyServletInputStream extends ServletInputStream {
        private final ByteArrayInputStream cachedBodyInputStream;

        public CachedBodyServletInputStream(byte[] cachedBody) {
            this.cachedBodyInputStream = new ByteArrayInputStream(cachedBody);
        }

        @Override
        public boolean isFinished() {
            return cachedBodyInputStream.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int read() {
            return cachedBodyInputStream.read();
        }
    }
}
