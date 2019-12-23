package inventory;

import objects.items.Items;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Items> inventory;
    private ArrayList<Integer> itemsCountInInventory;
    private String owner;

    public ArrayList<Items> getInventory() {
        return inventory;
    }

    public Inventory(String owner) {
        this.inventory = new ArrayList<>();
        this.itemsCountInInventory = new ArrayList<>();
        this.owner=owner;
    }
    public void add_item(Items item, int count) {
        if (this.inventory.contains(item)) {
            int index = this.inventory.indexOf(item);
            this.itemsCountInInventory.set(index, this.itemsCountInInventory.get(index) + count);
            System.out.println("Предмет "+item.getTitle()+" увеличил количество");
        } else {
            this.inventory.add(item);
            this.itemsCountInInventory.add(count);
            System.out.println("Предмет "+item.getTitle()+" добавлен в инвентарь " + this.owner);
        }
       // System.out.println("Предмет "+item.getTitle()+" добавлен в инвентарь " + this.owner);
    }
    public boolean check(Items item){
        if(this.inventory.contains(item)){
            if(this.itemsCountInInventory.get(this.inventory.indexOf(item))>1) return true;
        return  false;
        }
        else return false;
    }

    public void remove(Items item,int count){
        if(this.itemsCountInInventory.get(this.inventory.indexOf(item))-count>=1){
            this.itemsCountInInventory.set(this.inventory.indexOf(item),this.itemsCountInInventory.get(this.inventory.indexOf(item))-count);
        }
     else {
            this.itemsCountInInventory.remove(this.inventory.indexOf(item));
            this.inventory.remove(item);
        }
     }
    public int getIndex(Items item){
        return this.inventory.indexOf(item);
    }
    public Items get(int index){
        return this.inventory.get(index);
    }

    @Override
    public String toString() {
        String result=" Инветарь "+this.owner;
        if(this.inventory.size()>0) {
            result=result+" содержит: "+"\n";
            int length=result.length();
            for (int index = 0; index < inventory.size(); index++) {
               if(this.itemsCountInInventory.get(index)-1>0)
                result = result + (index + 1) + ":" + this.inventory.get(index) + " x" + (this.itemsCountInInventory.get(index)-1) + "\n";
            }
            if(result.length()==length) result="Инвентарь "+this.owner+" пуст.";
            return result;
        }
    return  result+" пуст";
    }
    public void flush(){
        this.inventory.clear();
        this.itemsCountInInventory.clear();
    }
}
