package characters;

import enums.Sex;

public class Rabbit extends Characters{
    public Rabbit(String name, double borntime, Sex sex, double force, String homeAdress) {
        super(name, borntime, sex,force,homeAdress);
    }

    public void command() {
        System.out.println("Throw!");
    }

    @Override
    public void protrude() {

    }


}
