package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    @Test
    void split() {
        String[] split = "1,2".split(",");
        assertThat(split).contains("2", "1");
    }

    @Test
    void split2() {
        String[] split = "1,2".split(",");
        assertThat(split).containsExactly("1", "2");
    }

    @DisplayName("charAt 메서드에서 assertThatThrownBy를 사용하여 Exception을 처리한다.")
    @Test
    void charAtException() {
        String charAt = "abc";
        int index = 12;

        assertThatThrownBy(() -> {
            char c = charAt.charAt(index);
            assertThat(c).isEqualTo('c');
        }).isInstanceOf(StringIndexOutOfBoundsException.class)
            .hasMessageContaining("%d", index);
    }

    @DisplayName("charAt 메서드에서 assertThatExceptionOfType 사용하여 Exception을 처리한다.")
    @Test
    void charAtException2() {
        String charAt = "abc";
        int index = 12;

        assertThatExceptionOfType(StringIndexOutOfBoundsException.class)
            .isThrownBy(() -> {
                char c = charAt.charAt(index);
                assertThat(c).isEqualTo('c');
            }).withMessageMatching("String index out of range: \\d+");
    }
}
