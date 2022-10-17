package model;

public class Player {

    private String name;
    private String nickname;
    private int level;
    private int score;
    private int amountOfLifes;

    public Player(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
        this.amountOfLifes = 5;
        this.level = 0;
        this.score = 10;
    }


    @Override
    public String toString() {
        return "Jugador " + nickname + "\n" +
                "Nombre: " + name + "\n" +
                "Nivel: " + level + "\n" +
                "Puntaje: " + score + "\n" +
                "Cantidad de vidas: " + amountOfLifes + "\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAmountOfLifes() {
        return amountOfLifes;
    }

    public void setAmountOfLifes(int amountOfLifes) {
        this.amountOfLifes = amountOfLifes;
    }
}
