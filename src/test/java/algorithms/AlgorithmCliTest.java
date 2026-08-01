package algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("integration")
class AlgorithmCliTest {

    @ParameterizedTest
    @MethodSource("stdioCases")
    void stdioEntryPointProducesExpectedOutput(
            String className,
            String methodName,
            String input,
            String expectedOutput) throws Exception {

        assertEquals(normalizeLines(expectedOutput), normalizeLines(invokeWithStdio(className, methodName, input)));
    }

    @Test
    void solution2RejectsMissingInputLine() {
        assertThrows(IllegalArgumentException.class, () -> invokeWithStdio(
                "algorithms.sprint1.Solution2",
                "main",
                "2%n1 2%n".formatted()));
    }

    @Test
    void solution2RejectsOversizedLineCount() {
        assertThrows(IllegalArgumentException.class, () -> invokeWithStdio(
                "algorithms.sprint1.Solution2",
                "main",
                "1000001%n".formatted()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"+", "", "1 0 /", "abc", "1 2"})
    void calculatorRejectsMalformedExpressionsWithoutThrowing(String input) throws Exception {
        assertEquals("", invokeWithStdio("algorithms.sprint2.Calculator", "run", input));
    }

    private static Stream<Arguments> stdioCases() {
        return Stream.of(
                arguments("algorithms.sprint0.SlidingAverage", "main",
                        "7%n1 2 3 4 5 6 7%n4%n".formatted(),
                        "2.5 3.5 4.5 5.5 "),
                arguments("algorithms.sprint0.Zip", "main",
                        "3%n1 3 5%n2 4 6%n".formatted(),
                        "1 2 3 4 5 6 "),
                arguments("algorithms.sprint1.SleightOfHand", "run",
                        "3%n1231%n2..2%n2..2%n2..2%n".formatted(),
                        "2%n".formatted()),
                arguments("algorithms.sprint1.Distances", "run",
                        "5%n0 1 4 9 0%n".formatted(),
                        "0 1 2 1 0%n".formatted()),
                arguments("algorithms.sprint1.Solution2", "main",
                        "2%n1 2%n3 4%n".formatted(),
                        "3%n7%n%n".formatted()),
                arguments("algorithms.sprint2.Calculator", "run",
                        "2 1 + 3 *%n".formatted(),
                        "9%n".formatted()),
                arguments("algorithms.sprint2.Deque", "run",
                        "4%n4%npush_front 861%npush_front -819%npop_back%npop_back%n".formatted(),
                        "861%n-819%n".formatted()),
                arguments("algorithms.sprint3.FastSort", "run",
                        """
                        5
                        alla 4 100
                        gena 6 1000
                        gosha 2 90
                        rita 2 90
                        timofey 4 80
                        """,
                        "gena%ntimofey%nalla%ngosha%nrita%n".formatted()),
                arguments("algorithms.sprint4.FindSystem", "solve",
                        """
                        3
                        i love coffee
                        coffee with milk and sugar
                        free tea for everyone
                        3
                        i like black coffee without milk
                        everyone loves new year
                        Mary likes black coffee without milk
                        """,
                        "1 2%n3%n2 1%n".formatted()),
                arguments("algorithms.sprint4.Map", "main",
                        """
                        10
                        get 1
                        put 1 10
                        get 1
                        put 1 20
                        get 1
                        delete 1
                        get 1
                        delete 1
                        put -5 7
                        get -5
                        """,
                        "None%n10%n20%n20%nNone%nNone%n7%n".formatted()),
                arguments("algorithms.sprint5.PyramidSort", "run",
                        """
                        5
                        alla 4 100
                        gena 6 1000
                        gosha 2 90
                        rita 2 90
                        timofey 4 80
                        """,
                        "gena%ntimofey%nalla%ngosha%nrita%n".formatted()),
                arguments("algorithms.sprint6.DorogayaSet", "run",
                        "4 4%n1 2 5%n1 3 6%n2 4 8%n3 4 3%n".formatted(),
                        "19%n".formatted()),
                arguments("algorithms.sprint6.WaterWorld", "run",
                        "3 3%n#.#%n.#.%n#.#%n".formatted(),
                        "5 1%n".formatted()),
                arguments("algorithms.sprint7.EqualSums", "run",
                        "4%n1 5 7 1%n".formatted(),
                        "True%n".formatted()),
                arguments("algorithms.sprint7.LevenshteinDistance", "run",
                        "kitten%nsitting%n".formatted(),
                        "3%n".formatted()),
                arguments("algorithms.sprint8.Crib", "run",
                        "examiwillpasstheexam%n5%nwill%npass%nthe%nexam%ni%n".formatted(),
                        "YES%n".formatted()),
                arguments("algorithms.sprint8.PackedPrefix", "run",
                        "3%n2[a]2[ab]%n3[a]2[r2[t]]%na2[aa3[b]]%n".formatted(),
                        "aaa%n".formatted())
        );
    }

    private static String invokeWithStdio(String className, String methodName, String input) throws Exception {
        InputStream oldIn = System.in;
        PrintStream oldOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            System.setOut(new PrintStream(output, true, StandardCharsets.UTF_8));
            invoke(className, methodName);
            return output.toString(StandardCharsets.UTF_8);
        } finally {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }
    }

    private static void invoke(String className, String methodName) throws Exception {
        Method method = "main".equals(methodName)
                ? Class.forName(className).getDeclaredMethod(methodName, String[].class)
                : Class.forName(className).getDeclaredMethod(methodName);
        method.setAccessible(true);

        try {
            if ("main".equals(methodName)) {
                method.invoke(null, (Object) new String[0]);
            } else {
                method.invoke(null);
            }
        } catch (InvocationTargetException exception) {
            throwOriginalCause(exception.getCause());
        }
    }

    private static void throwOriginalCause(Throwable cause) throws Exception {
        if (cause instanceof Exception exception) {
            throw exception;
        }
        if (cause instanceof Error error) {
            throw error;
        }
        throw new AssertionError(cause);
    }

    private static String normalizeLines(String value) {
        return value.replace("\r\n", "\n");
    }
}
