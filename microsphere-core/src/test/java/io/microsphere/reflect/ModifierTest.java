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

import org.junit.jupiter.api.Test;

import static io.microsphere.reflect.FieldUtils.getStaticFieldValue;
import static org.junit.Assert.assertEquals;

/**
 * {@link Modifier} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class ModifierTest {

    @Test
    public void testGetValue() {
        for (Modifier modifier : Modifier.values()) {
            assertEquals(modifier.getValue(), findModifierValue(modifier.name()));
        }
    }

    private int findModifierValue(String name) {
        return getStaticFieldValue(java.lang.reflect.Modifier.class, name);
    }
}