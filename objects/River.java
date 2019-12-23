package objects;





import enums.Act_Stat;

import static java.lang.Thread.sleep;

public class River extends Objects {
    double width;

    public River(String title, double width,double weight) {
        super(title,weight);
        this.width=width;
    }

    public void flow(Act_Stat flow_stat) throws InterruptedException {
        System.out.println(this.title + " flows " + flow_stat);
        sleep(3000);
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
        River river = (River) obj;
        return this.title == river.title;
    }
}
