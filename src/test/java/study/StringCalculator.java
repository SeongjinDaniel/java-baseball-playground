package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;

public class StringCalculator {


    @DisplayName("문자열을 분리한다.")
    @ParameterizedTest
    @ValueSource(strings = {"2 + 3 * 4 / 2"})
    void StringSplit(String input) {
        String[] split = input.split(" ");
        assertThat(split).containsExactly("2", "+", "3", "*", "4", "/", "2");
    }

    @DisplayName("문자열을 분리하여 계산한다.")
    @ParameterizedTest
    @ValueSource(strings = {"2 + 3 * 4 / 2"})
    void StringSplitCalculator(String inputValue) {
        String[] values = inputValue.split(" ");
        List<Integer> numbers = new ArrayList<>();
        List<String> symbols = new ArrayList<>();

        addStringToList(values, numbers, symbols);
        int result = calculateSequentiallyFromNumberAndSymbols(numbers, symbols);
        assertThat(result).isEqualTo(10);
    }

    /**
     * 숫자인지 기호인지 구분하여 저장
     */
    private void addStringToList(String[] values, List<Integer> numbers, List<String> symbols) {
        for (String value : values) {
            if (isNumeric(value)) {
                numbers.add(Integer.parseInt(value));
                continue;
            }
            symbols.add(value);
        }
    }

    /**
     * 숫자인지 아닌지 조회
     */
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 숫자와 기호를 받아서 순차적으로 계산
     */
    private int calculateSequentiallyFromNumberAndSymbols(List<Integer> numbers, List<String> symbols) {
        int result = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (i == 0) {
                result = numbers.get(i);
                continue;
            }
            Operation operation = Operation.fromSymbol(symbols.get(i - 1).charAt(0));
            result = operation.apply(result, numbers.get(i));
        }
        return result;
    }

    private enum Operation {
        ADDITION('+', (x, y) -> x + y),
        SUBTRACTION('-', (x, y) -> x - y),
        MULTIPLICATION('*', (x, y) -> x * y),
        DIVISION('/', (x, y) -> {
            if (y == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return x / y;
        }),
        ;

        private final char symbol;
        private final BiFunction<Integer, Integer, Integer> operation;

        Operation(char symbol, BiFunction<Integer, Integer, Integer> operation) {
            this.symbol = symbol;
            this.operation = operation;
        }

        public char getSymbol() {
            return symbol;
        }

        public int apply(int x, int y) {
            return operation.apply(x, y);
        };

        public static Operation fromSymbol(char symbol) {
            for (Operation op : Operation.values()) {
                if (op.getSymbol() == symbol) {
                    return op;
                }
            }
            throw new IllegalArgumentException("유효하지 않은 기호: " + symbol);
        }

    }
}
