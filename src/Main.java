import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize players with their starting ratings.
        PlayerData player1 = new PlayerData("Player 1", 1782.19);
        PlayerData player2 = new PlayerData("Player 2", 1575.38);
        PlayerData player3 = new PlayerData("Player 3", 1597.22);
        PlayerData player4 = new PlayerData("Player 4", 1568.27);

        List<PlayerData> players = List.of(player1, player2, player3, player4);

        // Print header for clarity.
        System.out.println("\n========================================\n");
        System.out.println("Elo Rating Updates (Simultaneous Calculation):\n");

        // Calculate rating updates for each matchup.
        // Note: A win is represented by a score of 1.0.
        double matchup_1_vs_2 = calculateRatingUpdate(players.get(0).oldRating, players.get(1).oldRating, 1.0);
        double matchup_1_vs_3 = calculateRatingUpdate(players.get(0).oldRating, players.get(2).oldRating, 1.0);
        double matchup_1_vs_4 = calculateRatingUpdate(players.get(0).oldRating, players.get(3).oldRating, 1.0);

        double matchup_2_vs_3 = calculateRatingUpdate(players.get(1).oldRating, players.get(2).oldRating, 1.0);
        double matchup_2_vs_4 = calculateRatingUpdate(players.get(1).oldRating, players.get(3).oldRating, 1.0);

        double matchup_3_vs_4 = calculateRatingUpdate(players.get(2).oldRating, players.get(3).oldRating, 1.0);

        // Compute new ratings by summing the rating changes from each matchup.
        double player1Rating = players.get(0).oldRating + matchup_1_vs_2 + matchup_1_vs_3 + matchup_1_vs_4;
        double player2Rating = players.get(1).oldRating - matchup_1_vs_2 + matchup_2_vs_3 + matchup_2_vs_4;
        double player3Rating = players.get(2).oldRating - matchup_1_vs_3 - matchup_2_vs_3 + matchup_3_vs_4;
        double player4Rating = players.get(3).oldRating - matchup_1_vs_4 - matchup_2_vs_4 - matchup_3_vs_4;

        // Calculate rating changes.
        double change1 = player1Rating - players.get(0).oldRating;
        double change2 = player2Rating - players.get(1).oldRating;
        double change3 = player3Rating - players.get(2).oldRating;
        double change4 = player4Rating - players.get(3).oldRating;

        // Print the results in a formatted table.
        System.out.println("+----------+------------+------------+------------+");
        System.out.println("| Player   | Old Rating |   Change   | New Rating |");
        System.out.println("+----------+------------+------------+------------+");
        System.out.printf("| %-8s | %10.2f | %10.2f | %10.2f |\n",
                players.get(0).player, players.get(0).oldRating, change1, player1Rating);
        System.out.printf("| %-8s | %10.2f | %10.2f | %10.2f |\n",
                players.get(1).player, players.get(1).oldRating, change2, player2Rating);
        System.out.printf("| %-8s | %10.2f | %10.2f | %10.2f |\n",
                players.get(2).player, players.get(2).oldRating, change3, player3Rating);
        System.out.printf("| %-8s | %10.2f | %10.2f | %10.2f |\n",
                players.get(3).player, players.get(3).oldRating, change4, player4Rating);
        System.out.println("+----------+------------+------------+------------+");

        System.out.println("\n========================================\n");
    }

    // K-Factor value for Elo updates.
    private static final int DEFAULT_K_FACTOR = 12;

    /**
     * Calculates the expected score for a player based on their rating and the opponent's rating.
     * Formula: expectedScore = 1 / (1 + 10^((opponentRating - rating) / 400))
     *
     * @param rating         the player's current rating.
     * @param opponentRating the opponent's rating.
     * @return the expected score (a value between 0 and 1).
     */
    public static double calculateExpectedScore(double rating, double opponentRating) {
        return 1.0 / (1.0 + Math.pow(10, (opponentRating - rating) / 400.0));
    }

    /**
     * Calculates the rating update for a player after a match.
     * newRatingChange = K * (score - expectedScore)
     *
     * @param currentRating  the player's current rating.
     * @param opponentRating the opponent's rating.
     * @param score          the result of the match (1.0 for win, 0.0 for loss).
     * @return the rating change.
     */
    public static double calculateRatingUpdate(double currentRating, double opponentRating, double score) {
        double expectedScore = calculateExpectedScore(currentRating, opponentRating);
        return DEFAULT_K_FACTOR * (score - expectedScore);
    }

    // Record to hold player data.
    private record PlayerData(String player, double oldRating) {}
}
