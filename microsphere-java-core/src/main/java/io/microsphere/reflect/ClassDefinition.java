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
package io.microsphere.reflect;

import io.microsphere.annotation.Nonnull;
import io.microsphere.annotation.Nullable;
import io.microsphere.lang.Deprecation;
import io.microsphere.util.Version;

/**
 * The definition class for {@link Class}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @see Class
 * @see ReflectiveDefinition
 * @since 1.0.0
 */
public class ClassDefinition extends ReflectiveDefinition {

    /**
     * @param since     the 'since' version
     * @param className the name of class
     */
    public ClassDefinition(@Nonnull String since, @Nonnull String className) {
        super(since, className);
    }

    /**
     * @param since       the 'since' version
     * @param deprecation the deprecation
     * @param className   the name of class
     */
    public ClassDefinition(@Nonnull String since, @Nullable Deprecation deprecation, @Nonnull String className) {
        super(since, deprecation, className);
    }

    /**
     * @param since     the 'since' version
     * @param className the name of class
     */
    public ClassDefinition(@Nonnull Version since, @Nonnull String className) {
        super(since, className);
    }

    /**
     * @param since       the 'since' version
     * @param deprecation the deprecation
     * @param className   the name of class
     */
    public ClassDefinition(@Nonnull Version since, @Nullable Deprecation deprecation, @Nonnull String className) {
        super(since, deprecation, className);
    }

    @Override
    public final boolean isPresent() {
        return super.getResolvedClass() != null;
    }

}
