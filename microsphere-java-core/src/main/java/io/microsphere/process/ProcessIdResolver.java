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
package io.microsphere.process;

import io.microsphere.lang.Prioritized;

/**
 * The resolver for Process ID
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @see ModernProcessIdResolver
 * @since 1.0.0
 */
public interface ProcessIdResolver extends Prioritized {

    /**
     * The unknown process id
     */
    long UNKNOWN_PROCESS_ID = -1L;

    /**
     * Whether supports to resolve the process id or not?
     *
     * @return <code>true</code> if supports, otherwise <code>false</code>
     */
    boolean supports();

    /**
     * Resolve the current process id
     *
     * @return <code>>null</code> if can't be resolved
     */
    Long current();
}
