import java.util.List;

public class Main {
    public static void main(String[] args) {
        PlayerData player1 = new PlayerData("Player 1", 1500);
        PlayerData player2 = new PlayerData("Player 2", 1500);
        PlayerData player3 = new PlayerData("Player 3", 1500);
        PlayerData player4 = new PlayerData("Player 4", 1500);

        List<PlayerData> players = List.of(player1, player2, player3, player4);

        // Player 1 win over player 2, 3, 4.
        // Player 2 win over player 3, 4. And Lose from player 1.
        // Player 3 win over player 4. And Lose from player 1, 2.
        // Player 4 Lose from player 1, 2, 3.

        System.out.println("""
                           This Elo is generated when:
                           player 2 lose from 1 first and then win over 3 and 4.
                           Player 3 lose from 1 and 2 first and then win over 4.
                           """);
        //Player 1 calculation
        double player1Rating = 0.0;
        //// Player 1 win over player 2

        player1Rating = updateRating(players.get(0).oldRating, players.get(1).oldRating, 1.0);

        //// Player 1 win over player 3
        player1Rating = updateRating(player1Rating, players.get(2).oldRating, 1.0);

        //// Player 1 win over player 4
        player1Rating = updateRating(player1Rating, players.get(3).oldRating, 1.0);

        System.out.println("Player 1: " + player1Rating);

        //Player 2 calculation
        double player2Rating = 0.0;

        //// Player 2 Lose from player 1
        player2Rating = updateRating(players.get(1).oldRating, player1Rating, 0.0);

        //// Player 2 win over player 3
        player2Rating = updateRating(player2Rating, players.get(2).oldRating, 1.0);

        //// Player 2 win over player 4
        player2Rating = updateRating(player2Rating, players.get(3).oldRating, 1.0);

        System.out.println("Player 2: " + player2Rating);

        //Player 3 calculation
        double player3Rating = 0.0;

        //// Player 3 Lose from player 1
        player3Rating = updateRating(players.get(2).oldRating, player1Rating, 0.0);

        //// Player 3 Lose from player 2
        player3Rating = updateRating(player3Rating, player2Rating, 0.0);

        //// Player 3 win over player 4
        player3Rating = updateRating(player3Rating, players.get(3).oldRating, 1.0);

        System.out.println("Player 3: " + player3Rating);

        //Player 4 calculation
        double player4Rating = 0.0;
        //// Player 4 Lose from player 1
        player4Rating = updateRating(players.get(3).oldRating, player1Rating, 0.0);

        //// Player 4 Lose from player 2
        player4Rating = updateRating(player4Rating, player2Rating, 0.0);

        //// Player 4 Lose from player 3
        player4Rating = updateRating(player4Rating, player3Rating, 0.0);

        System.out.println("Player 4: " + player4Rating);

        System.out.println("\n===========\n");

        System.out.println("""
                                   This Elo is generated when:
                                   player 2 win over 3 and 4 first and then lose from 1.
                                   Player 3 win over 4 first and then lose from 1 and 2.
                                   """);        //Player 1 calculation
        player1Rating = 0.0;
        //// Player 1 win over player 2

        player1Rating = updateRating(players.get(0).oldRating, players.get(1).oldRating, 1.0);

        //// Player 1 win over player 3
        player1Rating = updateRating(player1Rating, players.get(2).oldRating, 1.0);

        //// Player 1 win over player 4
        player1Rating = updateRating(player1Rating, players.get(3).oldRating, 1.0);

        System.out.println("Player 1: " + player1Rating);

        //Player 2 calculation
        player2Rating = 0.0;

        //// Player 2 win over player 3
        player2Rating = updateRating(player2Rating, players.get(2).oldRating, 1.0);

        //// Player 2 win over player 4
        player2Rating = updateRating(player2Rating, players.get(3).oldRating, 1.0);

        //// Player 2 Lose from player 1
        player2Rating = updateRating(players.get(1).oldRating, player1Rating, 0.0);

        System.out.println("Player 2: " + player2Rating);

        //Player 3 calculation
        player3Rating = 0.0;

        //// Player 3 win over player 4
        player3Rating = updateRating(player3Rating, players.get(3).oldRating, 1.0);

        //// Player 3 Lose from player 1
        player3Rating = updateRating(players.get(2).oldRating, player1Rating, 0.0);

        //// Player 3 Lose from player 2
        player3Rating = updateRating(player3Rating, player2Rating, 0.0);


        System.out.println("Player 3: " + player3Rating);

        //Player 4 calculation
        player4Rating = 0.0;
        //// Player 4 Lose from player 1
        player4Rating = updateRating(players.get(3).oldRating, player1Rating, 0.0);

        //// Player 4 Lose from player 2
        player4Rating = updateRating(player4Rating, player2Rating, 0.0);

        //// Player 4 Lose from player 3
        player4Rating = updateRating(player4Rating, player3Rating, 0.0);

        System.out.println("Player 4: " + player4Rating);

        System.out.println("\n===========\n");

        System.out.println("""
                           This Elo is generated in inverted order:
                            Player 4 lose from 1, 2, 3 first.
                            Player 3 lose from 1, 2 first and then win over 4.
                            Player 2 lose from 1 first and then win over 3 and 4.
                           """);

        //Player 4 calculation
        player4Rating = 0.0;
        //// Player 4 Lose from player 1
        player4Rating = updateRating(players.get(3).oldRating, player1Rating, 0.0);

        //// Player 4 Lose from player 2
        player4Rating = updateRating(player4Rating, player2Rating, 0.0);

        //// Player 4 Lose from player 3
        player4Rating = updateRating(player4Rating, player3Rating, 0.0);

        System.out.println("Player 4: " + player4Rating);

        //Player 3 calculation
        player3Rating = 0.0;

        //// Player 3 Lose from player 1
        player3Rating = updateRating(players.get(2).oldRating, player1Rating, 0.0);

        //// Player 3 Lose from player 2
        player3Rating = updateRating(player3Rating, player2Rating, 0.0);

        //// Player 3 win over player 4
        player3Rating = updateRating(player3Rating, players.get(3).oldRating, 1.0);

        System.out.println("Player 3: " + player3Rating);

        //Player 2 calculation
        player2Rating = 0.0;

        //// Player 2 Lose from player 1
        player2Rating = updateRating(players.get(1).oldRating, player1Rating, 0.0);

        //// Player 2 win over player 3
        player2Rating = updateRating(player2Rating, players.get(2).oldRating, 1.0);

        //// Player 2 win over player 4
        player2Rating = updateRating(player2Rating, players.get(3).oldRating, 1.0);

        System.out.println("Player 2: " + player2Rating);

        //Player 1 calculation
        player1Rating = 0.0;
        //// Player 1 win over player 2

        player1Rating = updateRating(players.get(0).oldRating, players.get(1).oldRating, 1.0);

        //// Player 1 win over player 3
        player1Rating = updateRating(player1Rating, players.get(2).oldRating, 1.0);

        //// Player 1 win over player 4
        player1Rating = updateRating(player1Rating, players.get(3).oldRating, 1.0);

        System.out.println("Player 1: " + player1Rating);

        System.out.println("\n===========\n");

        System.out.println("""
                           This Elo is generated in inverted order:
                            Player 4 lose from 1, 2, 3 first.
                            Player 3 win over 4 first and then lose from 1, 2.
                            Player 2 win over 3 and 4 first and then lose from 1.
                           """);

        //Player 4 calculation
        player4Rating = 0.0;
        //// Player 4 Lose from player 1
        player4Rating = updateRating(players.get(3).oldRating, player1Rating, 0.0);

        //// Player 4 Lose from player 2
        player4Rating = updateRating(player4Rating, player2Rating, 0.0);

        //// Player 4 Lose from player 3
        player4Rating = updateRating(player4Rating, player3Rating, 0.0);

        System.out.println("Player 4: " + player4Rating);

        //Player 3 calculation
        player3Rating = 0.0;

        //// Player 3 win over player 4
        player3Rating = updateRating(player3Rating, players.get(3).oldRating, 1.0);

        //// Player 3 Lose from player 1
        player3Rating = updateRating(players.get(2).oldRating, player1Rating, 0.0);

        //// Player 3 Lose from player 2
        player3Rating = updateRating(player3Rating, player2Rating, 0.0);

        System.out.println("Player 3: " + player3Rating);

        //Player 2 calculation
        player2Rating = 0.0;

        //// Player 2 win over player 3
        player2Rating = updateRating(player2Rating, players.get(2).oldRating, 1.0);

        //// Player 2 win over player 4
        player2Rating = updateRating(player2Rating, players.get(3).oldRating, 1.0);

        //// Player 2 Lose from player 1
        player2Rating = updateRating(players.get(1).oldRating, player1Rating, 0.0);

        System.out.println("Player 2: " + player2Rating);

        //Player 1 calculation
        player1Rating = 0.0;

        //// Player 1 win over player 2
        player1Rating = updateRating(players.get(0).oldRating, players.get(1).oldRating, 1.0);

        //// Player 1 win over player 3
        player1Rating = updateRating(player1Rating, players.get(2).oldRating, 1.0);

        //// Player 1 win over player 4
        player1Rating = updateRating(player1Rating, players.get(3).oldRating, 1.0);

        System.out.println("Player 1: " + player1Rating);

        System.out.println("\n===========\n");


    }

    private static final int DEFAULT_K_FACTOR = 10;

    /**
     * Calculates the expected score for a player based on their rating and the opponent's rating.
     * The expected score is computed using the formula:
     * expectedScore = 1 / (1 + 10^((opponentRating - rating) / 400))
     *
     * @param rating         the player's current rating.
     * @param opponentRating the opponent's rating.
     * @return the expected score (a value between 0 and 1).
     */
    public static double calculateExpectedScore(double rating, double opponentRating) {
        return 1.0 / (1.0 + Math.pow(10, (opponentRating - rating) / 400.0));
    }

    /**
     * Updates the rating of a player after a game.
     * The new rating is computed using the formula:
     * newRating = currentRating + kFactor * (score - expectedScore)
     * where score is:
     *   - 1.0 for a win,
     *   - 0.0 for a loss.
     *
     * @param currentRating  the player's current rating.
     * @param opponentRating the opponent's rating.
     * @param score          the game result score.
     * @return the updated rating.
     */
    public static double updateRating(double currentRating, double opponentRating, double score) {
        double expectedScore = calculateExpectedScore(currentRating, opponentRating);
        return currentRating + DEFAULT_K_FACTOR * (score - expectedScore);
    }

    private record PlayerData(String player, double oldRating) {}

}