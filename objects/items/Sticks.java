package objects.items;

import objects.Objects;

import static java.lang.Thread.sleep;

public class Sticks extends Items {
    public Sticks(String title, double weight) {
        super(title,weight);
    }

    @Override
    public void Fall(Objects purpose) {
        System.out.println(this.title +" fell in " + purpose.getTitle());
    }

    @Override
    public void Fall(java.util.Objects purpose) {

    }

    @Override
    public void Drift() throws InterruptedException {
        System.out.println(this.title + " drift");sleep(1000);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode() * 31;
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
        Sticks stick = (Sticks) obj;
        return ((this.title == stick.title)&&(this.weight==stick.weight));
    }
}
