package Enemy;

import java.util.Random;

public class Enemy {

    private EnemyType type;
    private int str;
    private int agi;
    private int hp;
    private int atk;

    public Enemy(EnemyType type, int str, int agi) {
        this.type = type;
        this.str = str;
        this.agi = agi;
        hp = (int) (Math.pow(str, 0.9)/1.5 + 15);
        atk = (int) (Math.pow((agi * 0.3) + (str * 0.12), 0.68) + 3);
    }

    public static Enemy generate(Float loccationMultiplyer){

        EnemyType type = null;
        Float str = null;
        Float agi = null;

        Random random = new Random();
        int typeChooser = random.nextInt(3);

        switch (typeChooser){
            case 1:
                type = EnemyType.BEAST;
                str = 5.0f + random.nextInt(8);
                agi = str/(0.2f + random.nextFloat()*0.3f);
                break;
            case 2:
                type = EnemyType.BANDIT;
                str = 10.0f + random.nextInt(9);
                agi = str/(0.8f + random.nextFloat()*0.3f);
                break;
            case 0:
                type = EnemyType.KNIGHT;
                str = 14.0f + random.nextInt(10);
                agi = str/(1.4f + random.nextFloat()*0.6f);
                break;
            default:
                System.out.println("Чё за хуйня бля");
                System.exit(0);
                break;
        }

        str *= 10 * loccationMultiplyer;
        agi *= 10 * loccationMultiplyer;

        return new Enemy(type, Math.round(str), Math.round(agi));

    }

    @Override
    public String toString() {
        return "Enemy.Enemy{" +
                "\n\ttype = " + type +
                "\n\tstr = " + str +
                "\n\tagi = " + agi +
                "\n\thp = " + hp +
                "\n\tatk = " + atk +
                "\n}";
    }
}
