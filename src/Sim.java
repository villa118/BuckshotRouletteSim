/**
 * Sim class
 * <p>
 *     Class used to run simulations of games.
 * </p>
 */


public class Sim {
    private static final int HAND_SIZE = 5;

    /**
     * runSimpleSim method has no decision logic, Player objects simply shoots at their opponents
     * using 5 randomly generated lives or blanks that reloads (regenerates random bullets) every 5 shots.
     * Game ends when a player's health reaches 0.
     *
     * @param count int number of simulations to be run.
     * @return String that displays the amount of wins each player recorded.
     */
    public static String runSimpleSim(int count) {
        int p1WinCount = 0;
        int p2WinCount = 0;
        int shotsFired;
        Player p1 = new Player("Player");
        Player p2 = new Player("Dealer");
        while (count > 0) {
            startNewGame(p1, p2, 5);
            while (p1.getHealth() > 0 && p2.getHealth() > 0) {
                int[] bullets = reload(HAND_SIZE);
                shotsFired = 0;
                displayReload(bullets);
                while (shotsFired <= bullets.length - 1) {
                    shotsFired += (takeTurnSimple(p1, p2, bullets, shotsFired));
                    if (p2.getHealth() == 0) {
                        p1WinCount++;
                        displayGameOver(p1, p2, p1WinCount);
                        break;
                    }
                    shotsFired += (takeTurnSimple(p2, p1, bullets, shotsFired));
                    if (p1.getHealth() == 0) {
                        p2WinCount++;
                        displayGameOver(p2, p1, p2WinCount);
                        break;
                    }
                }
            }
            count--;
        }
        return "Simple sim results: p1 wins: " + p1WinCount + " p2 wins: " + p2WinCount;

    }

    /**
     * This method runs a simulation in which the player only targets the opponent,
     * and the opponent randomly chooses to target itself or the opponent.
     *
     * @param count amount of simulations to be run.
     * @return String that displays the amount of wins each player recorded.
     */
    public static String runSimpleSim2(int count) {
        int p1WinCount = 0;
        int p2WinCount = 0;
        int shotsFired;
        Player p1 = new Player("Player");
        Player p2 = new Player("Dealer");
        while (count > 0) {
            startNewGame(p1, p2, 5);
            while (p1.getHealth() > 0 && p2.getHealth() > 0) {
                int[] bullets = reload(HAND_SIZE);
                shotsFired = 0;
                displayReload(bullets);
                while (shotsFired <= bullets.length - 1) {
                    shotsFired += (takeTurnSimple(p1, p2, bullets, shotsFired));
                    if (p2.getHealth() == 0) {
                        p1WinCount++;
                        displayGameOver(p1, p2, p1WinCount);
                        break;
                    }
                    shotsFired += (takeDealerTurn(p2, p1, bullets, shotsFired));
                    if (p1.getHealth() == 0) {
                        p2WinCount++;
                        displayGameOver(p2, p1, p2WinCount);
                        break;
                    }
                }
            }
            count--;
        }
        return "Simple sim2 results: p1 wins: " + p1WinCount + " p2 wins: " + p2WinCount;

    }

    /**
     * runPeekSim method has simple logic to decide whether to target self or opponent
     * based on how many blanks are left in the chamber. Otherwise, behaves the same as the simpleSim
     *
     * @param count number of simulations to be run.
     * @return String that displays the amount of wins each player recorded.
     */
    public static String runPeekSim(int count) {
        int p1WinCount = 0;
        int p2WinCount = 0;
        int shotsFired;
        Player p1 = new Player("Player");
        Player p2 = new Player("Dealer");
        while (count > 0) {
            startNewGame(p1, p2, 5);
            while (p1.getHealth() > 0 && p2.getHealth() > 0) {
                int[] bullets = reload(HAND_SIZE);
                shotsFired = 0;
                displayReload(bullets);
                while (shotsFired <= bullets.length - 1) {
                    shotsFired += (takeTurnPeek(p1, p2, bullets, shotsFired));
                    if (p2.getHealth() == 0) {
                        p1WinCount++;
                        displayGameOver(p1, p2, p1WinCount);
                        break;
                    }
                    shotsFired += (takeTurnPeek(p2, p1, bullets, shotsFired));
                    if (p1.getHealth() == 0) {
                        p2WinCount++;
                        displayGameOver(p2, p1, p2WinCount);
                        break;
                    }
                }
            }
            count--;
        }
        return "Peek sim results: p1 wins: " + p1WinCount + " p2 wins: " + p2WinCount;
    }

    /**
     * This sim uses the simple peek logic, where if the majority of bullets left are blanks, you target yourself
     *
     * @param count amount of simulations to be run.
     * @return String that displays the amount of wins each player recorded.
     */
    public static String runPeekSim2(int count) {
        int p1WinCount = 0;
        int p2WinCount = 0;
        int shotsFired;
        Player p1 = new Player("Player");
        Player p2 = new Player("Dealer");
        while (count > 0) {
            startNewGame(p1, p2, 5);
            while (p1.getHealth() > 0 && p2.getHealth() > 0) {
                int[] bullets = reload(HAND_SIZE);
                shotsFired = 0;
                displayReload(bullets);
                while (shotsFired <= bullets.length - 1) {
                    shotsFired += (takeTurnPeek(p1, p2, bullets, shotsFired));
                    if (p2.getHealth() == 0) {
                        p1WinCount++;
                        displayGameOver(p1, p2, p1WinCount);
                        break;
                    }
                    shotsFired += (takeDealerTurn(p2, p1, bullets, shotsFired));
                    if (p1.getHealth() == 0) {
                        p2WinCount++;
                        displayGameOver(p2, p1, p2WinCount);
                        break;
                    }
                }
            }
            count--;
        }

        return "Peek sim2 results: p1 wins: " + p1WinCount + " p2 wins: " + p2WinCount;
    }

    /**
     * This method displays when a new game state is created, resetting the players' health to the specified number.
     *
     * @param p1     Player object
     * @param p2     Player object
     * @param health int value used to set the players' health
     */
    public static void startNewGame(Player p1, Player p2, int health) {
        System.out.println("*****************NEW GAME*****************");
        p1.setHealth(health);
        p2.setHealth(health);
        System.out.println(p1 + " " + p2);
    }

    /**
     * shootSelf method, simply has the given Player object target itself.
     *
     * @param p1      Player that is taking the shot.
     * @param bullets int array for bullets.
     * @param index   which index of the array, chooses which bullet to use.
     * @return int  the return value of 1 or 0 is used to keep track of how many shots have been taken.
     */

    public static int shootSelf(Player p1, int[] bullets, int index) {
        if (index > bullets.length - 1 || p1.getHealth() == 0) {
            return 0;
        }
        p1.shoot(p1, bullets[index]);
        return 1;
    }

    /**
     * Displays the values in the given array
     *
     * @param bullets int[] to display
     */
    public static void displayReload(int[] bullets) {
        System.out.println("Reloading...");
        System.out.print("Bullets: [ ");
        for (int a : bullets) {
            System.out.printf("%d ", a);
        }
        System.out.print("]");
        System.out.println();
    }

    public static void displayGameOver(Player winner, Player loser, int wins) {
        System.out.println();
        System.out.println(loser.getName() + " has been killed, " + winner.getName() + " has won " + wins + " times.");
        System.out.println("*****************GAME OVER*****************");
        System.out.println();
    }

    /**
     * Creates an array of the given size, randomly filled with 0s or 1s.
     *
     * @param size int size of the array to be made.
     * @return int[] filled array.
     */
    public static int[] reload(int size) {
        int[] bullets = new int[size];
        for (int i = 0; i < bullets.length; i++) {
            //generates a random number from 0-100, if it's odd, the next number in the array will be a 1, else 0
            if (Math.random() < 0.5) {
                bullets[i] = 1;
            }
            else {
                bullets[i] = 0;
            }
        }
        return bullets;
    }

    /**
     * Counts the amount of blanks (0s) left in the given array.
     *
     * @param a     array to check
     * @param index starting index of the search.
     * @return int total sum of the blanks found in the array.
     */
    public static int sumHand(int[] a, int index) {
        int sum = 0;
        for (int i = index; i < a.length; i++) {
            if (a[i] == 0) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * Takes a turn with no logic, only targeting the opponent, except in the case of the last shot where it takes the optimal choice.
     *
     * @param p1      Player that will take the shot.
     * @param p2      Player that is targeted.
     * @param bullets int array for bullets.
     * @param index   which index of the array, chooses which bullet to use.
     * @return int  the return value of 1 or 0 is used to keep track of how many shots have been taken.
     */
    public static int takeTurnSimple(Player p1, Player p2, int[] bullets, int index) {
        //take no shot if out of bounds or one of the players is dead.
        if (index > bullets.length - 1 || p1.getHealth() == 0 || p2.getHealth() == 0) {
            return 0;
        }
        //The last round in the chamber, by process of elimination is known, so take the optimal choice.
        if (index == bullets.length - 1) {
            if (bullets[index] == 1) {
                p1.shoot(p2, bullets[index]);
                return 1;
            }
            return shootSelf(p1, bullets, index);
        }
        p1.shoot(p2, bullets[index]);
        return 1;
    }

    /**
     * Takes a turn with basic logic, playing the odds, if there are >50% blanks left target self and if it is a blank, take another turn.
     * If less than 50% blanks, target opponent. When there is only a single shot left, uses the optimal choice.
     *
     * @param p1      Player that will take the shot.
     * @param p2      Opponent player.
     * @param bullets int array for bullets.
     * @param index   which index of the array, chooses which bullet to use.
     * @return int    the return value of 1 or 0 is used to keep track of how many shots have been taken.
     */

    public static int takeTurnPeek(Player p1, Player p2, int[] bullets, int index) {
        //take no shot if out of bounds or one of the players is dead.
        if (index > bullets.length - 1 || p2.getHealth() == 0 || p1.getHealth() == 0) {
            return 0;
        }
        //The last round in the chamber, by process of elimination is known, so take the optimal choice.
        if (index == bullets.length - 1) {
            if (bullets[index] == 1) {
                return takeTurnSimple(p1, p2, bullets, index);
            }
            return shootSelf(p1, bullets, index);
        }
        //counts the blanks left based on how many shots have been taken, and makes a decision.
        if (sumHand(bullets, index) >= bullets.length / 2) {
            p1.shoot(p1, bullets[index]);
            if (bullets[index] == 0) {
                return 1 + takeTurnPeek(p1, p2, bullets, index + 1);
            }
            return 1;
        }
        return takeTurnSimple(p1, p2, bullets, index);
    }

    /**
     * Dealer turn method, apparently closer to the Dealer's AI, randomly choosing to target itself or its opponent,
     * except for the last round where it takes the optimal choice based on if it is a live or blank.
     *
     * @param p1      Player that will take the shot.
     * @param p2      Opponent player.
     * @param bullets int array for bullets.
     * @param index   which index of the array, chooses which bullet to use.
     * @return int   the return value of 1 or 0 is used to keep track of how many shots have been taken.
     */
    public static int takeDealerTurn(Player p1, Player p2, int[] bullets, int index) {
        //take no shot if out of bounds or one of the players is dead.
        if (index > bullets.length - 1 || p1.getHealth() == 0 || p2.getHealth() == 0) {
            return 0;
        }
        //The last round in the chamber, by process of elimination is known, so take the optimal choice.
        if (index == bullets.length - 1) {
            if (bullets[index] == 1) {
                return takeTurnSimple(p1, p2, bullets, index);
            }
            else return shootSelf(p1, bullets, index);
        }
        //Dealer randomly chooses to shoot the player or itself
        boolean shootSelf = Math.random() < 0.5;
        // Turn always ends
        if (shootSelf) {
            p1.shoot(p1, bullets[index]);  // Dealer shoots itself
            if (bullets[index] == 0) {
                return 1 + takeDealerTurn(p1, p2, bullets, index + 1);  // Blank: take another turn
            }
        }
        else {
            p1.shoot(p2, bullets[index]);  // Dealer shoots the opponent
        }
        return 1;  // Live round: end turn
    }
}
