package model;

import java.util.Arrays;

public class Enemy {

    private String name;
    private EnemyType type;
    private int scoreToGive;
    private int scoreToSubtract;
    private int [] position = new int[2];

    public Enemy(String name, EnemyType type, int scoreToGive, int scoreToSubtract) {
        this.name = name;
        this.type = type;
        this.scoreToGive = scoreToGive;
        this.scoreToSubtract = scoreToSubtract;

    }

    public Enemy(Enemy enemy, int randomHeight, int randomWidth) {
        this.name = enemy.name;
        this.scoreToGive = enemy.scoreToGive;
        this.scoreToSubtract = enemy.scoreToSubtract;
        this.type = enemy.type;
        this.position[0] = randomHeight;
        this.position[1] = randomWidth;
    }

    @Override
    public String toString() {
        String positionMessage ="";
        if (this.position ==null){
             positionMessage= "No definida";
        }else {
            positionMessage = Arrays.toString(position);
        }
        return "Enemigo " + name + "\n" +
                "Tipo: " + type.toString() + "\n" +
                "Punataje que da al ser derrotado: " + scoreToGive + "\n" +
                "Punataje que quita: " + scoreToSubtract + "\n" +
                "Posición: " + positionMessage ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnemyType getType() {
        return type;
    }

    public void setType(EnemyType type) {
        this.type = type;
    }

    public int getScoreToGive() {
        return scoreToGive;
    }

    public void setScoreToGive(int scoreToGive) {
        this.scoreToGive = scoreToGive;
    }

    public int getScoreToSubtract() {
        return scoreToSubtract;
    }

    public void setScoreToSubtract(int scoreToSubtract) {
        this.scoreToSubtract = scoreToSubtract;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }
}
