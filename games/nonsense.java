package games;

import objects.Bridge;
import characters.Rabbit;
import enums.Act_Stat;
import enums.Bump_Size;
import exeption.NotEnoughItemsExeption;
import objects.Objects;
import objects.items.Bump;
import objects.items.Sticks;
import characters.*;

import java.util.*;

import static java.lang.Thread.sleep;

public class nonsense extends Pushishki {
    Objects startPoint;
    Objects endPoint;
    private ArrayList<Characters> sequence_moves = new ArrayList<>();

    public nonsense(String title, Objects startPoint, Objects endPoint, String rules) throws NotEnoughItemsExeption, InterruptedException {
        this.title = title;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.rules = rules;
    }

    protected Characters whoWinner() throws InterruptedException {
        Random random = new Random();
        int index;
        double random_double = random.nextDouble();
        if ((random_double > 0.6 && this.sequence_moves.size() > 0) || (this.sequence_moves.size() == 1)) {
            this.sequence_moves.get(0).upMoodlevel();
            return this.winner = this.sequence_moves.get(0);
        }
        double powForSmallerProbability;
        double powForBiggerProbability;
        for (index = this.sequence_moves.size() - 1; index >= 0; index--) {
            powForSmallerProbability = 1;
            powForBiggerProbability = index;
            System.out.println(this.sequence_moves + " " + this.sequence_moves.size());
            if (index + 1 == this.sequence_moves.size() &&
                    this.sequence_moves.size() > 1) powForSmallerProbability = 0;
            if (random_double <= Math.pow(0.4, powForBiggerProbability) * Math.pow(0.6, powForSmallerProbability)) {
                this.winner = this.sequence_moves.get(index);
                break;
            }

        }
        this.winner.upMoodlevel();
        return winner;
    }

    @Override
    public void start(Characters player) throws InterruptedException, NotEnoughItemsExeption {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        System.out.println(this.title + " start");
        System.out.print(player.getName() + ":");
        Rabbit playerr = (Rabbit) player;
        playerr.command();
        sleep(1000);
        sleep(100);
        System.out.println("Team of players in " + this.title + " " + this.getCommand());
        int correction = 0;
        int[] misses = new int[]{-1, -1, -1, -1};
        int character_index;
        int item_index;
        String in = "";
        int added_item_index;
        ArrayList<Integer> inputs = new ArrayList<>();
        for (int number = 0; number < this.command.size(); number++) {
            System.out.println("Какой герой кинет предмет сейчас?(доступны индексы от 1 до 4)");
            in = input.next();
            try {
                character_index = Integer.parseInt(in);
                if (character_index < 1 || character_index > 4) {
                    inputs.remove((Integer) character_index);
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод!!");
                --number;
                continue;
            }
            character_index = Integer.parseInt(in);
            if (inputs.contains(character_index)) {
                System.out.println("Этот герой уже кинул предмет!!!");
                --number;
                continue;
            } else {
                inputs.add(character_index);
                Characters pl = this.command.get(character_index - 1);
                try {
                    System.out.println(this.command.get(character_index-1).getInventory());
                    System.out.println("Какой предмет герой должен кинуть?(1-палка;2-маленькая шишка;3-большая шишка)");
                    item_index = input.nextInt();
                    if (item_index < 1 || item_index > 3) {
                        System.out.println("Неверный ввод!!!");
                        --number;
                        inputs.remove((Integer) character_index);
                        continue;
                    }

                    if (!(pl.getInventory().get(item_index - 1) instanceof Sticks)) {
                        System.out.println("По правилам игры " + this.title + " нельзя использовать этот предмет");
                        System.out.println("Уверены ли вы в своём действии?");
                        input.nextLine();
                        String answer = input.nextLine();
                        if (answer.equalsIgnoreCase("да")) {
                            pl.throwItem(pl.getInventory().get(item_index - 1), this.startPoint, random.nextDouble() * 90);
                            System.out.println(pl.getName() + " больше не участвует в игре");
                            continue;
                        } else {
                            System.out.println("Неверный ввод!!!");
                            --number;
                            inputs.remove((Integer) character_index);
                            continue;
                        }
                    }
                        if (!pl.throwItem(pl.getInventory().get(item_index - 1), this.startPoint, random.nextDouble() * 90)) {
                            System.out.println(pl.getName() + " больше не участвует в игре");
                            pl.rush((Bridge.Story_Bridge) this.endPoint, Act_Stat.scampering);
                            misses[number] = character_index - 1 - correction;
                            correction++;
                        } else {
                            this.sequence_moves.add(this.command.get(character_index - 1));
                        }

                    } catch(NotEnoughItemsExeption | IndexOutOfBoundsException e){
                        inputs.remove((Integer) character_index);
                        System.out.println("предмета  нет в инвентаре " + pl.getName());
                        System.out.println("Добавить предмет?");
                        String answer = input.next();
                        if (answer.equalsIgnoreCase("да")) {
                            System.out.println("Какой предмет вы хотите добавить?(1-палка;2-маленькая шишка;3-большая шишка)");
                            added_item_index = input.nextInt();
                            switch (added_item_index) {
                                case 1:
                                    Sticks stickToAdd = new Sticks("stick", 0.04);
                                    pl.getInventory().add_item(stickToAdd, 20);
                                    break;
                                case 2:
                                    Bump bumpToAdd = new Bump("Little bump", Bump_Size.LITTLE, 0.03);
                                    pl.getInventory().add_item(bumpToAdd, 20);
                                    break;
                                case 3:
                                    bumpToAdd =new Bump("Big bump", Bump_Size.BIG, 0.04);
                                    pl.getInventory().add_item(bumpToAdd, 20);
                                    break;
                            }
                        }
                        number--;
                    input.nextLine();
                    } catch(InputMismatchException e){
                        System.out.println("Неверный ввод (-__-)!!. Вводите цифры от 1 до 3. ");
                        inputs.remove(inputs.lastIndexOf(character_index));
                        --number;
                        input.nextLine();
                    }
                }
            }


            if (this.sequence_moves.size() == 0) {
                System.out.println("Все герои промахнулись!\nСделать ещё попытку?");
                if (input.next().equalsIgnoreCase("да")) this.start(this.command.get(3));
                else {
                    System.out.println("Тогда история Винни и его друзей завершается.");
                    System.exit(1);
                }
            }
            System.out.println(this.sequence_moves + " " + this.sequence_moves.size());
            for (
                    Characters character : this.sequence_moves) {
                character.getInventory().get(0).Fall(this.startPoint);
                character.getInventory().get(0).Drift();
                character.rush((Bridge.Story_Bridge) this.endPoint, Act_Stat.scampering);
                character.lookAt(this.startPoint, Act_Stat.waiting);
            }
            for (Characters character : this.sequence_moves) {
                if (character instanceof Kenguru) {
                    ((Kenguru) character).jump(Act_Stat.clockwork);
                    ((Kenguru) character).squek("Stick, stick, hurry! Stick, stick, hurry!");
                }
            }
            for (Characters character : this.sequence_moves) {
                character.Wait();
            }
            System.out.println("ONE ETERNITY LATER");
            this.whoWinner().see(new Sticks("stick", 0.04));
            System.out.println(this.winner + " win");
            this.end();
        }

        @Override
        protected void end () throws InterruptedException {
            System.out.println(this.title + " finish");
            sleep(3000);
        }

    }
