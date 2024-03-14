import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
       /* ArrayList bullets;
        bullets = Sim.makeHand(5);*/
        String simple = Sim.runSimpleSim(1000);
        String peek = Sim.runPeekSim(1000);
        System.out.println(simple);
        System.out.println(peek);
    }
}
