/**
 * Sim class
 * <p>
 *     Class used to run simulations of games.
 * </p>
 */


public class Sim {
    private static final int HAND_SIZE = 6;

    /**
     * runSimpleSim method has no decision logic, Player objects simply shoots at their opponents
     * using 5 randomly generated lives or blanks that reloads (regenerates random bullets) every 5 shots.
     * Game ends when a player's health reaches 0.
     * @param count int number of simulations to be run.
     * @return String that displays the amount of wins each player recorded.
     */
    public static String runSimpleSim( int count){
        int p1WinCount = 0;
        int p2WinCount = 0;
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        while(count > 0) {
            System.out.println("New run.");
            p1.setHealth(5);
            p2.setHealth(5);
            System.out.println(p1.toString() + " " + p2.toString());
            while (p1.getHealth() > 0 && p2.getHealth() > 0) {
                int[] bullets = makeHand(HAND_SIZE);
                System.out.print("Bullets: ");
                for(int a : bullets){
                    System.out.printf("%d " , a);
                }
                System.out.println();
                for (int i = 0; i < bullets.length - 1; i+=2) {
                    if(takeTurnSimple(p1,p2,bullets, i) == 1){
                        p1WinCount++;
                        break;
                    }
                    if(takeTurnSimple(p2,p1,bullets,i+1) == 1){
                        p2WinCount++;
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
     * @param count
     * @return
     */
    public static String runSimpleSim2( int count){
        int p1WinCount = 0;
        int p2WinCount = 0;
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        while(count > 0) {
            System.out.println("New run.");
            p1.setHealth(5);
            p2.setHealth(5);
            System.out.println(p1.toString() + " " + p2.toString());
            while (p1.getHealth() > 0 && p2.getHealth() > 0) {
                int[] bullets = makeHand(HAND_SIZE);
                System.out.print("Bullets: ");
                for(int a : bullets){
                    System.out.printf("%d " , a);
                }
                System.out.println();
                for (int i = 0; i < bullets.length - 1; i+=2) {
                    if(takeTurnSimple(p1,p2,bullets, i) == 1){
                        p1WinCount++;
                        break;
                    }
                    if(takeDealerTurn(p2,p1,bullets,i+1) == 1){
                        p2WinCount++;
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
     * @param count number of simulations to be run.
     * @return String that displays the amount of wins each player recorded.
     */
    public static String runPeekSim(int count) {
        int p1WinCount = 0;
        int p2WinCount = 0;
        int sum;
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        while (count > 0) {
            System.out.println("New run.");
            p1.setHealth(5);
            p2.setHealth(5);
            System.out.println(p1.toString() + " " + p2.toString());
            while (p1.getHealth() > 0 && p2.getHealth() > 0) {
                int[] bullets = makeHand(HAND_SIZE);
                System.out.print("Bullets: ");
                for (int a : bullets) {
                    System.out.printf("%d ", a);
                }
                System.out.println();
                for (int i = 0; i < bullets.length - 1; i += 2){
                   if(takeTurnPeek(p1,p2,bullets,i) == 1){
                       p1WinCount++;
                       break;
                   }
                   if(takeTurnPeek(p2,p1,bullets,i+1) == 1){
                       p2WinCount++;
                       break;
                   }
                }
            }
            count--;
        }
        return "Peek sim results: p1 wins: " + p1WinCount + " p2 wins: " + p2WinCount;
    }

    public static String runPeekSim2(int count){
        int p1WinCount = 0;
        int p2WinCount = 0;
        int sum;
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        while (count > 0) {
            System.out.println("New run.");
            p1.setHealth(5);
            p2.setHealth(5);
            System.out.println(p1.toString() + " " + p2.toString());
            while (p1.getHealth() > 0 && p2.getHealth() > 0) {
                int[] bullets = makeHand(HAND_SIZE);
                System.out.print("Bullets: ");
                for (int a : bullets) {
                    System.out.printf("%d ", a);
                }
                System.out.println();
                for (int i = 0; i < bullets.length - 1; i += 2){
                    if(takeTurnPeek(p1,p2,bullets,i) == 1){
                        p1WinCount++;
                        break;
                    }
                    if(takeDealerTurn(p2,p1,bullets,i+1) == 1){
                        p2WinCount++;
                        break;
                    }
                }
            }
            count--;
        }

        return "Peek sim2 results: p1 wins: " + p1WinCount + " p2 wins: " + p2WinCount;
    }

    public static int[] makeHand(int size){
        int[] bullets = new int[size];
        for(int i = 0;i<bullets.length-1;i++){
            if((int)(Math.random() * 100 % 2) != 0){
                bullets[i] = 1;
            }
            else{
                bullets[i] = 0;
            }
        }
        return bullets;
    }

    public static int sumHand(int[] a, int index){
        int sum = 0;
        for(int i = index;i<a.length-1;i++){
            if(a[i] == 0){
                sum++;
            }
        }
        return sum;
    }

    /**
     * Takes a turn with no logic, only targeting the opponent.
     * @param p1 Player that will take the shot.
     * @param p2 Player that is targeted.
     * @param bullets int array for bullets.
     * @param index which index of the array, chooses which bullet to use.
     * @return int 1 if the shot lowered the targeted Player's health to 0. Returns 0 if the targeted Player survived the shot.
     */
    public static int takeTurnSimple(Player p1, Player p2, int[] bullets, int index){
        p1.shoot(p2, bullets[index]);
        if (p2.getHealth() == 0) {
            return 1;
        }
        else return 0;
    }
    /**
     * Takes a turn with basic logic, if there are 3 or more blanks target self and if it is a blank, take another turn.
     * If less than 3 blanks, target opponent.
     * @param p1 Player that will take the shot.
     * @param p2 Opponent player.
     * @param bullets int array for bullets.
     * @param index which index of the array, chooses which bullet to use.
     * @return int 1 if the shot lowered the targeted Player's health to 0. Returns 0 if the targeted Player survived the shot.
     */

    public static int takeTurnPeek(Player p1, Player p2, int[]bullets, int index){
        if(index >= bullets.length-1)
            if(p1.getHealth() == 0)
                return 1;
        else return 0;
        if(sumHand(bullets, index) >= 3){
            p1.shoot(p1,bullets[index]);
            if(bullets[index] == 0){
                takeTurnPeek(p1,p2,bullets,index+1);
            }
        }
        return takeTurnSimple(p1,p2,bullets,index);
    }

    /**
     * Dealer turn method, apparently closer to the Dealer's AI, randomly choosing to target itself or its opponent.
     * @param p1 Player that will take the shot.
     * @param p2 Opponent player.
     * @param bullets int array for bullets.
     * @param index which index of the array, chooses which bullet to use.
     * @return int 1 if the shot lowered the targeted Player's health to 0. Returns 0 if the targeted Player survived the shot.
     */
    public static int takeDealerTurn(Player p1, Player p2, int[] bullets, int index){
        if((int)(Math.random() * 100 % 2) != 0) {
          return  takeTurnSimple(p1, p2, bullets,index);
        }
        else return takeTurnSimple(p1,p1,bullets,index);
    }
}
