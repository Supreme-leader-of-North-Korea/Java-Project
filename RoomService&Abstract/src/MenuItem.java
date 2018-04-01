public class MenuItem {
    private String name, description, price;

    
    public MenuItem(String name, String desc, String price){
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
    public String getPrice() {
        return price;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String desc) {
        description = desc;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}