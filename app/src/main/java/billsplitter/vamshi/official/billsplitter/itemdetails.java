package billsplitter.vamshi.official.billsplitter;

public class itemdetails {
    String itemname;

    public itemdetails() {
    }

    public itemdetails(String itemname, String itemcost) {
        this.itemname = itemname;
        this.itemcost = itemcost;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemcost() {
        return itemcost;
    }

    public void setItemcost(String itemcost) {
        this.itemcost = itemcost;
    }

    String itemcost;
}
