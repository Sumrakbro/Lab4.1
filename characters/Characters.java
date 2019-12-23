package characters;


import enums.Act_Stat;
import enums.Sex;
import exeption.NotEnoughItemsExeption;
import exeption.OneOfCharactersCharacteristicWrong;
import games.Games;
import interfaces.Actions;
import interfaces.Interection;
import inventory.Inventory;
import objects.Bridge;
import objects.Objects;
import objects.items.Items;

import java.util.ArrayList;

import static java.lang.Math.*;
import static java.lang.Thread.sleep;

public abstract class Characters implements Interection, Actions {
    protected String name;
    protected double borntime = -1;
    protected Sex sex;
    protected Inventory inventory;
    protected double stress_level;
    protected double mood_level=100;
    protected double force;
    protected ArrayList<String> skills;
    protected String homeAdress;

    protected Characters(String name, double borntime, Sex sex, double force, String homeAdress) {
        this.name = name;
        this.borntime = borntime;
        this.sex = sex;
        this.force = force;
        this.inventory = new Inventory(this.name);
        this.skills = new ArrayList<>();
        this.homeAdress = homeAdress;
        try {
            if ((this.name == null) || (this.name == "") || (this.force == 0) || (this.sex == null) || (this.borntime == -1))
                throw new OneOfCharactersCharacteristicWrong("Проверьте характеристики созданных героев");
        } catch (OneOfCharactersCharacteristicWrong e) {
            System.out.println("ПРОВЕРЬТЕ ХАРАКТЕРИСТИКИ ГЕРОЕВ, КОТОРЫЙ ВЫ СОЗДАЕТЁ!!!" + "\nOneOfCharactersCharacteristicWrong");
            System.exit(1);
        }
    }

    public void upforce() {
        this.force = force + 5;
    }

    public String GetName() {
        return this.name;
    }

    public double GetBorntime() {
        return this.borntime;
    }

   public Games createGame(Games game){
      System.out.println(this.name+" придумал "+game.getTitle());
        return game;
   }
    @Override
    public void lookAt(Objects object, Act_Stat act_stat) throws InterruptedException {
        System.out.println(this.name + " " + Act_Stat.waiting + " looks at " + object.getTitle());
        sleep(2000);

    }

    public void worry(Act_Stat act_stat) {

    }

    @Override
    public void  throwItem(Objects purpose, Items... item) {
        {
            if (this.inventory.check(item[0]) && this.inventory.check(item[1])) {
                System.out.println(this.name + " throw " + item[0].getTitle() + " and " + item[1].getTitle() + " в " + purpose.getTitle());
                this.inventory.remove(item[0], 1);
                this.inventory.remove(item[1], 1);
            } else System.out.println("предмета нет в инвентаре");
        }
    }

    @Override
    public void walk() {
        System.out.println(this.name + " куда-то идет");
    }

    @Override
    public void walk(String purpose) {
        System.out.println(this.name + " идёт " + purpose);
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public boolean throwItem(Items item, Objects purpose, double angle) throws NotEnoughItemsExeption {
        if (this.inventory.check(item)) {
            System.out.println(this.name + " throw " + item.getTitle() + " " + purpose.getTitle());
            this.inventory.remove(item, 1);
            double speed = (this.force * 0.1) / item.getWeight();
            double distance = pow(speed, 2) * sin(2 * angle) / 9.81;
            if (distance > purpose.getWidth()) {
                System.out.println(this.name + " промахнулся");
                return false;
            } else return true;
        } else throw new NotEnoughItemsExeption("предмета " + item.getTitle() + " нет в инвентаре " + this.name);
    }

    public String getHomeAdress() {
        return homeAdress;
    }

    public String getName() {
        return name;
    }

    @Override
    public void rush(Bridge.Story_Bridge bridge, Act_Stat act_stat) {
        System.out.println(this.name + " " + act_stat + " rush to" + bridge.getLeftSide());
    }

    @Override
    public void swim() {

    }

    @Override
    public void Wait() throws InterruptedException {
        System.out.println(this.name + " ожидает");
    }

    public void see(Objects object) throws InterruptedException {
        System.out.println(this.name + " sees " + object.getTitle());
        sleep(1000);
    }

    public void protrude() {

    }

    @Override
    public int hashCode() {
        int result;
        result = 31 * this.name.hashCode();
        result = result * 31 + (int) this.borntime;
        result = result * 31 + this.sex.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Characters character = (Characters) obj;
        return (this.name == character.name) & (this.sex == character.sex) & (this.borntime == character.borntime);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void silent() {
        System.out.println(this.name + " замолчал");
    }

    @Override
    public void think() {
        System.out.println(this.name + " задумался");
    }

    @Override
    public void think(String s) {
        {
            System.out.println(this.name + " думает:\n\t" + s);
        }
    }

    @Override
    public void say(String s) {
        System.out.println(this.name+":"+"\n\t"+ s);
    }
    public void hurt(){
        System.out.println(this.name+" обиделся!!!");
        this.mood_level=this.mood_level-40;
        System.out.println(this.name+" mood level: "+ this.mood_level);
    }
    public void upMoodlevel(){
        this.mood_level=this.mood_level+20;
        if(this.mood_level>100) System.out.println(this.name+ " безумно счастлив!");
    }
}