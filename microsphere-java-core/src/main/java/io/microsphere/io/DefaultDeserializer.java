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
package io.microsphere.io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import static io.microsphere.util.ArrayUtils.isEmpty;

/**
 * Default {@link Deserializer} based on Java Standard Serialization.
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ObjectInputStream
 * @see Serializable
 * Date : 2021-05-02
 * @since 1.0.0
 */
public class DefaultDeserializer implements Deserializer<Object> {

    public static final DefaultDeserializer INSTANCE = new DefaultDeserializer();

    @Override
    public Object deserialize(byte[] bytes) throws IOException {
        if (isEmpty(bytes)) {
            return null;
        }
        Object value = null;
        try (FastByteArrayInputStream inputStream = new FastByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)
        ) {
            // byte[] -> Value
            value = objectInputStream.readObject();
        } catch (Exception e) {
            throw new IOException(e);
        }
        return value;
    }
}
