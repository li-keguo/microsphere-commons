package io.microsphere.text;

import io.microsphere.util.BaseUtils;

import static io.microsphere.util.StringUtils.isEmpty;

/**
 * The utility class of text format
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @since 1.0.0
 */
public abstract class FormatUtils extends BaseUtils {

    public static final String DEFAULT_PLACEHOLDER = "{}";

    public static String format(String pattern, Object... args) {
        return formatWithPlaceholder(pattern, DEFAULT_PLACEHOLDER, args);
    }

    public static String formatWithPlaceholder(String pattern, String placeholder, Object... args) {
        if (isEmpty(pattern)) {
            return pattern;
        }
        int offset = placeholder.length();
        int argsLength = args == null ? 0 : args.length;
        if (argsLength == 0) {
            return pattern;
        }
        StringBuilder stringBuilder = new StringBuilder(pattern);
        int index = -1;
        for (int i = 0; i < argsLength; i++) {
            index = stringBuilder.indexOf(placeholder);
            if (index == -1) {
                break;
            }
            String value = String.valueOf(args[i]);
            stringBuilder.replace(index, index + offset, value);
        }
        return stringBuilder.toString();
    }
}
