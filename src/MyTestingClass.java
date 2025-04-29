public class MyTestingClass {
    private int id;

    public MyTestingClass(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyTestingClass that = (MyTestingClass) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        // Custom hashCode to achieve uniform distribution
        int hash = 17;
        hash = 31 * hash + id;
        hash = 31 * hash + (id >> 16);
        return hash;
    }

    @Override
    public String toString() {
        return "ID: " + id;
    }
}