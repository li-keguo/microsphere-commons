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

import io.microsphere.annotation.ConfigurationProperty;
import io.microsphere.classloading.ManifestArtifactResourceResolver;
import org.junit.jupiter.api.Test;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import java.util.List;
import java.util.Set;

import static io.microsphere.annotation.processor.util.AnnotationUtils.getAnnotation;
import static io.microsphere.annotation.processor.util.FieldUtils.getDeclaredFields;
import static io.microsphere.annotation.processor.util.LoggerUtils.trace;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * {@link AbstractAnnotationProcessingTest} for
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @see ConfigurationProperty
 * @since 1.0.0
 */
public class ConfigurationPropertyProcessingTest extends AbstractAnnotationProcessingTest {

    @Override
    protected void addCompiledClasses(Set<Class<?>> compiledClasses) {
        compiledClasses.add(ManifestArtifactResourceResolver.class);
    }

    @Test
    public void test() {
        TypeElement type = getTypeElement(ManifestArtifactResourceResolver.class);
        List<VariableElement> fields = getDeclaredFields(type);
        assertNotNull(fields);
        for (VariableElement field : fields) {
            AnnotationMirror annotation = getAnnotation(field, ConfigurationProperty.class);
            if (annotation != null) {
                trace("Field[name : '{}' , constant value : '{}' , annotation[type : '{}' , attributes : {}]",
                        field.getSimpleName(),
                        field.getConstantValue(),
                        annotation.getAnnotationType(),
                        annotation.getElementValues()
                );
            }
        }
    }
}
