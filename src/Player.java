/**
 * Player class
 * <p>
 *     Creates a Player object. Constructor takes one argument, a String for its name.
 *     By default the player will start with 5 health.
 * </p>
 */
public class Player {
    private int health;
    private String name;
    public Player(String name){
        this.name = name;
        this.health = 5;
    }

    /**
     * Shoot method, displays whether the given shot would be a live round or blank
     * and subtracts the value from the target player's health.
     * @param p Player that will be targeted by the shot.
     * @param b int value, should currently be 1 or 0, for live or blank rounds, respectively.
     */
    public void shoot(Player p, int b){
        p.setHealth(p.getHealth() - b);
        if(b == 0) {
            System.out.println("Blank");
            System.out.println(p.toString());
        }
        if(b == 1){
            System.out.println("Live");
            System.out.println(p.toString());
        }
        else System.out.println("Invalid number");
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth(){
        return this.health;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return "Name: " + this.name + " Health: " + this.getHealth();
    }
}
