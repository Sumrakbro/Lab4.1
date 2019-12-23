package interfaces;

import objects.Objects;

public interface ObjectsAction {
    void Fall(Objects purpose);

    void Drift() throws InterruptedException;
}