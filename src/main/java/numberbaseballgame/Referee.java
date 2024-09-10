package numberbaseballgame;

import java.util.HashSet;
import java.util.Set;

/**
 * 심판
 */
public class Referee {
    private String result;

    private final TargetLetter targetLetter;
    private final GameHost gameHost;

    private final static int NUMBER_LENGTH = 3;

    public Referee(GameHost gameHost) {
        this.gameHost = gameHost;
        this.targetLetter = TargetLetter.generateRandomThreeDigitNumber();
    }

    public String getResult() {
        return result;
    }

    public void intResult() {
        this.result = null;
    }

    public void displayRefereeResult(String playerLetter) {
        final int BALL_COUNT = 1;
        final int STRIKE_COUNT = 2;
        int resultBallCount = 0;
        int resultStrikeCount = 0;
        for (int i = 0; i < playerLetter.length(); i++) {
            char currentChar = playerLetter.charAt(i);
            int ballStrikeCount = 0;
            if (isCharPresent(currentChar)) {
                ballStrikeCount++;
            }
            if (isCharAtCurrentIndex(currentChar, i)) {
                ballStrikeCount++;
            }
            if (ballStrikeCount == BALL_COUNT) {
                resultBallCount++;
            }
            if (ballStrikeCount == STRIKE_COUNT) {
                resultStrikeCount++;
            }
        }
        displayResult(resultBallCount, resultStrikeCount);
    }

    private void displayResult(int resultBallCount, int resultStrikeCount) {
        if (resultBallCount > 0 && resultStrikeCount > 0) {
            result = String.join("", String.valueOf(resultBallCount), "볼 ", String.valueOf(resultStrikeCount), "스트라이크");
            System.out.println(result);
        }
        if (resultBallCount == 0 && resultStrikeCount > 0) {
            result = String.join("", String.valueOf(resultStrikeCount), "스트라이크");
            System.out.println(result);
        }
        if (resultBallCount > 0 && resultStrikeCount == 0) {
            result = String.join("", String.valueOf(resultBallCount), "볼");
        }
    }

    /**
     * 문제 내의 입력 문자가 포함되어 있는지 유무
     */
    public boolean isCharPresent(char input) {
        return targetLetter.getLetter()
            .contains(String.valueOf(input));
    }

    /**
     * 문제 내의 입력 문자가 현재 순번과 같은지 유무
     */
    public boolean isCharAtCurrentIndex(char input, int index) {
        return input == targetLetter.getLetter().charAt(index);
    }

    public void validateThreeUniqueNumbers(String input) {
        validateNumberLength(input);
        Set<Character> charSet = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            validateNumericInput(currentChar);
            charSet.add(currentChar);
        }
        validateThreeUniqueDigits(charSet);
    }

    private static void validateNumericInput(char currentChar) {
        if (!Character.isDigit(currentChar)) {
            throw new IllegalArgumentException("입력 값은 숫자여야 합니다.");
        }
    }

    private static void validateThreeUniqueDigits(Set<Character> charSet) {
        if (charSet.size() != NUMBER_LENGTH) {
            throw new IllegalArgumentException("서로 다른 3개의 숫자여야합니다.");
        }
    }

    private static void validateNumberLength(String input) {
        if (input.length() != NUMBER_LENGTH) {
            throw new IllegalArgumentException("입력 값은 정확히 3자리여야 합니다.");
        }
    }
}
