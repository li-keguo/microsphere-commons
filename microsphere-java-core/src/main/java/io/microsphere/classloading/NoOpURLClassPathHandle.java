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
package io.microsphere.classloading;

import java.net.URL;

import static io.microsphere.net.URLUtils.EMPTY_URL_ARRAY;

/**
 * No-Operation {@link URLClassPathHandle}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @see URLClassPathHandle
 * @since 1.0.0
 */
public class NoOpURLClassPathHandle implements URLClassPathHandle {

    @Override
    public boolean supports() {
        return true;
    }

    @Override
    public URL[] getURLs(ClassLoader classLoader) {
        return EMPTY_URL_ARRAY;
    }

    @Override
    public boolean removeURL(ClassLoader classLoader, URL url) {
        return false;
    }
}
