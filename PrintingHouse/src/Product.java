public class Product {
    private int productId;
    private String productName;
    private int productEdition;
    private int productPrice;

    public Product(int productId, String productName, int productEdition, int productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productEdition = productEdition;
        this.productPrice = productPrice;
    }

    
    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getProductEdition() { return productEdition; }
    public int getProductPrice() { return productPrice; }
}