package ui;

import model.Controller;

import java.util.Scanner;

public class Manager {
    private static Controller controller;
    private static Scanner scanner;

    public static void main(String[] args) {


        initValues();
        askForResolution();
        askForIncrementValueToInitLevels();

        boolean continueOnLoop = true;

        while(continueOnLoop){

            displayMenu();
            String menuSelection = scanner.next();

            switch (menuSelection) {

                case "1":
                    registerPLayer();
                    break;
                case "2":
                    registerTreasure();
                    break;
                case "3":
                    registerEnemy();
                    break;
                case "4":
                    modifyPlayerScore();
                    break;
                case "5":
                    identifyAndEstablishLevel();
                    break;
                case "6":
                    establishAndIdentifyComplexity();
                    break;
                case "7":
                    associateTreasureToLevel();
                    break;
                case "8":
                    associateEnemyToLevel();
                    break;
                case "9":
                    treasuresAndEnemiesOfaLevel();
                    break;
                case "10":
                    amountOfASpecificTreasure();
                    break;
                case "11":
                    if (controller.isThereEnemies()){
                        System.out.println(controller.amountOfConsonants());
                    }else System.out.println("No hay enemigos registrados por lo tanto no se puede realizar esta operación.");
                    break;
                case "12":
                    if (controller.isTherePlayers()){
                        System.out.println(controller.top5Players());
                    }else System.out.println("No hay jugadores registrados por lo tanto no se puede realizar esta operación.");
                    break;
                case "13":
                    amountOfSpecificEnemyType();
                    break;
                case "14":

                    if (controller.isThereTreasures()){
                        System.out.println(controller.mostRepeatedTreasure());
                    }else System.out.println("NO hay tesoros registrados,  por lo tanto no se puede realizar esta acción.");

                    break;
                case "15":
                    if (controller.isThereEnemies()){
                        System.out.println(controller.enemyWithBiggestReward());
                    }else System.out.println("No hay enemigos registrados, por lo tanto no se puede realizar esta acción");
                    break;

                case "16":
                    continueOnLoop = false;
                    System.out.println("Hasta luego usuario.");
                    break;

                default:
                    System.out.println("El dato ingresado no coincide con ninguna opción");
                    break;
            }
        }
    }

    /**
     * Description : This method generate the quantity of levels of the program, and
     * initialize the scanner that will use the Manager class.
     */
    public static void initValues() {
        controller = new Controller(10,20,  50,25);
        scanner = new Scanner(System.in);
    }


    /**
     * Description: This method ask the user the resolution of his device where he is running the program.
     * Is important to say,that he has to select one of the displayed resolutions by the program.
     */
    public static void askForResolution() {
        System.out.println("Por favor escribe el número de la resolución que corresponde con tu dispositivo:" + "\n");
        System.out.println(controller.getTypeOfResolution());
        int position =  scanner.nextInt();
        controller.establishResolution(position);
        System.out.println("Resolución establecida.");
    }

    /**
     * Description: This method ask for the increment value that will be used to initialize
     * the scoreRequiredToPass attribute of the levels generated.
     */
    public static void askForIncrementValueToInitLevels(){
        System.out.println("Dame por favor la tasa de incremento del puntaje para pasar de nivel: ");
        int increase = scanner.nextInt();
        controller.initLevels(increase);
    }

    /**
     * Description: This method display the menu with its program options.
     */
    public static void displayMenu () {

        String menu = "\n" +
                      "Buen día usuario, por favor escriba el número que corresponde con la acción\n" +
                      "que desea desarrollar.\n" +
                      "\n" +
                      "1.  Registrar jugador.\n" +
                      "2.  Registar tesoro.\n" +
                      "3.  Registrar enemigo.\n" +
                      "4.  Modificar puntaje de un jugador.\n" +
                      "5.  Identificar y establecer nivel de un jugador\n" +
                      "6.  Identificar y establecer complejidad de un nivel.\n" +
                      "7.  Asociar tesoro a un nivel.\n" +
                      "8.  Asociar enemigo a un nivel.\n" +
                      "9.  Generar reporte de los tesoros y enemigos de un nivel.\n" +
                      "10. Generar reporte de la cantidad de tesoros de un tesoro en específico.\n" +
                      "11. Contabilizar la cantidad de consonantes que hay en los nombres de los enemigos del juego.\n" +
                      "12. Top 5 jugadores con mayor puntaje.\n" +
                        "13. Contabilizar la cantidad de enemigos de  un enemigo en específico. \n" +
                        "14. Informar el tesoro más repetido. \n" +
                        "15. Enemigo que otorga mayor puntaje.\n" +
                      "16. Salir del programa.";

        System.out.println(menu);
    }

    /**
     * This method register a player; thus, it requests the attributes of the player that will be created.
     * Is important to say that this method ensures that the given nickname is unique.
     */
    //Player Staff
    public static void registerPLayer (){
        if (controller.isThereSpaceToRegisterAPlayer()){
            scanner.nextLine();
            System.out.println("Por favor dame el nickname que deseas para tu jugador: ");
            String nickname = scanner.nextLine();
            while(!controller.isNicknameUnique(nickname)){
                System.out.println("El nickname ya fue usado, por favor elige otro: ");
                nickname = scanner.nextLine();
            }
            System.out.println("Por favor dame el nombre que deseas para tu jugador: ");
            String name = scanner.nextLine();

            controller.registerPlayer(name,nickname);
            System.out.println("Usuario registrado.");
        }else {
            System.out.println("El programa no tiene espacio para almacenar otro jugador.");
        }


    }

    /**
     * Description : This method is developed to solicit the player which score attribute will be changed for a
     * value given by the user.
     */
    public static void modifyPlayerScore(){

        if (controller.isTherePlayers()){
            System.out.println("Por favor escribe el número que le corresponde al jugador que quieres modificar su puntaje: ");
            System.out.println(controller.concatenatePlayers());
            int positionPlayer = scanner.nextInt();
            System.out.println("Por favor dime el puntaje que deseas asociar a este jugador: ");
            int score = scanner.nextInt();
            controller.modifyPlayerScore(positionPlayer,score);
            System.out.println("Puntaje establecido.");
            System.out.println(controller.identifyAndEstablishLevel(positionPlayer));
        }else {
            System.out.println("No hay jugadores registrados, por lo tanto no se puede realizar esta operación. " );
        }

    }

    /**
     * Description : This method is developed to identify the player that the user want to identify and establish
     * a level.
     */
    public static void identifyAndEstablishLevel(){

        if (controller.isTherePlayers()){
            System.out.println("Por favor escribe el número que le corresponde al jugador que quieres identificar su nivel: ");
            System.out.println(controller.concatenatePlayers());
            int positionPlayer = scanner.nextInt();
            System.out.println(controller.identifyAndEstablishLevel(positionPlayer));
        }else {
            System.out.println("No hay jugadores registrados, por lo tanto no se puede realizar esta operación.");
        }

    }

    // Treasure staff

    /**
     * Description: This method register a treasure; thus, it requests the attributes of the treasure that will be created.
     * Is important to say that this method verify if the treasure created doesn't have duplicates.
     */
    public static void registerTreasure () {

        if (controller.isThereSpaceToRegisterATreasure()){
            scanner.nextLine();
            System.out.println("Dame el nombre del tesoro: ");
            String name = scanner.nextLine();
            System.out.println("Dame el valor a dar del tesoro: ");
            int scoreToGive = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Dame la url de la imagen que representa a el tesoro: ");
            String url = scanner.nextLine();
            if (controller.registerTreasure(name,scoreToGive,url)){
                System.out.println("Tesoro registrado");
            }else {
                System.out.println("Se encontró un duplicado, por lo tanto no se añadirá este tesoro al juego.");
            }
            }else {
            System.out.println("No hay espacios para agregar este tesoro.");
        }
    }

    /**
     * Description: This method is developed to ask the user the amount of copies of a specific treasure that he want to associate to a level selected by
     * him. Also, this method notify if there isn't enough space to storage the copies, and if there isn't treasures registered too.
     */
    public static void associateTreasureToLevel(){
        if (controller.isThereTreasures()){
            System.out.println("Por favor escribe el número que corresponde al tesoro que quieres asociar:");
            System.out.println(controller.concatenateTreasures());
            int treasurePosition = scanner.nextInt();

            System.out.println("Por favor escribe el número que corresponde al nivel que deseas asociar el tesoro previamente seleccionado:");
            System.out.println(controller.concatenateLevels());
            int levelPosition = scanner.nextInt();

            System.out.println("Dime por favor la cuántas veces quieres asociar este tesoro al nivel seleccionado:");
            int amountOfCopies = scanner.nextInt();

            if (controller.asociateTreasureToLevel(treasurePosition,amountOfCopies,levelPosition)){
                System.out.println("Tesoros asociados.");
                System.out.println(controller.establishAndIdentifyComplexity(levelPosition));
            }else{
                System.out.println("No hay espacio suficiente para almacenar "+ amountOfCopies +" tesoros.");
            }

        }else {
            System.out.println("No hay tesoros registrado, por lo tanto no se puede realizar esta operación.");
        }
    }

    /**
     * Description: This method register an enemy; thus, it requests the attributes of the enemy that will be created.
     * Is important to say that this method notify if the enemy created have duplicates.
     */

    public static void registerEnemy(){

        if (controller.isThereSpaceToRegisterEnemy()){
            scanner.nextLine();
            System.out.println("Dame por favor el nombre del enemigo: ");
            String name = scanner.nextLine();
            System.out.println("Dame por favor el puntaje a dar del enemigo si es derrotado");
            int scoreToGive = scanner.nextInt();
            System.out.println("Dame por favor el puntaje a quitar si derrota a el enemigo: ");
            int scoreToSubtract = scanner.nextInt();
            System.out.println("Por favor escribe el número que corresponde a el tipo de este enemigo: ");
            System.out.println(controller.concatenateEnemyTypes());
            int typePosition = scanner.nextInt();

            if (controller.registerEnemy(name, typePosition, scoreToGive, scoreToSubtract)){
                System.out.println("Enemigo creado.");
            }else {
                System.out.println("Se encontró un duplicado de este enemigo, por lo tanto este no se registró.");
            }

        }else{
            System.out.println("No hay más para almacenar un nuevo enemigo");
        }

    }

    /**
     * Description: This method is developed to ask the user the enemy that he want to associate to a level selected by
     * him. Also, this method notify if the selected enemy had been related, and if there isn't enemies registered too.
     */
    public static void associateEnemyToLevel(){
        if (controller.isThereEnemies()){
            System.out.println("Escribe el número que corresponde con el enemigo que deseas asociar: ");
            System.out.println(controller.concatenateEnemies());
            int enemyPosition = scanner.nextInt();

            System.out.println("Escribe el número que corresponde con el nivel al cual deseas asociar el enemigo.");
            System.out.println(controller.concatenateLevels());
            int levelPosition = scanner.nextInt();

            if (controller.asociateEnemyToLevel(levelPosition,enemyPosition)){
                System.out.println("Enemigo asociado.");
                System.out.println(controller.establishAndIdentifyComplexity(levelPosition));
            }else{
                System.out.println("Ya se ha asociado este enemigo, por lo tanto no se puede realizar esta operación.");
            }

        }else {
            System.out.println("No hay enemigos credos, por lo tanto no se puede realizar esta operación.");
        }
    }

    /**
     * Description : This method is developed to recognize the level which want to identify and establish
     * a level.
     */
    public static void establishAndIdentifyComplexity() {
        System.out.println("Escribe por favor  el número que le corresponde al nivel que quieres usar: ");
        System.out.println(controller.concatenateLevels());
        int levelPosition = scanner.nextInt();
        System.out.println(controller.establishAndIdentifyComplexity(levelPosition));
    }

    //Report staff

    /**
     * Description : This method is developed to recognize the enemy type which user want to know the amount
     * of enemies with that type in the whole game.
     */
    public static void amountOfSpecificEnemyType(){
        if (controller.isThereEnemies()){

            System.out.println("Escirbe el número que corresponde con el tipo de enemeigo que deseas contabilizar");
            System.out.println(controller.concatenateEnemyTypes());
            int positionType  = scanner.nextInt();

            System.out.println(controller.amountOfSpecificEnemyType(positionType));


        }else System.out.println("No hay enemigos registrados por lo tanto no se puede realizar esta acción.");
    }

    /**
     * Description : This method is developed to recognize the level which user want to generate the report of its treasures and enemies.
     */
    public static void treasuresAndEnemiesOfaLevel(){
        System.out.println("Por favor dame el número que corresponde con el nivel al cual quieres generar el informe.");
        System.out.println(controller.concatenateLevels());
        int positionLevel = scanner.nextInt();
        System.out.println(controller.treasuresAndEnemiesOfaLevel(positionLevel));
    }

    /**
     * Description : This method is developed to recognize the treasure which user want to know the amount of it in the whole program.*
     */
    public static void amountOfASpecificTreasure(){
        if (controller.isThereTreasures()) {
            System.out.println("Por favor dame el número que corresponde con el tesoro al cual quieres generar el informe.");
            System.out.println(controller.concatenateTreasures());
            int treasurePosition = scanner.nextInt();
            System.out.println(controller.amountOfASpecificTreasure(treasurePosition));
        }else System.out.println("No hay tesoros registrados, por lo tanto no se puede realizar esta acción.");
    }
}