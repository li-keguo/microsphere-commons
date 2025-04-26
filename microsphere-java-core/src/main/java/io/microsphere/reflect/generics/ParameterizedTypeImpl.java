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
package io.microsphere.reflect.generics;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Objects;

import static io.microsphere.constants.SymbolConstants.DOLLAR_CHAR;
import static io.microsphere.util.ArrayUtils.arrayEquals;
import static java.util.Objects.hash;

/**
 * {@link ParameterizedType} Implementation forks {@link sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see ParameterizedType
 * @see sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
 * @since 1.0.0
 */
public class ParameterizedTypeImpl implements ParameterizedType {

    private final Type[] actualTypeArguments;

    private final Class<?> rawType;

    private final Type ownerType;

    private ParameterizedTypeImpl(Class<?> rawType,
                                  Type[] actualTypeArguments,
                                  Type ownerType) {
        this.actualTypeArguments = actualTypeArguments;
        this.rawType = rawType;
        this.ownerType = (ownerType != null) ? ownerType : rawType.getDeclaringClass();
        validateConstructorArguments();
    }

    private void validateConstructorArguments() {
        TypeVariable<?>[] formals = rawType.getTypeParameters();
        // check correct arity of actual type args
        if (formals.length != actualTypeArguments.length) {
            throw new MalformedParameterizedTypeException();
        }
        for (int i = 0; i < actualTypeArguments.length; i++) {
            // check actuals against formals' bounds
        }
    }

    /**
     * Static factory. Given a (generic) class, actual type arguments
     * and an owner type, creates a parameterized type.
     * This class can be instantiated with a a raw type that does not
     * represent a generic type, provided the list of actual type
     * arguments is empty.
     * If the ownerType argument is null, the declaring class of the
     * raw type is used as the owner type.
     * <p> This method throws a MalformedParameterizedTypeException
     * under the following circumstances:
     * If the number of actual type arguments (i.e., the size of the
     * array <tt>typeArgs</tt>) does not correspond to the number of
     * formal type arguments.
     * If any of the actual type arguments is not an instance of the
     * bounds on the corresponding formal.
     *
     * @param rawType             the Class representing the generic type declaration being
     *                            instantiated
     * @param actualTypeArguments - a (possibly empty) array of types
     *                            representing the actual type arguments to the parameterized type
     * @return An instance of <tt>ParameterizedType</tt>
     * @throws MalformedParameterizedTypeException - if the instantiation
     *                                             is invalid
     */
    public static ParameterizedTypeImpl of(Class<?> rawType, Type... actualTypeArguments) {
        return of(rawType, actualTypeArguments, null);
    }

    /**
     * Static factory. Given a (generic) class, actual type arguments
     * and an owner type, creates a parameterized type.
     * This class can be instantiated with a a raw type that does not
     * represent a generic type, provided the list of actual type
     * arguments is empty.
     * If the ownerType argument is null, the declaring class of the
     * raw type is used as the owner type.
     * <p> This method throws a MalformedParameterizedTypeException
     * under the following circumstances:
     * If the number of actual type arguments (i.e., the size of the
     * array <tt>typeArgs</tt>) does not correspond to the number of
     * formal type arguments.
     * If any of the actual type arguments is not an instance of the
     * bounds on the corresponding formal.
     *
     * @param rawType             the Class representing the generic type declaration being
     *                            instantiated
     * @param actualTypeArguments - a (possibly empty) array of types
     *                            representing the actual type arguments to the parameterized type
     * @param ownerType           - the enclosing type, if known.
     * @return An instance of <tt>ParameterizedType</tt>
     * @throws MalformedParameterizedTypeException - if the instantiation
     *                                             is invalid
     */
    public static ParameterizedTypeImpl of(Class<?> rawType, Type[] actualTypeArguments, Type ownerType) {
        return new ParameterizedTypeImpl(rawType, actualTypeArguments, ownerType);
    }

    /**
     * Returns an array of <tt>Type</tt> objects representing the actual type
     * arguments to this type.
     *
     * <p>Note that in some cases, the returned array be empty. This can occur
     * if this type represents a non-parameterized type nested within
     * a parameterized type.
     *
     * @return an array of <tt>Type</tt> objects representing the actual type
     * arguments to this type
     * @throws <tt>TypeNotPresentException</tt>             if any of the
     *                                                      actual type arguments refers to a non-existent type declaration
     * @throws <tt>MalformedParameterizedTypeException</tt> if any of the
     *                                                      actual type parameters refer to a parameterized type that cannot
     *                                                      be instantiated for any reason
     * @since 1.5
     */
    public Type[] getActualTypeArguments() {
        return actualTypeArguments.clone();
    }

    /**
     * Returns the <tt>Type</tt> object representing the class or interface
     * that declared this type.
     *
     * @return the <tt>Type</tt> object representing the class or interface
     * that declared this type
     */
    public Class<?> getRawType() {
        return rawType;
    }

    /**
     * Returns a <tt>Type</tt> object representing the type that this type
     * is a member of.  For example, if this type is <tt>O<T>.I<S></tt>,
     * return a representation of <tt>O<T></tt>.
     *
     * <p>If this type is a top-level type, <tt>null</tt> is returned.
     *
     * @return a <tt>Type</tt> object representing the type that
     * this type is a member of. If this type is a top-level type,
     * <tt>null</tt> is returned
     * @throws <tt>TypeNotPresentException</tt>             if the owner type
     *                                                      refers to a non-existent type declaration
     * @throws <tt>MalformedParameterizedTypeException</tt> if the owner type
     *                                                      refers to a parameterized type that cannot be instantiated
     *                                                      for any reason
     */
    public Type getOwnerType() {
        return ownerType;
    }

    /*
     * From the JavaDoc for java.lang.reflect.ParameterizedType
     * "Instances of classes that implement this interface must
     * implement an equals() method that equates any two instances
     * that share the same generic type declaration and have equal
     * type parameters."
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ParameterizedType) {
            // Check that information is equivalent
            ParameterizedType that = (ParameterizedType) o;

            if (this == that)
                return true;

            Type thatOwner = that.getOwnerType();
            Type thatRawType = that.getRawType();

            return
                    Objects.equals(ownerType, thatOwner) &&
                            Objects.equals(rawType, thatRawType) &&
                            arrayEquals(actualTypeArguments, // avoid clone
                                    that.getActualTypeArguments());
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return hash(actualTypeArguments) ^
                Objects.hashCode(ownerType) ^
                Objects.hashCode(rawType);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (ownerType != null) {
            if (ownerType instanceof Class)
                sb.append(((Class) ownerType).getName());
            else
                sb.append(ownerType);

            sb.append(DOLLAR_CHAR);

            if (ownerType instanceof ParameterizedTypeImpl) {
                // Find simple name of nested type by removing the
                // shared prefix with owner.
                sb.append(rawType.getName().replace(((ParameterizedTypeImpl) ownerType).rawType.getName() + DOLLAR_CHAR, ""));
            } else
                sb.append(rawType.getSimpleName());
        } else
            sb.append(rawType.getName());

        if (actualTypeArguments != null && actualTypeArguments.length > 0) {
            sb.append("<");
            boolean first = true;
            for (Type t : actualTypeArguments) {
                if (!first)
                    sb.append(", ");
                sb.append(t.getTypeName());
                first = false;
            }
            sb.append(">");
        }

        return sb.toString();
    }
}
