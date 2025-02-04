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
package io.microsphere.util;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import static io.microsphere.util.AnnotationUtils.CALLER_SENSITIVE_ANNOTATION_CLASS;
import static io.microsphere.util.AnnotationUtils.CALLER_SENSITIVE_ANNOTATION_CLASS_NAME;
import static io.microsphere.util.AnnotationUtils.getDeclaredAnnotations;
import static io.microsphere.util.AnnotationUtils.isAnnotationPresent;
import static io.microsphere.util.AnnotationUtils.isCallerSensitivePresent;
import static io.microsphere.util.AnnotationUtils.isMetaAnnotation;
import static io.microsphere.util.ClassLoaderUtils.isPresent;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@link AnnotationUtils} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class AnnotationUtilsTest {

    @Test
    public void testIsAnnotatedPresent() {
        assertTrue(isAnnotationPresent(B.class, DataAccess.class));
        assertTrue(isAnnotationPresent(A.class, DataAccess.class));
        assertFalse(isAnnotationPresent(A.class, Monitored.class));
    }

    @Test
    public void testIsMetaAnnotation() {
        assertTrue(isMetaAnnotation(Monitored.class, ServiceMode.class));
        assertTrue(isMetaAnnotation(DataAccess.class, ServiceMode.class));
    }

    @Test
    public void testGetDeclaredAnnotations() {
        List<Annotation> annotations = getDeclaredAnnotations(B.class);
        assertEquals(1, annotations.size());
        assertEquals(DataAccess.class, annotations.get(0).annotationType());
    }

    @Test
    public void testIsCallerSensitivePresent() {
        assertEquals(isPresent(CALLER_SENSITIVE_ANNOTATION_CLASS_NAME), isCallerSensitivePresent());
        assertEquals(CALLER_SENSITIVE_ANNOTATION_CLASS != null, isCallerSensitivePresent());
    }


    @DataAccess
    class A {
    }


    class B extends A {

    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Inherited
    @Documented
    public @interface ServiceMode {
    }


    @Inherited
    @Target({TYPE, METHOD})
    @Retention(RUNTIME)
    @ServiceMode
    @interface Monitored {

    }

    @Inherited
    @Target({TYPE, METHOD})
    @Retention(RUNTIME)
    @Monitored
    @interface DataAccess {
    }

}


