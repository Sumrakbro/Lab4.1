package objects.items;

import enums.Bump_Size;

import java.util.Objects;

public class Bump extends Items{
            Bump_Size size;
            public  Bump(String title,Bump_Size size,double weight){
                super(title,weight);
                this.size=size;
            }

    public Bump_Size getSize() {
        return size;
    }

    @Override
    public void Fall(Objects purpose) {

    }

    @Override
    public void Drift() throws InterruptedException {

    }

    @Override
    public String toString() {
        return this.title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Bump bump = (Bump) obj;
        return ((this.title == bump.title)&&(this.size==bump.size)&&(this.weight==bump.weight));
    }
}
