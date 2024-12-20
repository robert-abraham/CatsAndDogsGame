import java.util.ArrayList;
import java.util.Random;
import javafx.scene.text.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Game extends Application {
    //initiates a random variable that the program calls on 
    private Random rand = new Random();

    //intilizes animal array list variable
    private ArrayList<animal> animals;

    //Increase or decrese the difficulty, Higher = harder, lower = easier. Go by very small increments
    private static final double DIFFICULTY = 0.03;

    @Override
    public void start(Stage primaryStage) {
        // Create Window
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 800, 400);

        //Create player
        Character player = new Character(375,350);
        pane.getChildren().add(player.getView());

        //Dynamic Animal array list
        animals = new ArrayList<>();

        //get key event presses
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            
            switch (code) {
                case LEFT -> player.moveLeft(10);                    
                case RIGHT -> player.moveRight(10);
                default -> {}
            }});

        // Create an AnimationTimer to move the ball
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                player.updateFrame();

                if (rand.nextDouble() < DIFFICULTY) {
                    spawnAnimal(pane);
                }
                
                //iterate thropugh all aniamls falling
                for (int i = 0; i < animals.size(); i++) {
                    animals.get(i).updateFrame();
                    //if the animal has activated its deleted state, then remove from pane and array list
                    if(animals.get(i).deletable()){
                        pane.getChildren().remove(animals.get(i).getView());
                        animals.remove(i);
                    }
                    //checks for collosions and stops the game if true
                    if (animals.get(i).checkCollision(player)){
                        System.out.println("GAME OVER!");
                        this.stop();
                        game_over(pane);

                        

                    }  
                }
            }
        };

        game_start(pane, timer);

        primaryStage.setTitle("Cats and Dogs Game!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //function to spawn a random animal either dog or cat
    private void spawnAnimal(Pane pane) {
        double x = rand.nextDouble() * (pane.getWidth() - 50);
        double y = -60; // start above the screen
        animal a;
        if (rand.nextBoolean()) {
            a = new cat(x, y);
        } else {
            a = new dog(x, y);
        }
        animals.add(a);
        pane.getChildren().add(a.getView());
    }
    


    public static void main(String[] args) {
        launch(args);
    }


    public static void game_over(Pane pane){
        Text t = new Text("Game Over"); // create text object
        t.setFont(Font.font("Arial", 48));          // Make the text larger and arial font
        t.setFill(Color.RED);                       // change colour to red

        //Center Text
        t.setX((pane.getWidth() - t.getLayoutBounds().getWidth()) / 2);
        t.setY((pane.getHeight() - t.getLayoutBounds().getHeight()) / 2);

        // Add text to the pane
        pane.getChildren().add(t);
    }

    public static void game_start(Pane pane, AnimationTimer timer){
        // Create a Text element for the description
        Text descriptionText = new Text("Dodge all Falling Cats and Dogs\n Try to survive as long as possible"); // create text object
        descriptionText.setFont(Font.font("Arial", 48));          // Make the text larger and arial font
        descriptionText.setFill(Color.BLACK);                       // change colour to red

        //Center Text
        descriptionText.setX((pane.getWidth() - descriptionText.getLayoutBounds().getWidth()) / 2);
        descriptionText.setY((pane.getHeight() - descriptionText.getLayoutBounds().getHeight()) / 2);
      
        // Create a Button for 'Play Now'
        Button playNowButton = new Button("Play Now");
        playNowButton.setLayoutX(350);
        playNowButton.setLayoutY(250);

        // Set the button action
        playNowButton.setOnAction(event -> {
            // Remove the text and button when clicked
            pane.getChildren().removeAll(descriptionText, playNowButton);
            timer.start();

        });

        // Add the text and button to the layout
        pane.getChildren().addAll(descriptionText, playNowButton);
    }
}