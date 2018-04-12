public class MenuItem {
    private String name, description;
    private double price;
    
    public MenuItem(String name, String desc, double price){
        this.name = name;
        description = desc;
        this.price = price;
    }
    
    public String getName(){
        return name;
    }
    public String getDescription() {
        return description;
    }
    public double getPrice() {
        return price;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String desc) {
        description = desc;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}