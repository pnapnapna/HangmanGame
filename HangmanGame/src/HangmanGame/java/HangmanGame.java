package HangmanGame.java;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] words = {"happy", "Christmas", "santa"};
        String selectedWord = words[random.nextInt(words.length)];
        char[] guessedLetters = new char[selectedWord.length()];
        int attemptsLeft = 6;

        for (int i = 0; i < guessedLetters.length; i++) {
            guessedLetters[i] = '*';
        }

        System.out.println("먼저 행맨 게임의 규칙을 알려드리겠습니다.");
        System.out.println("| 단어를 맞추는 게임입니다. 각 단어의 문자 수만큼 별('*')이 표시된 상태로 게임이 시작됩니다. 이 별은 아직 맞추지 않은 글자를 나타냅니다.");
        System.out.println("| 플레이어는 한 번에 한 글자씩 추측하여 단어를 맞추려고 합니다. ");
        System.out.println("| 만약 추측한 글자가 정답에 포함되어 있다면, 그 위치의 밑줄을 해당 글자로 대체하여 게임 상태를 갱신합니다.");
        System.out.println("| 만약 추측한 글자가 정답에 포함되어 있지 않다면, 플레이어의 시도 횟수가 감소합니다.");
        System.out.println("| 플레이어가 단어를 모두 맞추면 게임에서 이기게 되며, 정해진 시도 횟수를 초과하면 게임에서 집니다.");
        System.out.println("| 정답 단어는 영어단어이므로 알파벳만 입력해주세요!");
        System.out.println("----------------------------그럼 게임을 시작하겠습니다.----------------------------");
        
        printCurrentState(guessedLetters, attemptsLeft);

        while (attemptsLeft > 0) {
            System.out.print("한 글자를 추측하세요: ");
            char userGuess = scanner.next().charAt(0);

            if (isAlreadyGuessed(userGuess, guessedLetters)) {
                System.out.println("이미 추측한 글자입니다. 다시 시도하세요.");
                System.out.println("---------------------------------------------------------------------");
                continue;
            }

            boolean isCorrectGuess = updateGuessedLetters(userGuess, selectedWord, guessedLetters);

            if (isCorrectGuess) {
                System.out.println("철자를 맞추셨습니다.");
                System.out.println("---------------------------------------------------------------------");
            } else {
                attemptsLeft--;
                System.out.println("틀렸습니다. 남은 시도 횟수: " + attemptsLeft);
                System.out.println("---------------------------------------------------------------------");
            }

            printCurrentState(guessedLetters, attemptsLeft);

            if (isWordGuessed(guessedLetters)) {
                System.out.println("~~~~~*~~~~~*~~~~~*~~~~~축하합니다. 정답입니다.~~~~~*~~~~~*~~~~~*~~~~~");
                break;
            }
        }

        if (attemptsLeft == 0) {
            System.out.println("아쉽습니다. 시도 횟수를 모두 사용하셨습니다. 정답은 " + selectedWord + "입니다.");
        }

        scanner.close();
    }

    private static void printCurrentState(char[] guessedLetters, int attemptsLeft) {
        System.out.println("현재 상태: " + new String(guessedLetters));
        System.out.println("남은 시도 횟수: " + attemptsLeft);
    }

    private static boolean isAlreadyGuessed(char guess, char[] guessedLetters) {
        for (char letter : guessedLetters) {
            if (letter == guess) {
                return true;
            }
        }
        return false;
    }

    private static boolean updateGuessedLetters(char guess, String word, char[] guessedLetters) {
        boolean isCorrectGuess = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                guessedLetters[i] = guess;
                isCorrectGuess = true;
            }
        }
        return isCorrectGuess;
    }

    private static boolean isWordGuessed(char[] guessedLetters) {
        for (char letter : guessedLetters) {
            if (letter == '*') {
                return false;
            }
        }
        return true;
    }
}
