package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
	
	Stage stage;
	Scene scene;
	
	@FXML
	private TextField firstnTextField;
	@FXML
	private TextField lastnTextField;
	@FXML
	private TextField unameTextField;
	@FXML
	private PasswordField setpasswordPasswordField;
	@FXML
	private PasswordField confirmPasswordPasswordField;
	@FXML
	private TextField emailTextField;
	@FXML
	private Label passwordWarningLabel;
	@FXML
	private Label emailWarningLabel;
	@FXML
	private Label registerLabel;
	
	public void registerButtonAction(ActionEvent event) {
		if (reigsterUser() == true)
			registerOnUser();
	}

	public boolean reigsterUser() {
		boolean okEmail = false;
		boolean okPassword = false;
		String text = emailTextField.getText();
		Pattern pattern = Pattern.compile("@yahoo.com", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		boolean matchFound = matcher.find();

		if (setpasswordPasswordField.getText().equals(confirmPasswordPasswordField.getText())) {
			passwordWarningLabel.setText("");
			okPassword = true;
		} else {
			passwordWarningLabel.setText("Password does not match!");
			okPassword = false;
		}

		if (matchFound) {
			emailWarningLabel.setText("");
			okEmail = true;
		} else {
			emailWarningLabel.setText("Please enter a valid email adress!");
			okPassword = false;
		}
		if (okPassword == true && okEmail == true)
			return true;
		else
			return false;
	}
	
	public void registerOnUser() {
		DBController connectNow = new DBController();
		Connection connectDB = connectNow.getConnection();

		String firstname = firstnTextField.getText();
		String lastname = lastnTextField.getText();
		String username = unameTextField.getText();
		String password = setpasswordPasswordField.getText();
		String email = emailTextField.getText();
		String insertFields = "INSERT INTO useraccounts(firstname,lastname,username,password,email) VALUES('";
		String insertValues = firstname + "' , '" + lastname + "' , '" + username + "' , '" + password + "' , '" + email
				+ "')";
		String insertToRegister = insertFields + insertValues;
		try {
			Statement statement = connectDB.createStatement();
			statement.executeUpdate(insertToRegister);
			registerLabel.setText("Registration succefully!");

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
}
