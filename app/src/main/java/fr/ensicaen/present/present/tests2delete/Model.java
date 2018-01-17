package fr.ensicaen.present.present.tests2delete;

/**
 * Created by pierr on 03/12/2017.
 */

public class Model {
    String name;
    int value; /* 0 -&gt; checkbox disable, 1 -&gt; checkbox enable */

    Model(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

}