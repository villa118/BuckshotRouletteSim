/**
 * Sim class
 * <p>
 *     Class used to run simulations of games.
 * </p>
 */


import java.util.ArrayList;

public class Sim {

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
                int[] bullets = makeHand(5);
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
                int[] bullets = makeHand(5);
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

    public static int takeTurnSimple(Player p1, Player p2, int[] bullets, int index){
        p1.shoot(p2, bullets[index]);
        if (p2.getHealth() == 0) {
            return 1;
        }
        else return 0;
    }


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
}
