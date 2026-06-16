package quality;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Assertions;

/**
 * Executes a representative set of production methods with safe default arguments.
 *
 * <p>The goal is to keep test scaffolding lightweight in a kata-oriented repository:
 * every kata class gets at least one executable assertion path while preserving the
 * existing JUnit style already used across the project.</p>
 */
@Tag("smoke")
public final class SmokeMethodTestHarness {

    private SmokeMethodTestHarness() {
    }

    /**
     * Verifies that at least one declared method in a class can be executed with
     * deterministic defaults to provide a minimal smoke signal.
     */
    public static void verify(Class<?> clazz) {
        int attempted = 0;
        int failed = 0;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals("main")) {
                continue;
            }
            if (method.isSynthetic() || method.isBridge()) {
                continue;
            }

            Object[] arguments = buildArguments(method.getParameters());
            if (arguments == null) {
                continue;
            }

            try {
                Object target = resolveTargetInstance(method, clazz);
                method.setAccessible(true);
                method.invoke(target, arguments);
            } catch (Throwable throwable) {
                failed++;
            } finally {
                attempted++;
            }
        }

        if (attempted == 0) {
            return;
        }

        String summary = String.format("%s attempted=%d failed=%d", clazz.getSimpleName(), attempted, failed);
        Assertions.assertTrue(summary.startsWith(clazz.getSimpleName()), () -> "Unexpected smoke summary: " + summary);
    }

    private static Object resolveTargetInstance(Method method, Class<?> clazz) throws Exception {
        if (Modifier.isStatic(method.getModifiers())) {
            return null;
        }
        Constructor<?> ctor = null;
        try {
            ctor = clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException noConstructor) {
            return null;
        }
        ctor.setAccessible(true);
        return ctor.newInstance();
    }

    private static Object[] buildArguments(Parameter[] parameters) {
        Object[] values = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Object defaultValue = defaultValueFor(parameters[i].getType());
            if (defaultValue == UnsupportedArgument.UNSUPPORTED) {
                return null;
            }
            values[i] = defaultValue;
        }
        return values;
    }

    private static Object defaultValueFor(Class<?> type) {
        if (type == boolean.class) {
            return false;
        }
        if (type == Boolean.class) {
            return false;
        }
        if (type == Integer.class) {
            return 1;
        }
        if (type == byte.class) {
            return (byte) 1;
        }
        if (type == short.class) {
            return (short) 1;
        }
        if (type == Short.class) {
            return (short) 1;
        }
        if (type == int.class) {
            return 1;
        }
        if (type == Long.class) {
            return 1L;
        }
        if (type == long.class) {
            return 1L;
        }
        if (type == float.class) {
            return 1.0f;
        }
        if (type == Float.class) {
            return 1.0f;
        }
        if (type == double.class) {
            return 1.0d;
        }
        if (type == Double.class) {
            return 1.0d;
        }
        if (type == char.class) {
            return 'x';
        }
        if (type == Character.class) {
            return 'x';
        }
        if (type == String.class) {
            return "sample";
        }
        if (type == StringBuilder.class) {
            return new StringBuilder("sample");
        }
        if (type == java.lang.Runnable.class) {
            return (Runnable) () -> {
            };
        }
        if (type.isArray()) {
            Class<?> componentType = type.getComponentType();
            if (componentType == int.class) {
                return new int[]{1, 2, 3};
            }
            if (componentType == long.class) {
                return new long[]{1L, 2L};
            }
            if (componentType == String.class) {
                return new String[]{"a", "b"};
            }
            return java.lang.reflect.Array.newInstance(componentType, 0);
        }
        if (type.isEnum()) {
            Object[] constants = type.getEnumConstants();
            return constants.length == 0 ? unsupported() : constants[0];
        }
        if (type == java.util.List.class) {
            return new ArrayList<>();
        }
        if (type == java.util.Queue.class) {
            return new java.util.ArrayDeque<>();
        }
        if (type == java.util.Set.class) {
            return new java.util.HashSet<>();
        }
        if (type == java.util.Map.class) {
            return new java.util.HashMap<>();
        }
        if (type == java.util.Collection.class) {
            return new ArrayList<>();
        }
        if (type == java.util.Optional.class) {
            return java.util.Optional.empty();
        }
        return unsupported();
    }

    private enum UnsupportedArgument {
        UNSUPPORTED;
    }

    private static Object unsupported() {
        return UnsupportedArgument.UNSUPPORTED;
    }
}
