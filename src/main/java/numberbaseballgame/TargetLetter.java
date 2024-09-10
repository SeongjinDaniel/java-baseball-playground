package numberbaseballgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 맞춰야 할 목표 문자
 */
public class TargetLetter {
    private final String letter;

    private final static int START_NUMBER = 1;
    private final static int END_NUMBER = 9;

    public TargetLetter(String letter) {
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }

    /** 랜덤하게 서로다른 3개의 숫자를 발생 */
    public static TargetLetter generateRandomThreeDigitNumber() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = START_NUMBER; i <= END_NUMBER; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return new TargetLetter(numbers.get(0) + numbers.get(1) + numbers.get(2) + "");
    }
}
