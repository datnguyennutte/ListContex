package dat.project.listcontex;

public class ListViewBean {
    int image;
    String foodProduct;
    String price;

    public ListViewBean(int image, String foodProduct, String price) {
        this.image = image;
        this.foodProduct = foodProduct;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getFoodProduct() {
        return foodProduct;
    }

    public void setFoodProduct(String foodProduct) {
        this.foodProduct = foodProduct;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ListViewBean() {
    }
}
