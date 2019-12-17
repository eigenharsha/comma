package org.comma.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Utils {

    private static List<Pattern> whiteListPattern = new LinkedList<>();

    /**
     * objects [key, value, key, value],
     * convert objects array to object map.
     *
     * @param keyType
     * @param valueType
     * @param entries
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }

    /**
     * text "key, value, key, value",
     * convert string value to map.
     *
     * @param text
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> toMap(String text) throws IOException {
        Map map = new HashMap();
        try {
            map = new ObjectMapper().readValue(text, new TypeReference<Map<String, String>>() {
            });
        } catch (IOException e) {
            throw e;
        }

        return map;
    }

    public static boolean isNull(@Nullable Object object) {
        if(object == null)
            return true;

        return false;
    }

    public static boolean isNotNull(@Nullable Object object) {
        return  !isNull(object);
    }

    public static boolean isNullorEmpty(@Nullable Object object) {
        if(object == null)
            return true;

        if(object instanceof String)
            return StringUtils.isEmpty(object);

        return false;
    }

    public static String join(String[] array) {
        return array == null ? "" : join((Collection)Arrays.asList(array));
    }

    public static String join(String[] array, char separator) {
        return array == null ? "" : join((Collection)Arrays.asList(array), separator);
    }

    public static String join(Collection<String> collection) {
        return join(collection, ',');
    }

    public static String join(Collection<String> collection, char separator) {
        if (collection != null && !collection.isEmpty()) {
            StringBuilder result = new StringBuilder();
            join((Iterable)collection, separator, result);
            return result.toString();
        } else {
            return "";
        }
    }

    public static void join(Iterable<String> iterable, char separator, StringBuilder sb) {
        join(iterable, separator, (x) -> {
            return x;
        }, sb);
    }

    public static <T> void join(Iterable<T> iterable, char separator, Function<T, String> function, StringBuilder sb) {
        if (iterable != null) {
            boolean first = true;

            Object value;
            for(Iterator var5 = iterable.iterator(); var5.hasNext(); sb.append((String)function.apply((T) value))) {
                value = var5.next();
                if (first) {
                    first = false;
                } else {
                    sb.append(separator);
                }
            }
        }
    }

    public static void requireNonNullandNotEmpty(String param, String s) {

    }
}
