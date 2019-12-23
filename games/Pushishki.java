package games;

import characters.Characters;
import enums.Bump_Size;
import exeption.NotEnoughItemsExeption;
import objects.River;
import objects.items.Bump;

import java.util.Random;
import java.util.Scanner;

public class Pushishki extends Games {
    public Pushishki() {
        this.title = "game";
    }

    @Override
    public void start(Characters player) throws InterruptedException, NotEnoughItemsExeption {
        Scanner input = new Scanner(System.in);
        River river = new River("River", 10,10000);
        Bump bigBump = new Bump("Большая шишка", Bump_Size.BIG, 0.3);
        Bump littleBump = new Bump("Маленькая шишка", Bump_Size.LITTLE, 0.1);
        player.getInventory().add_item(bigBump, 11);
        player.getInventory().add_item(littleBump, 11);
        player.throwItem(river, bigBump, bigBump);
        if (this.whichFirst(bigBump, bigBump) == null) {
            System.out.println("Шишки были одинаковы и Винни не смог определить какая шишика приплыла первая.");
            player.think("Надо кидать шишки разных размеров");
        }
        player.getInventory().add_item(bigBump, 2);
        int number;
        int wins = 0;
        player.say("Мне нужна твоя помощь.Помоги угадать какая шишка будет первая. Поможешь мне?(да или нет)");
        String answer = "";

        while (!(answer.equalsIgnoreCase("да") || answer.equalsIgnoreCase("нет"))) {
            answer = input.next();
            if (answer.equalsIgnoreCase("да")) {
                player.say("Отлично. Твоя задача писать одну из двух цифр.(1-большая 0-маленькая)");
                for (int count = 0; count < 10; count++) {
                    answer=input.next();
                    try {
                        number =Integer.parseInt(answer);
                        if(number!=1&&number!=0){System.out.println("Неверное значение. Допустимый ввод \"1\"или\"0\"");count--;continue;}
                        player.throwItem(river, littleBump, bigBump);
                        Bump firstBump = this.whichFirst(littleBump, bigBump);
                    if (((firstBump.getSize() == Bump_Size.BIG) && (number == 1)) ||
                            (firstBump.getSize() == Bump_Size.LITTLE) && (number == 0)) {
                        System.out.println(firstBump + " выплыла первая");
                        System.out.println("Винни выйграл");
                        wins++;
                    } else {
                        System.out.println(firstBump + " выплыла первая");
                        System.out.println("Винни проиграл");
                    }
                    } catch (NumberFormatException e) {
                        System.out.println("Неверный ввод!!!");
                        count--;
                    }
                }
                System.out.println("Винни выйграл " + wins + " раз");
                answer="да";
            } else {
                if (answer.equalsIgnoreCase("нет")) {
                    player.hurt();
                    player.say("Ладно, я и сам справлюсь");
                    player.walk("Играть в одиночестве");
                } else player.say("Я тебя не понял. Поможешь?");
            }
        }
    }




    private void settitle(String title) {
        this.title = title;
    }

    public void rename(String title) {
        System.out.println(this.title + " переименована в " + title);
        settitle(title);
    }

    private Bump whichFirst(Bump bump1, Bump bump2) {
        Random random = new Random();
        if (bump1.getSize() == bump2.getSize()) return null;
        else if (random.nextDouble() <= 0.3) return bump2;
        else return bump1;
    }


    @Override
    protected void end() throws InterruptedException {
        System.out.println(this.title + " завершилась");
    }

}
