import static java.lang.Math.pow;

public class OutputBeterFormat {

    // Use a simple class to hold simulation results.
    private static class SimulationResult {
        double player1;
        double player2;
        double player3;
        double player4;

        public SimulationResult(double player1, double player2, double player3, double player4) {
            this.player1 = player1;
            this.player2 = player2;
            this.player3 = player3;
            this.player4 = player4;
        }
    }

    public static void main(String[] args) {
        // Compute final ratings for each scenario.
        SimulationResult scenario1 = simulateScenario1();
        SimulationResult scenario2 = simulateScenario2();
        SimulationResult scenario3 = simulateScenario3();
        SimulationResult scenario4 = simulateScenario4();

        // Print a table header.
        System.out.println("+-------------------------------------------+----------+----------+----------+----------+");
        System.out.println("| Simulation Scenario                       | Player 1 | Player 2 | Player 3 | Player 4 |");
        System.out.println("+-------------------------------------------+----------+----------+----------+----------+");

        // Print each scenario’s results in one row.
        printScenario("Scenario 1", scenario1);
        printScenario("Scenario 2", scenario2);
        printScenario("Scenario 3", scenario3);
        printScenario("Scenario 4", scenario4);

        System.out.println("+-------------------------------------------+----------+----------+----------+----------+");
    }

    // Helper method to print one table row.
    private static void printScenario(String name, SimulationResult result) {
        System.out.printf("| %-41s| %8.2f | %8.2f | %8.2f | %8.2f |\n",
                          name, result.player1, result.player2, result.player3, result.player4);
    }

    // Scenario 1:
    // Player 1 wins over Player 2, 3, 4.
    // Player 2 loses to Player 1 first and then wins over Player 3 and 4.
    // Player 3 loses to Player 1 and 2 first and then wins over Player 4.
    // Player 4 loses to Player 1, 2 and 3.
    private static SimulationResult simulateScenario1() {
        // Player 1: wins over P2, P3, P4.
        double p1 = 1500.0;
        p1 = updateRating(p1, 1500.0, 1.0); // win over P2
        p1 = updateRating(p1, 1500.0, 1.0); // win over P3
        p1 = updateRating(p1, 1500.0, 1.0); // win over P4

        // Player 2: loses to P1 then wins over P3 and P4.
        double p2 = 1500.0;
        p2 = updateRating(p2, p1, 0.0);         // loss to P1
        p2 = updateRating(p2, 1500.0, 1.0);    // win over P3
        p2 = updateRating(p2, 1500.0, 1.0);    // win over P4

        // Player 3: loses to P1 and P2, then wins over P4.
        double p3 = 1500.0;
        p3 = updateRating(p3, p1, 0.0);         // loss to P1
        p3 = updateRating(p3, p2, 0.0);         // loss to P2
        p3 = updateRating(p3, 1500.0, 1.0);    // win over P4

        // Player 4: loses to P1, P2 and P3.
        double p4 = 1500.0;
        p4 = updateRating(p4, p1, 0.0);         // loss to P1
        p4 = updateRating(p4, p2, 0.0);    // loss to P2
        p4 = updateRating(p4, p3, 0.0);    // loss to P3

        return new SimulationResult(p1, p2, p3, p4);
    }

    // Scenario 2:
    // Player 1 wins over everyone.
    // Player 2 wins over Player 3 and 4 first and then loses to Player 1.
    // Player 3 wins over Player 4 first and then loses to Player 1 and 2.
    // Player 4 loses to everyone.
    private static SimulationResult simulateScenario2() {
        // Player 1: wins over everyone.
        double p1 = 1500.0;
        p1 = updateRating(p1, 1500.0, 1.0);
        p1 = updateRating(p1, 1500.0, 1.0);
        p1 = updateRating(p1, 1500.0, 1.0);

        // Player 2: wins over P3 and P4, then loses to P1.
        double p2 = 1500.0;
        p2 = updateRating(p2, 1500.0, 1.0);   // win over P3
        p2 = updateRating(p2, 1500.0, 1.0);   // win over P4
        p2 = updateRating(p2, p1, 0.0);           // loss to P1

        // Player 3: wins over P4, then loses to P1 and P2.
        double p3 = 1500.0;
        p3 = updateRating(p3, 1500.0, 1.0);    // win over P4
        p3 = updateRating(p3, p1, 0.0);            // loss to P1
        p3 = updateRating(p3, p2, 0.0);            // loss to P2

        // Player 4: loses to P1, P2 and P3.
        double p4 = 1500.0;
        p4 = updateRating(p4, p1, 0.0);            // loss to P1
        p4 = updateRating(p4, p2, 0.0);            // loss to P2
        p4 = updateRating(p4, p3, 0.0);            // loss to P3

        return new SimulationResult(p1, p2, p3, p4);
    }

    // Scenario 3 (Inverted order version A):
    // Player 4 loses from P1, P2 and P3 first.
    // Player 3 loses to P1 and P2 first and then wins over P4.
    // Player 2 loses to P1 first and then wins over P3 and P4.
    // Player 1 wins over everyone.
    private static SimulationResult simulateScenario3() {
        // In this scenario we compute in an order different from scenario 1.
        double p4 = 1500.0;
        p4 = updateRating(p4, 1500.0, 0.0);  // loss to P1
        p4 = updateRating(p4, 1500.0, 0.0);  // loss to P2
        p4 = updateRating(p4, 1500.0, 0.0);  // loss to P3

        double p3 = 1500.0;
        p3 = updateRating(p3, 1500.0, 0.0);  // loss to P1
        p3 = updateRating(p3, 1500.0, 0.0);  // loss to P2
        p3 = updateRating(p3, p4, 1.0);          // win over P4

        double p2 = 1500.0;
        p2 = updateRating(p2, 1500.0, 0.0);  // loss to P1
        p2 = updateRating(p2, p3, 1.0);          // win over P3
        p2 = updateRating(p2, p4, 1.0);  // win over P4

        double p1 = 1500.0;
        p1 = updateRating(p1, p2, 1.0);  // win over P2
        p1 = updateRating(p1, p3, 1.0);  // win over P3
        p1 = updateRating(p1, p4, 1.0);  // win over P4

        return new SimulationResult(p1, p2, p3, p4);
    }

    // Scenario 4 (Inverted order version B):
    // Player 4 loses from P1, P2 and P3 first.
    // Player 3 wins over P4 first and then loses to P1 and P2.
    // Player 2 wins over P3 and P4 first and then loses to P1.
    // Player 1 wins over everyone.
    private static SimulationResult simulateScenario4() {
        double p4 = 1500.0;
        p4 = updateRating(p4, 1500.0, 0.0);  // loss to P1
        p4 = updateRating(p4, 1500.0, 0.0);  // loss to P2
        p4 = updateRating(p4, 1500.0, 0.0);  // loss to P3

        double p3 = 1500.0;
        p3 = updateRating(p3, p4, 1.0);  // win over P4
        p3 = updateRating(p3, 1500.0, 0.0);  // loss to P1
        p3 = updateRating(p3, 1500.0, 0.0);  // loss to P2

        double p2 = 1500.0;
        p2 = updateRating(p2, p4, 1.0);  // win over P3
        p2 = updateRating(p2, p4, 1.0);  // win over P4
        p2 = updateRating(p2, 1500.0, 0.0);  // loss to P1

        double p1 = 1500.0;
        p1 = updateRating(p1, p2, 1.0);  // win over P2
        p1 = updateRating(p1, p3, 1.0);  // win over P3
        p1 = updateRating(p1, p4, 1.0);  // win over P4

        return new SimulationResult(p1, p2, p3, p4);
    }

    // --- Elo Rating Methods ---

    private static final int DEFAULT_K_FACTOR = 10;

    /**
     * Calculates the expected score for a player.
     * Formula: expected = 1 / (1 + 10^((opponentRating - rating) / 400))
     */
    public static double calculateExpectedScore(double rating, double opponentRating) {
        return 1.0 / (1.0 + pow(10, (opponentRating - rating) / 400.0));
    }

    /**
     * Updates the rating of a player after a match.
     * newRating = currentRating + K * (score - expectedScore)
     * where score is 1.0 for win, 0.0 for loss.
     */
    public static double updateRating(double currentRating, double opponentRating, double score) {
        double expectedScore = calculateExpectedScore(currentRating, opponentRating);
        return currentRating + DEFAULT_K_FACTOR * (score - expectedScore);
    }
}
