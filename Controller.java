package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {
	@FXML
	private Button closeButton;
	@FXML
	private Button minimizeButton;
	@FXML
	private Pane topPane;

	public void handleCloseButton(ActionEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	// Login Part
	
	Stage stage;
	Scene scene;

	@FXML
	private TextField usernameTextField;

	@FXML
	private PasswordField passwordPasswordField;

	@FXML
	private Button loginButton;

	@FXML
	private Label warningLabel;

	public void loginButtonAction(ActionEvent event) {
		if (usernameTextField.getText().isEmpty() == false && passwordPasswordField.getText().isEmpty() == false)
			validateLogin();
		else
			warningLabel.setText("Please enter a username or password!");
	}

	public void validateLogin() {
		DBController connectNow = new DBController();
		Connection connectDB = connectNow.getConnection();

		String verifyLogin = "SELECT count(1) FROM UserAccounts WHERE username = '" + usernameTextField.getText()
				+ "' AND password = '" + passwordPasswordField.getText() + "'";

		try {
			Statement statement = connectDB.createStatement();
			ResultSet queryResult = statement.executeQuery(verifyLogin);
			while (queryResult.next()) {
				if (queryResult.getInt(1) == 1) {
					switchToMainMenu();
					Stage stage = (Stage) loginButton.getScene().getWindow();
					stage.close();
				} else
					warningLabel.setText("Wrong username or password");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void switchToMainMenu() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Stage stage = new Stage();
			stage.setTitle("JapanosApp");
			Image icon = new Image("naruto.png");
			stage.getIcons().add(icon);
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.getCause();
			e.printStackTrace();
		}
	}
	
	public void switchToLogIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToRegister(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}


}
