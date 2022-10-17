package model;

import java.util.Arrays;

public class Treasure {

    private String name;
    private int scoreToGive;
    private String url;
    private int [] position = {0,0};

    public Treasure(String name, int scoreToGive, String url) {
        this.name = name;
        this.scoreToGive = scoreToGive;
        this.url = url;
    }
    public Treasure (Treasure treasure, int randomHeight, int randomWidth){

        this.name = treasure.getName();
        this.url = treasure.getUrl();
        this.scoreToGive = treasure.getScoreToGive();
        this.position[0] = randomHeight;
        this.position[1] = randomWidth;

    }

    @Override
    public String toString() {
        return "Tesoro " + name + "\n" +
                "Puntaje que da: " + scoreToGive + "\n" +
                "URL: " + url + "\n" +
                "Posición: " + Arrays.toString(position);
    }

    public boolean isEqual(Treasure treasure) {
        if(this.getName().equals(treasure.getName()) && this.getUrl().equals(treasure.getUrl()) && this.getScoreToGive() == treasure.getScoreToGive()){
            return true;
        }else{
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScoreToGive() {
        return scoreToGive;
    }

    public void setScoreToGive(int scoreToGive) {
        this.scoreToGive = scoreToGive;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }
}
