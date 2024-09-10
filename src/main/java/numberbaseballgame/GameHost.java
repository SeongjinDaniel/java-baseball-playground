package numberbaseballgame;

import java.util.Scanner;

/**
 * 게임 진행자
 */
public class GameHost {
    private final Referee referee;
    private final String GAME_SUCCESS = "3스트라이크";
    private final int NEW_GAME = 1;

    public GameHost(Referee referee) {
        this.referee = referee;
    }

    /** 게임 시작 */
    public void playGame() {
        askUntilCorrect();
    }

    private void askUntilCorrect() {
        do {
            System.out.println("숫자를 입력해 주세요 : ");
            String playerSubmitNumber = Player.submitThreeUniqueNumbers();
            referee.validateThreeUniqueNumbers(playerSubmitNumber);
            referee.displayRefereeResult(playerSubmitNumber);
        } while (!processGameWin());
    }

    private boolean processGameWin() {
        if (GAME_SUCCESS.equals(referee.getResult())) {
            System.out.println("3개의 숫자를 모두 맞히셨습니다.! 게임종료");
            askToContinueGame();
            return true;
        }
        return false;
    }

    private void askToContinueGame() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        Scanner sc = new Scanner(System.in);
        if (sc.nextInt() == NEW_GAME) {
            referee.intResult();
            playGame();
        }
    }
}
