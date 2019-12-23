package characters;

import enums.Sex;
import games.Games;

import java.util.Random;

public class Bear extends Characters {
    public Bear(String name, double borntime, Sex sex, double force, String homeAdress) {
        super(name, borntime, sex, force,homeAdress);
    }

    public void teach(Characters student, Games game) {
        Random random = new Random();
        boolean flag=false;
        while (!flag) {
            if (random.nextBoolean()) {
                System.out.println(this.name + " научил " + student.name + " играть в " + game.getTitle());
                student.skills.add(game.getTitle());
                flag=true;
            }
            else System.out.println("Обучить не удалось. Герои повторяют попытку");
        }
    }

}


