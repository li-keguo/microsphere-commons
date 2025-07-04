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

package io.microsphere.annotation.processor;

import io.microsphere.annotation.processor.model.ConfigurationPropertyModel;
import io.microsphere.classloading.ManifestArtifactResourceResolver;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * {@link ConfigurationPropertyAnnotationProcessor} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ConfigurationPropertyAnnotationProcessor
 * @since 1.0.0
 */
public class ConfigurationPropertyAnnotationProcessorTest extends AbstractAnnotationProcessingTest {

    @Override
    protected void beforeTest() {
        super.beforeTest();
    }

    @Override
    protected void addCompiledClasses(Set<Class<?>> compiledClasses) {
        compiledClasses.add(ConfigurationPropertyModel.class);
        compiledClasses.add(ManifestArtifactResourceResolver.class);
    }

    @Test
    public void test() {
    }
}
