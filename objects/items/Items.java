package objects.items;


import interfaces.ObjectsAction;

import objects.*;
public abstract class Items extends Objects implements ObjectsAction {


    public Items(String title, double weight) {
        super(title,weight);
    }

    @Override
    public void Fall(Objects purpose) {

    }

    public abstract void Fall(java.util.Objects purpose);

    @Override
    public void Drift() throws InterruptedException {

    }
}
