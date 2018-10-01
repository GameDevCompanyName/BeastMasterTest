package Enemy;

public class EnemyTest {

    public static void main(String[] args) {

        float[] diff = {0.5f, 1.0f, 5.0f, 15.0f};

        for (float fl: diff) {
            System.out.println("----- DIFF = " + fl + " -----");
            for (int i = 0; i < 10; i++){
                System.out.println(Enemy.generate(fl));
            }
            System.out.println("-------------------");
        }

    }
}
