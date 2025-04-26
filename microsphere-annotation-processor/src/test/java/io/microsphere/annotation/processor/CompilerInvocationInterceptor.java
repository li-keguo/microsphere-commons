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

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.microsphere.annotation.processor.AbstractAnnotationProcessingTest.testInstanceHolder;


/**
 * {@link InvocationInterceptor} based on Java {@link Compiler}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @since 1.0.0
 */
public class CompilerInvocationInterceptor implements InvocationInterceptor {

    @Override
    public void interceptTestMethod(Invocation<Void> invocation,
                                    ReflectiveInvocationContext<Method> invocationContext,
                                    ExtensionContext extensionContext) throws Throwable {
        Set<Class<?>> compiledClasses = new LinkedHashSet<>();
        AbstractAnnotationProcessingTest abstractAnnotationProcessingTest = testInstanceHolder.get();
        compiledClasses.add(getClass());
        abstractAnnotationProcessingTest.addCompiledClasses(compiledClasses);
        Compiler compiler = new Compiler();
        compiler.sourcePaths(compiledClasses);
        compiler.processors(new AnnotationProcessingTestProcessor(abstractAnnotationProcessingTest, invocation, invocationContext, extensionContext));
        compiler.compile(compiledClasses.toArray(new Class[0]));
    }
}
