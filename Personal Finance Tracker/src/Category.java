public class Category {
    private final int categoryID;
    private String categoryName;
    private String icon; // Assuming the icon is a string (like a font-awesome name or a path). In a real app, this might be different.

    public Category(int categoryID, String categoryName, String icon) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.icon = icon;
    }

    public void createCategory() {
        InMemoryDB.categories.put(this.categoryID, this);
    }

    public void editCategory(String newCategoryName, String newIcon) {
        this.categoryName = newCategoryName;
        this.icon = newIcon;
        InMemoryDB.categories.put(this.categoryID, this); // Update the category in the in-memory database.
        System.out.println("Category updated successfully!");
    }

    public void removeCategory() {
        InMemoryDB.categories.remove(this.categoryID);
        System.out.println("Category removed successfully!");
    }

    // Getter and Setter methods (optional but useful for further functionality)
    public int getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}