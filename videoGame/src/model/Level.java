package model;

public class Level {

    private int id;
    private ComplexityType complexity;
    private Enemy [] levelEnemies;
    private int scoreRequiredToPass;
    private Treasure [] levelTreasures;
    @Override
    public String toString() {
        return "Nivel #"  + id + "\n" +
                "Puntaje requerido para pasar de nivel: " + scoreRequiredToPass + "\n";
    }

    public Level(int id, int scoreRequiredToPass) {
        this.id = id;
        this.scoreRequiredToPass = scoreRequiredToPass;
        this.levelTreasures = new Treasure[50];
        this.levelEnemies = new Enemy[25];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScoreRequiredToPass() {
        return scoreRequiredToPass;
    }

    public void setScoreRequiredToPass(int scoreRequiredToPass) {
        this.scoreRequiredToPass = scoreRequiredToPass;
    }

    public Treasure[] getLevelTreasures() {
        return levelTreasures;
    }

    public void setLevelTreasures(Treasure[] levelTreasures) {
        this.levelTreasures = levelTreasures;
    }

    public Enemy[] getLevelEnemies() {
        return levelEnemies;
    }

    public void setLevelEnemies(Enemy[] levelEnemies) {
        this.levelEnemies = levelEnemies;
    }

    public ComplexityType getComplexity() {
        return complexity;
    }

    public void setComplexity(ComplexityType complexity) {
        this.complexity = complexity;
    }
}
