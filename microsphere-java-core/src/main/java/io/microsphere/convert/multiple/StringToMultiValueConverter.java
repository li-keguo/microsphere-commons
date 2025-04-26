/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.microsphere.convert.multiple;


import static io.microsphere.constants.SymbolConstants.COMMA_CHAR;
import static io.microsphere.util.ArrayUtils.length;
import static io.microsphere.util.StringUtils.split;

/**
 * The class to convert {@link String} to multiple value object
 *
 * @see MultiValueConverter
 * @since 1.0.0
 */
public interface StringToMultiValueConverter extends MultiValueConverter<String> {

    @Override
    default Object convert(String source, Class<?> multiValueType, Class<?> elementType) {

        if (source == null) {
            return null;
        }

        // split by the comma
        String[] segments = split(source, COMMA_CHAR);

        int size = length(segments);

        return convert(segments, size, multiValueType, elementType);
    }

    /**
     * Convert the segments to multiple value object
     *
     * @param segments    the String array of content
     * @param size        the size of multiple value object
     * @param targetType  the target type
     * @param elementType the element type
     * @return multiple value object
     */
    Object convert(String[] segments, int size, Class<?> targetType, Class<?> elementType);
}
