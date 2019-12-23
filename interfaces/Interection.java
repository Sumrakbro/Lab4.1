package interfaces;

import enums.Act_Stat;
import exeption.NotEnoughItemsExeption;
import objects.Objects;
import objects.items.Items;

public interface Interection {
    void lookAt(Objects object, Act_Stat act_stat) throws InterruptedException;

    void see(Objects object) throws InterruptedException;

    boolean throwItem(Items item, Objects purpose, double angle) throws NotEnoughItemsExeption;

    void throwItem(Objects purpose, Items... item);
}
