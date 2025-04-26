package io.microsphere.classloading;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.net.URLClassLoader;

import static io.microsphere.lang.Prioritized.MIN_PRIORITY;
import static io.microsphere.net.URLUtils.EMPTY_URL_ARRAY;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * {@link URLClassPathHandle} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @see URLClassPathHandle
 * @since 1.0.0
 */
public class URLClassPathHandleTest extends BaseURLClassPathHandleTest {

    @Override
    protected URLClassPathHandle createHandle() {
        return new URLClassPathHandle() {
            @Override
            public boolean supports() {
                return false;
            }

            @Override
            public boolean removeURL(ClassLoader classLoader, URL url) {
                return false;
            }
        };
    }

    @Override
    @Test
    public void testSupports() {
        assertFalse(handle.supports());
    }

    @Test
    public void testGetURLs() {
        assertSame(EMPTY_URL_ARRAY, handle.getURLs(null));
        URL[] urls = new URL[0];
        assertArrayEquals(urls, handle.getURLs(new URLClassLoader(urls)));
    }

    @Test
    public void testInitializeLoaders() {
        assertFalse(handle.initializeLoaders(null));
    }

    @Override
    @Test
    public void testGetPriority() {
        assertEquals(MIN_PRIORITY, handle.getPriority());
    }
}