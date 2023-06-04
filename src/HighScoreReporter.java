import java.io.*;

public class HighScoreReporter {
    private static final String HIGH_SCORE_FILE = "highscore.txt";

    public static void recordHighScore(String playerName, int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE, true))) {
            writer.write(playerName + "," + score);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayHighScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String playerName = data[0];
                int score = Integer.parseInt(data[1]);
                System.out.println("Player: " + playerName + ", Score: " + score);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Record high scores
        recordHighScore("Player 1", 100);
        recordHighScore("Player 2", 200);
        recordHighScore("Player 3", 150);

        // Display high scores
        System.out.println("High Scores:");
        displayHighScores();
    }
}