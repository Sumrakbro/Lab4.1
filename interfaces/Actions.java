package interfaces;

import enums.Act_Stat;
import objects.Bridge;

public interface Actions {
    void rush(Bridge.Story_Bridge bridge, Act_Stat act_stat);

    void Wait() throws InterruptedException;

    void walk(String purpose);

    void walk();

    void say(String s);

    void think(String s);

    void think();

    void silent();

    void swim();
}
