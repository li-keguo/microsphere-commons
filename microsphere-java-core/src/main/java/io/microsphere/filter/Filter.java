/**
 *
 */
package io.microsphere.filter;

/**
 * {@link Filter} interface
 *
 * @param <T> the type of Filtered object
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @see Filter
 * @since 1.0.0
 */
public interface Filter<T> {

    /**
     * Does accept filtered object?
     *
     * @param filteredObject filtered object
     * @return
     */
    boolean accept(T filteredObject);
}
