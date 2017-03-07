package com.failuredetection.view;

import com.failuredetection.controller.TrainingController;
import com.failuredetection.model.Node;
import com.failuredetection.model.TreeNode;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Main extends Application {
	
	TrainingController trainingController = new TrainingController();
	TreeNode<Node> root = null;
	
	/*@Override
	public void start(Stage primaryStage) throws Exception {
		
		Button trainingBtn = new Button("Training Mode");
		trainingBtn.setMinHeight(100);
		trainingBtn.setMinWidth(150);
		trainingBtn.setMaxHeight(100);
		trainingBtn.setMaxWidth(150);
		trainingBtn.setPrefHeight(100);
		trainingBtn.setPrefWidth(150);
		trainingBtn.setStyle("-fx-color: gray;");
		
		Button detectionBtn = new Button("Detection Mode");
		detectionBtn.setMinHeight(100);
		detectionBtn.setMinWidth(150);
		detectionBtn.setMaxHeight(100);
		detectionBtn.setMaxWidth(150);
		detectionBtn.setPrefHeight(100);
		detectionBtn.setPrefWidth(150);
		detectionBtn.setStyle("-fx-color: gray;");
		
		final FileChooser fileChooser = new FileChooser();
		
		trainingBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// Open file chooser
				File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    root = trainingController.buildTree(FileUtil.readFile(file));
                }
				
				System.out.println("Done");
			}
			
		});
		
		VBox vBox = new VBox(50);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().addAll(trainingBtn, detectionBtn);
		
		Scene scene = new Scene(vBox);
		
		primaryStage.setTitle("Mode");
		primaryStage.setScene(scene);
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
		primaryStage.show();
	}*/
	
	@Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                       new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                         new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                          new double[]{210, 210, 240, 240}, 4);
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
}