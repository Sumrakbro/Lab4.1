package com.company;
import characters.*;

import enums.Act_Stat;
import enums.Bump_Size;
import enums.Sex;
import exeption.NotEnoughItemsExeption;
import games.Pushishki;
import games.nonsense;
import objects.Bridge;
import objects.River;
import objects.items.Bump;
import objects.items.Sticks;


public class Main {

    public static void main(String[] args) throws InterruptedException, NotEnoughItemsExeption {
        System.out.println("Вам предстоит поучаствовать в событиях рассказа про Винни и его друзей.(В зависимости от вашего выбора история может изменяться)");
        River river = new River("River", 10,Double.MAX_VALUE);
        Bear bear = new Bear("Vinnie", System.currentTimeMillis(), Sex.MALE, 10, "Vinnie home");
        Pig pig = new Pig("Piglet", System.currentTimeMillis(), Sex.MALE, 5, "Piglet home");
        Rabbit rabbit = new Rabbit("Rabbit", System.currentTimeMillis(), Sex.MALE, 3, "Rabbit home");
        Kenguru little_roo = new Kenguru("Little Roo", System.currentTimeMillis(), Sex.MALE, 6, "Littleroo home");
        Bump littleBump = new Bump("Маленькая шишка", Bump_Size.LITTLE, 0.1);
        Characters Eeyore = new Characters("Иа-Иа", System.currentTimeMillis(), Sex.MALE, 5, "Eeyore home") {
            @Override
            public void swim() {
                System.out.println(this.name + " floats with his feet up ");
            }
        };
        Characters[] characters = new Characters[]{little_roo, pig, rabbit, bear};

        Bridge.Story_Bridge story_bridge = new Bridge.Story_Bridge("Bridge",10000);
        Pushishki pushishki = new Pushishki();
        bear.walk(bear.getHomeAdress());
        bear.think("Кинуть в реку шишки и попытаться угадать, какая выплывет первая-хорошая идея");
        pushishki.start(bear);
        bear.think("Интересная игра. Надо бы научить остальных в нее играть.");
        bear.teach(pig, pushishki);
        bear.teach(little_roo, pushishki);
        bear.teach(rabbit, pushishki);
        pig.say(bear.getName() + " это отличная игра. Давай назовем её в твою честь.");
        bear.think("It's good idea.");
        pushishki.rename("Pushishki");
        rabbit.say("Давайте вместо шишек использовать плаки. Это игра будет называться Пустяки");
        nonsense nonsense = (nonsense) rabbit.createGame(new nonsense("Nonsense", river, story_bridge, "Надо кидать палки в реку.Чья палка первая выплывет из под моста тот и победил!"));
        System.out.println(nonsense.getRulesСontainer());

        river.flow(Act_Stat.lazily);
        for (Characters character : characters) {
            character.getInventory().flush();
            character.getInventory().add_item(new Sticks("stick", 0.09), 100);
        }

        nonsense.setCommand(pig, bear, little_roo, rabbit);
        nonsense.start(rabbit);

        if (nonsense.getWinner().equals(pig)) pig.worry(Act_Stat.terribly);
        Eeyore.swim();
        pig.say("Смотрите Иа-Иа плывет.");
        for (Characters character : characters) {
            character.think();
            character.silent();
        }
    }
}

