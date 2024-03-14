public class Player {
    private int health;
    private String name;
    public Player(String name){
        this.name = name;
        this.health = 5;
    }


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
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth(){
        return this.health;
    }
    public String toString(){
        return "Name: " + this.name + " Health: " + this.getHealth();
    }
}
