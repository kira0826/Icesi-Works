package model;

public enum TypesOfResolution {
    SD("Stardar edition", 640,480),
    QHD("Quarter of Definition", 960,540),
    HD("Hight Definition", 1280,720),
    FHD("Full Hight Definition", 1920, 1080),
    WQHD("Wide Quad Hight Definition", 2560, 1440),
    UHD("Ultra Hight Definition", 3840, 2160),
    UHD8K("Ultra Hight Definition 8k", 7680, 4320);

    private String fullName;
    private int height;
    private int width;

    private TypesOfResolution(String fullName, int width, int height) {
        this.fullName = fullName;
        this.height = height;
        this.width = width;
    }

    @Override
    public String toString() {
        return  this.name() + "\n" +
                fullName + ":  " + width + "X"+ height + "\n";
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
