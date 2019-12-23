package objects;

public abstract class Objects {
    protected double weight;
    protected String title;
    protected double width;

    public double getWidth() {
        return width;
    }

    public Objects(String title, double weight) {
        this.title = title;
        this.weight = weight;
    }

    public String getTitle() {
        return title;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int hashCode() {
        int hascode;
        hascode = this.title.hashCode();
        hascode = (int) this.weight + hascode;
        return hascode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Objects object = (Objects) obj;
        return (this.title == object.title) & (this.weight == object.weight);
    }

    @Override
    public String toString() {
        return this.title;
    }
}
