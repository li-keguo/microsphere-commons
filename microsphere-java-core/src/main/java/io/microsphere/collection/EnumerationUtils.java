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
package io.microsphere.collection;

import io.microsphere.util.Utils;

import java.util.Collections;
import java.util.Enumeration;

/**
 * The utilities class for Java {@link Enumeration}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Enumeration
 * @see Collections#enumeration
 * @since 1.0.0
 */
public abstract class EnumerationUtils implements Utils {

    /**
     * Create a {@link Enumeration} instance from the specified elements
     *
     * @param elements the specified elements
     * @param <E>      the type of element
     * @return non-null
     */
    public static <E> Enumeration<E> of(E... elements) {
        return ofEnums(elements);
    }

    /**
     * Create a {@link Enumeration} instance from the specified elements
     *
     * @param elements the specified elements
     * @param <E>      the type of element
     * @return non-null
     */
    public static <E> Enumeration<E> ofEnums(E... elements) {
        return new ArrayEnumeration<>(elements);
    }

    private EnumerationUtils() {
    }
}
