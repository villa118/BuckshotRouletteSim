import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String simple = Sim.runSimpleSim(10000);
        String peek = Sim.runPeekSim(10000);
        System.out.println(simple);
        System.out.println(peek);
    }
}
