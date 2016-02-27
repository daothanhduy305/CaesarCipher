package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    Button encryptBut, decryptBut;
    @FXML
    TextArea inputBox, outputBox;
    @FXML
    TextField keyValBox;

    private boolean isInEncryptMode;
    private int keyVal;

    @FXML
    public void encrypt_function() {
        isInEncryptMode = true;
        perform();
    }

    @FXML
    public void decrypt_function() {
        isInEncryptMode = false;
        perform();
    }

    private void perform() {
        try {
            keyVal = Math.abs(Integer.parseInt(keyValBox.getText()));
        } catch (RuntimeException e) {
            keyVal = 0;
            keyValBox.setText("" + 0);
        } finally {
            outputBox.setText("");
        }
        inputBox.getText().chars()
                .forEach(ch -> {
                    char current = (char)ch;
                    if (Character.isLetter(current)) {
                        current = shift(current);
                    }
                    outputBox.setText(outputBox.getText() + current);
                });
    }

    private boolean isBound (char ch) {
        return (Character.isLowerCase(ch)?
                (isInEncryptMode? (ch + keyVal > 'z') : (ch - keyVal < 'a')) :
                (isInEncryptMode? (ch + keyVal > 'Z') : (ch - keyVal < 'A')));
    }

    private char shift(char ch) {
        int delta = isBound(ch)? keyVal - 26 : keyVal;
        return (isInEncryptMode? (char)(ch + delta) : (char)(ch - delta));
    }
}
