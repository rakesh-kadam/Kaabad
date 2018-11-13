package initial.project.com.goldbin;

public class ScrapInfo {
    private String title, unitPrice, scrapCategory, quantity, totalQuantity;

    public Scrap(String title, String unitPrice, String scrapCategory, String quantity, String totalQuantity) {
        this.title = title;
        this.unitPrice = unitPrice;
        this.scrapCategory = scrapCategory;
        this.quantity = quantity;
        this.totalQuantity = totalQuantity;
    }

    //Referring title
    public String getTitle {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //Referring Unit Price
    public String getUnitPrice {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    //Referring Scrap Category
    public String getScrapCategory {
        return scrapCategory;
    }

    public void setScrapCategory(String scrapCategory) {
        this.scrapCategory = scrapCategory;
    }

    //Referring Quantity Amounted
    public String getQuantity {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    //Referring Total Units
    public String getTotalQuantity {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
