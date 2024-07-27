public class Transaction {
    private int id;
    private String description;
    private double amount;
    private String type;
    private java.sql.Date date;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public java.sql.Date getDate() { return date; }
    public void setDate(java.sql.Date date) { this.date = date; }
}
