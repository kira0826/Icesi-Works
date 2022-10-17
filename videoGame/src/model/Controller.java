package model;

import java.util.Random;

public class Controller {

    private TypesOfResolution resolution;
    private Level[] levels;
    private Player[] players;
    private Treasure [] treasures;
    private Enemy [] enemies;
    private static Random random = new Random();


    public Controller(int quantityOfLevels, int amountOfPlayers, int amountOfTreasures, int amountOfEnemies) {
        this.players = new Player[amountOfPlayers];
        this.levels = new Level[quantityOfLevels];
        this.treasures = new Treasure[amountOfTreasures];
        this.enemies = new Enemy[amountOfEnemies];
    }
    //Report staff

    /**Description : This method generates a report, which express the treasures and enemies of a seleceted level.
     * It's worth to mention, that this method indicates if the level selected doesn't have treasures or enemies associated.
     * @param levelPosition Indicates the position of the level selected by the user.
     * @return A string that contains the generated inform.
     */
    public String treasuresAndEnemiesOfaLevel (int levelPosition){

        String messageTreasures  = "";
        int counter;
        //Treasures process
        for (int treasureModel = 0; treasureModel < getTreasures().length;treasureModel++){
            if (getTreasures()[treasureModel] != null){
                counter = 0;
                for (int copy = 0; copy< getLevels()[levelPosition].getLevelTreasures().length;copy++){
                    if (getLevels()[levelPosition].getLevelTreasures()[copy] != null){
                        if (getLevels()[levelPosition].getLevelTreasures()[copy].getName().equals(getTreasures()[treasureModel].getName())){
                            counter++;
                        }

                    }
                }
                if (counter!=0)messageTreasures += getTreasures()[treasureModel].getName() + " X " + counter + " | ";
            }
        }
        if (messageTreasures.isEmpty())messageTreasures += "No hay tesoros en el nivel.";
        String messageEnemies  = "";
        for (int positionEnemy = 0; positionEnemy< getLevels()[levelPosition].getLevelEnemies().length;positionEnemy++){
            if (getLevels()[levelPosition].getLevelEnemies()[positionEnemy] != null){
                messageEnemies += getLevels()[levelPosition].getLevelEnemies()[positionEnemy].getName() + " | ";
            }
        }
        if (messageEnemies.isEmpty()) messageEnemies += "No hay enemigos en el nivel.";

        return "TESOROS: " + "\n" + messageTreasures + "\n" + "ENEMIGOS" + "\n" + messageEnemies;
    }

    /**Description : This method generates a report, which express the amount of treasures  of a specific type of treasure in the whole program.
     * @param treasurePosition Indicates the position of the treasure selected by the user.
     * @return A string that contains the generated inform.
     */
    public String amountOfASpecificTreasure (int treasurePosition){

        int counter = 0;

        for (int i = 0; i < getLevels().length;i++){

            for(Treasure treasure : getLevels()[i].getLevelTreasures()){
                if (treasure != null){
                    if (treasure.getName().equals(getTreasures()[treasurePosition].getName())){
                        counter ++;
                    }
                }
            }
        }
        return "Tesoro: " + getTreasures()[treasurePosition].getName() + "\n" +
                "Cantidad : " + counter;

    }

    /**Description: This method count the number of consonants of the enemies name registered.
     * @return A String that indicates the number of consonants found.
     */
    public String amountOfConsonants(){

        char[] consonantsList;
        consonantsList = new char[]{'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','y','z'};
        int counter = 0;
        for (int enemy  = 0; enemy < getEnemies().length; enemy++){
            if (getEnemies()[enemy] != null){
                for (int charName = 0; charName < getEnemies()[enemy].getName().length();charName++){
                    for (int consonant = 0; consonant < consonantsList.length;consonant++){
                        if (consonantsList[consonant] == getEnemies()[enemy].getName().toLowerCase().charAt(charName)){
                            counter ++;
                        }
                    }
                }
            }
        }

        return "Cantidad consonantes encontradas: " + counter;

    }

    /**
     * Description: This method identifies the top  5 of players with the highest score in the game. It firstly organizes
     * and artificial array of players, ordering by the score of each player; Being the first player, the player with the highest
     * score.
     * @return A string that contains the players with the highest score.
     */
    public String top5Players () {
        int counter = 0;
        for (Player player : getPlayers()) {
            if (player != null) counter++;
        }
        Player [] playersToOrganize = new Player[counter];
        counter = 0;
        for (Player player : getPlayers()) {
            if (player != null) {
                playersToOrganize[counter] = player;
                counter++;
            }
        }
        for (int i = 0; i < playersToOrganize.length; i++ ){
            for (int k = i; k <playersToOrganize.length; k++){
                if (playersToOrganize[i].getScore() < playersToOrganize[k].getScore()){
                    Player intermediary = playersToOrganize[i];
                    playersToOrganize[i] = playersToOrganize[k];
                    playersToOrganize[k] = intermediary;
                }
            }
        }
        String message = "";
        if (playersToOrganize.length > 5){
            counter = 5;
        }else counter = playersToOrganize.length;
        for (int i = 0; i < counter; i++ ){
            message += i+1 + ".) " + playersToOrganize[i].getNickname() + " | Puntaje:  " + playersToOrganize[i].getScore() + "\n";
        }
        return "TOP 5" + "\n" + message;
    }

    //Resolution staff

    /**Description: This method organize and concatenate the information of each type of resolution.
     * @return A message that contains the crucial information of each type of resolution.
     */
    public String getTypeOfResolution (){
        String message ="";
        int counter = 0;
        for (TypesOfResolution types : TypesOfResolution.values()) {
            message += counter + ".)" + "\n" + types.toString();
            counter ++;
        }
        return message;
    }

    /**Description: This method establishes the resolution of the program, that was given by the user.
     * @param positionType This parameter indicates the position of the type of resolution object to use.
     */
    public void establishResolution (int positionType){

        setResolution(TypesOfResolution.values()[positionType]);
    }

    // Levels staff

    /**Description: This method initialize the id and scoreRequiredToPass of each level generated.
     * @param increase The accumulation of this value per iteration will be used to establish the scoreRequiredToPass
     *                 attribute.
     */
    public void initLevels(int increase){
        int accumulate = increase;
        for (int i = 0; i<getLevels().length; i++){
            getLevels()[i] = new Level(i,accumulate);
            accumulate += increase;
        }
    }

    /**Description: This method compares the sum of scoreToGive attribute of treasures and the sum of scoreToGive attribute
     * of all the enemies in order to identify and establish the complexity of a specific level.
     * @param positionLevel l.
     * @return A String that indicates the complexStorages the position of the level that need to identify and establish leveity assigned to the selected level.
     */
    public String establishAndIdentifyComplexity(int positionLevel){

        int treasuresScore = 0;
        int enemiesScore = 0;
        for (Treasure treasure : getLevels()[positionLevel].getLevelTreasures()){
            if (treasure != null)treasuresScore += treasure.getScoreToGive();
        }

        for (Enemy enemy : getLevels()[positionLevel].getLevelEnemies()){
            if (enemy != null) enemiesScore += enemy.getScoreToGive();
        }
        if (treasuresScore > enemiesScore){
            getLevels()[positionLevel].setComplexity(ComplexityType.BAJA);
        }else if (treasuresScore < enemiesScore) {
            getLevels()[positionLevel].setComplexity(ComplexityType.ALTA);
        }else {
            if (treasuresScore == 0 && enemiesScore == 0 ){
                return "Como a este nivel no se ha asociado ni enemigos,ni tesoros, este nivel no posee complejidad.";
            }
            getLevels()[positionLevel].setComplexity(ComplexityType.MEDIA);
        }
        String message = "Complejidad del nivel: " + getLevels()[positionLevel].getComplexity() + ".";
        return message;

    }

    /** This method concatenates the string format of each treasure registered.
     *  @return A String with the information of each treasure.
     */
    public String concatenateLevels(){
        String message = "";
        for (int i = 0; i< getLevels().length;i++) {
            if (getLevels()[i] != null) message += i + ".) " + "\n" + getLevels()[i].toString();
        }
        return message;
    }

    //Player staff


    /** Description: This method identifies the level of a specific player by comparing its score and the score required
     *to pass the level of each level. It's worth to say that if the player reaches a higher score than the last level,
     * the method every time will assign the last level plus one into the level attribute of the player. Thus, players
     * can only reach the number of levels plus one, no matter by how many they exceed the attribute scoreRequireToPass
     * of the last level.
     *
     * @param playerPosition Storages the array position of the player selected by the user. Useful to identify the player object.
     * @return A string that indicates if the player upgraded, descend or kept up the level.
     */
    public String identifyAndEstablishLevel (int playerPosition){
        String message = "";

        int initialLevel = getPlayers()[playerPosition].getLevel();

        for(int i = 0; i < getLevels().length;i++){
            if (i == (getLevels().length-1) && (getPlayers()[playerPosition].getScore() >= getLevels()[getLevels().length-1].getScoreRequiredToPass())){
                getPlayers()[playerPosition].setLevel(i+1);
            }else{
                if (getPlayers()[playerPosition].getScore() < getLevels()[i].getScoreRequiredToPass()){
                    getPlayers()[playerPosition].setLevel(i);
                    break;
                }
            }
        }
        if (initialLevel < getPlayers()[playerPosition].getLevel()){
            message += " El jugador aumentó de nivel; pasó del " + initialLevel + " al " + getPlayers()[playerPosition].getLevel()+ ".";
        } else if (initialLevel > getPlayers()[playerPosition].getLevel()) {
            message += " El jugador descendió de nivel; pasó del nivel " + initialLevel + " al " + getPlayers()[playerPosition].getLevel()+ ".";
        }else if (initialLevel == getPlayers()[playerPosition].getLevel() && initialLevel == getLevels().length ){
            message += "El jugador se mantuvo en el nivel máximo, es decir " + getPlayers()[playerPosition].getLevel() + ".";
        }else {
            int requiredScoreToPassLevel = getLevels()[getPlayers()[playerPosition].getLevel()].getScoreRequiredToPass() - getPlayers()[playerPosition].getScore();

            message += "El jugador se mantuvo en el nivel " + getPlayers()[playerPosition].getLevel() + ". Requiere " +
                        requiredScoreToPassLevel + " puntos para pasar de nivel.";
        }
        return message;

    }


    /**This method identify the treasure or treasure most repeated in the game.
     * @return A String with the information of the treasures most repeated.
     */
    public String mostRepeatedTreasure(){
        boolean flag = true;
        int counter = 0;
        int temporal_counter;
        String message = "";

        for(int i = 0; i < getTreasures().length; i++){
            if (getTreasures()[i] != null) {
                temporal_counter = 0;
                for (int level = 0; level < getLevels().length;level++){


                    for(int treasure = 0 ; treasure < getLevels()[level].getLevelTreasures().length;treasure++){

                        if (getLevels()[level].getLevelTreasures()[treasure]!= null){

                            if (getLevels()[level].getLevelTreasures()[treasure].isEqual(getTreasures()[i])){
                                temporal_counter++;

                                if (temporal_counter>counter){
                                    counter = temporal_counter;
                                }

                                if (temporal_counter == counter && !flag){
                                    message += getTreasures()[i].getName() + "| Cantidad : " + counter + "\n";
                                }
                            }
                        }
                    }
                }
            }

            if (i == getTreasures().length -1 && flag){
                i = -1;
                flag = false;

            }
        }

        if (message.isEmpty()){
            message += "No se encontró tesoros asociados a niveles.";
        }
        return "Tesoro(s) más repetido(s): " + "\n" + message;

    }

    /**This method verify if exist at least one player.
     * @return If exist at least one player, return true, otherwise, return false.
     */
    public boolean isTherePlayers(){
        for (Player player: getPlayers()) {
            if (player != null) return true;
        }
        return false;
    }

    /**This method concatenates the string format of each player registered.
     * @return A String with the information of each player.
     */
    public String concatenatePlayers(){
        String message = "";

        for (int i = 0; i < getPlayers().length;i++) {
            if (getPlayers()[i] != null) message += i + ".) " + "\n" + getPlayers()[i].toString();
        }
        return message;
    }


    /**Description: Firstly,  this method identifies the player selected by the user, and then establishes
     * the score attribute for the score that the user gave.
     * @param playerPosition Storages the position where is located the player selected by the user.
     * @param score Storages the score that will be established as the new score of the selected player.
     */
    public void modifyPlayerScore(int playerPosition, int score ){
        getPlayers()[playerPosition].setScore(score);
    }

    /**Description: This method verify is the nickname given is unique among the nickname of the other players registered.
     * @param nickname This parameter represent the nickname that will be compared with the others nicknames.
     * @return If and only if the nickname is unique, return true, otherwise, return false.
     */
    public boolean isNicknameUnique(String nickname){
        for (Player player: getPlayers()) {
            if (player != null){
                if (player.getNickname().equals(nickname))return false;
            }
        }
        return true;
    }

    /**Description: This method verify if exist a space in  players[] in order to indicate that a player can be added.
     * @return If there is a space to add a player, return true, otherwise, return false.
     */
    public boolean isThereSpaceToRegisterAPlayer(){
        for (Player player : getPlayers()) {
            if (player ==null)return true;
        }
        return false;
    }

    /**This method register a player and storage it in players[].
     * @param name This is the string that will be the attribute name of the new player.
     * @param nickname This is the unique string that will be the attribute nickname of the new player.
     */
    public void registerPlayer (String name, String nickname){
        for (int i  = 0; i < getPlayers().length;i++) {
            if (getPlayers()[i] ==null){
                Player player1 = new Player(name,nickname);
                getPlayers()[i] = player1;
                return;
            }
        }
    }

    //Treasures staff


    /**Description: This method verify if exist a space in  treasures[] in order to indicate that a treasure can be added.
     * @return If there is a space to add a treasure, return true, otherwise, return false.
     *
     */
    public boolean isThereSpaceToRegisterATreasure(){
        for (Treasure treasure : getTreasures()) {
            if (treasure ==null)return true;
        }
        return false;
    }

    /**Description:Firsly, this method checks if the array of treasures of the level selected by user, has  enough spaces
     * in order to storage the amount of copies of a treasure; if that is the case, the method generate copies of a specific treasure and simultaneously,
     * establish randomly the position attribute of each one. The random previous assignation is generated taking care the resolution selected.
     * @param positionTreasure  Indicates the position of the treasure selected by the user.
     * @param amountOfCopies    Express the quantity of copies that the method must do of a specific treasure.
     * @param positionLevel     Indicates the position of the level selected by the user.
     * @return If the array of treasures of the level selected by user doesn't have enough spaces in order to storage the amount of copies of a treasure,
     * return false, otherwise, return true.
     */
    public boolean asociateTreasureToLevel(int positionTreasure, int amountOfCopies, int positionLevel){
        int counter = 0;
        for (int i = 0; i < getLevels()[positionLevel].getLevelTreasures().length;i++){
            if (getLevels()[positionLevel].getLevelTreasures()[i] == null){
                counter++;
                if (counter >= amountOfCopies){
                    for (int k = 0; k<amountOfCopies; k++){

                        for (int j = 0; j < getLevels()[positionLevel].getLevelTreasures().length; j++ ){

                            if (getLevels()[positionLevel].getLevelTreasures()[j] == null){
                                getLevels()[positionLevel].getLevelTreasures()[j] =
                                        new Treasure(getTreasures()[positionTreasure],
                                                random.nextInt(getResolution().getHeight() + 1),
                                                random.nextInt(getResolution().getWidth() + 1));
                                break;
                            }
                        }
                    }
                    return true;
                }
            }
        }
            return false;
    }

    /**This method concatenates the string format of each treasure registered.
     * @return A String with the information of each treasure.
     */
    public String concatenateTreasures(){
        String message = "";
        for (int i = 0; i< getTreasures().length;i++) {
            if (getTreasures()[i] != null) message += i + ".) " + "\n" + getTreasures()[i].toString() + "\n";
        }
        return message;
    }
    /**This method verify if exist at least one treasure.
     * @return If exist at least one treasure, return true, otherwise, return false.
     *
     */
    public boolean isThereTreasures(){
        for (Treasure treasure: getTreasures()) {
            if (treasure != null) return true;
        }
        return false;
    }


    /**This method register and storages a unique treasure in treasures[]. It's worth to say that if the treasure created
     * is equal to another treasure registered, it won't  be saved.
     * @param name This parameter storages the name of the treasure that will be created.
     * @param scoreToGive This parameter storages the amount of score to give if it's found of the treasure that will be created.
     * @param url This parameter storages the image url  of the treasure that will be created.
     *
     * @return If the treasure created is equal to another treasure, returns false, otherwise return true and the treasure
     * in this case will be saved in the treasure list of Controller.
     */
    public boolean registerTreasure (String name, int scoreToGive, String url){

        Treasure treasure = new Treasure(name,scoreToGive,url);
        for (int i = 0;i<getTreasures().length;i++){
            if (getTreasures()[i] != null){
                if (treasure.isEqual(getTreasures()[i]))return false;
            }
        }
        for (int i = 0; i< getTreasures().length;i++){
            if (getTreasures()[i] == null){
                getTreasures()[i] = treasure;
                break;
            }
        }
        return true;
    }



    //Enemy Staff


    /**Description: This method identifies the enemy with the biggest reward when is defeated. It´s worth to say
     * that this method,  in case of tie, expose the enemies with the biggest reward.
     * @return A string that contains the information of the enemies with the biggest rewards found.
     */
    public String enemyWithBiggestReward(){

        int biggestReward = 0;
        boolean flag  = true;
        String message = "";
        for (int level = 0; level < getLevels().length;level++){
            for(int enemy = 0 ; enemy < getLevels()[level].getLevelEnemies().length;enemy++){

                if (getLevels()[level].getLevelEnemies()[enemy] != null ){
                    if (getLevels()[level].getLevelEnemies()[enemy].getScoreToGive() >= biggestReward){

                        biggestReward = getLevels()[level].getLevelEnemies()[enemy].getScoreToGive();

                        if (!flag) message += getLevels()[level].getLevelEnemies()[enemy].getName() + "| Nivel: " + level +
                                " | Reward :  " + getLevels()[level].getLevelEnemies()[enemy].getScoreToGive() + "\n";

                    }

                }
            }

            if (level == getLevels().length-1 && flag ){
                level= -1;
                flag = false;
            }
        }

        if (message.isEmpty()){
            message += "No se encontró enemigos asociados a niveles.";
        }
        return "Enemigo(s) con mayor recompensa(s): " + "\n" + message;

    }


    /**
     * Description: This method counts the amount of a specific type of Enemy in the whole game.
     * @param positionType This variables storages the position of the enemy type selected by the user
     * @return A String indicating the quantity of enemies with the type seleceted.
     */
    public String amountOfSpecificEnemyType (int positionType) {

        int  counter  = 0;

        for(int i = 0; i < getLevels().length; i++){
            for (int j= 0; j < getLevels()[i].getLevelEnemies().length;j++ ){

                if (getLevels()[i].getLevelEnemies()[j] != null){
                    if (getLevels()[i].getLevelEnemies()[j].getType().equals(EnemyType.values()[positionType])){
                        counter++;
                    }
                }
            }
        }
        return "Cantidad de enemigos del tipo " + EnemyType.values()[positionType].toString() + ":   " +counter;

    }

    /**
     * This method concatenates the string format of each enemy type registered.
     * @return A String with the information of each type.
     */
    public String concatenateEnemyTypes () {
        String message = "";
        for (int i = 0; i< EnemyType.values().length;i++) {
            if (EnemyType.values()[i] != null) message += i + ".) " +  EnemyType.values()[i].toString() + "\n";
        }
        return message;
    }

    /**
     * This method concatenates the string format of each enemy registered.
     * @return A String with the information of each enemy.
     */
    public String concatenateEnemies(){
        String message = "";
        for (int i = 0; i< getEnemies().length;i++) {
            if (getEnemies()[i] != null) message += i + ".) " +  getEnemies()[i].toString() + "\n";
        }
        return message;
    }

    /**
     * Description: This method verify if exist a space in  enemies[] in order to indicate that an enemy can be added.
     * @return If there is a space to add a treasure, return true, otherwise, return false.
     */
    public  boolean isThereSpaceToRegisterEnemy(){
        for (Enemy enemy : getEnemies()) {
            if (enemy ==null)return true;
        }
        return false;
    }

    /**
     * This method verify if exist at least one enemy.
     * @return If exist at least one enemy, return true, otherwise, return false.
     */
    public boolean isThereEnemies(){
        for (Enemy enemy: getEnemies()) {
            if (enemy != null) return true;
        }
        return false;
    }

    /**Description: This method associates a copy of an Enemy object in a level, only if the enemy selected have not been
     * related to this specific level previously.
     * @param levelPosition  Indicates the position of the level selected by the user.
     * @param enemyPosition  Indicates the position of the enemy selected by the user.
     * @return This method returns false if exist a duplicate enemy compared with the enemy created. On the other hand,
     * returns true if the enemy was associated.
     */
    public boolean asociateEnemyToLevel(int levelPosition,  int enemyPosition){
        for (Enemy enemy :  getLevels()[levelPosition].getLevelEnemies()){
            if (enemy !=null){
                if (getEnemies()[enemyPosition].getName().equals(enemy.getName()))return false;
            }
        }
        for (int i = 0; i < getLevels()[levelPosition].getLevelEnemies().length;i++){
            if (getLevels()[levelPosition].getLevelEnemies()[i] == null){
                    getLevels()[levelPosition].getLevelEnemies()[i] =
                        new Enemy(getEnemies()[enemyPosition],
                                random.nextInt(getResolution().getHeight() + 1),
                                random.nextInt(getResolution().getWidth() + 1));
                    break;
                }
            }
        return true;
    }

    /**Description: This method create and register a unique enemy into enemies[] of Controller class.
     * @param name This variable correspond with the name attribute of the enemy that will be created.
     * @param positionType  Indicates the position of the EnemyType object that will be associated  to the enemy that will be registered.
     * @param scoreToGive This variable storages the scoreToGive attribute of the enemy that will be created.
     * @param scoreToSubtract This variable storages  the scoreToSubtract attribute of the enemy that will be created.
     * @return If the object created has a duplicate in enemies[], return true; otherwise, return true and the object will
     * be associated.
     */
    public boolean registerEnemy(String name, int positionType, int scoreToGive, int scoreToSubtract){

        for (Enemy enemy: getEnemies()) {
            if (enemy != null){
                if (enemy.getName().equals(name))return false;
            }
        }
        for (int i = 0; i < getEnemies().length;i++){
            if (getEnemies()[i] == null){
                getEnemies()[i] = new Enemy(name,EnemyType.values()[positionType],scoreToGive,scoreToSubtract);
                break;
            }
        }
        return true;
    }

    //Setters and getters

    public TypesOfResolution getResolution() {
        return resolution;
    }

    public void setResolution(TypesOfResolution resolution) {
        this.resolution = resolution;
    }

    public Level[] getLevels() {
        return levels;
    }

    public void setLevels(Level[] levels) {
        this.levels = levels;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Treasure[] getTreasures() {
        return treasures;
    }

    public void setTreasures(Treasure[] treasures) {
        this.treasures = treasures;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public void setEnemies(Enemy[] enemies) {
        this.enemies = enemies;
    }
}
