package fr.ensicaen.present.present.tests2delete;

/**
 * Created by jueast on 09/11/17.
 */

public class HobbiesExample {
    private String name;

    public HobbiesExample(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HobbiesExample that = (HobbiesExample) o;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public String toString() {
        return "HobbiesExample{" +
                "name='" + name + '\'' +
                '}';
    }
}
