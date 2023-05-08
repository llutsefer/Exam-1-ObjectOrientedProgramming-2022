public abstract class Product {
    String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public abstract double getPrice(int year, int month);
}
