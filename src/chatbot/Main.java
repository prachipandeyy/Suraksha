package chatbot;

import javafx.beans.value.ChangeListener;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;

import static javafx.concurrent.Worker.State;

public class Main extends Application {
    public static String DialogflowURL = "";//https://console.dialogflow.com/api-client/demo/embedded/xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(final Stage stage) {
        WebView webView = new WebView();
        final WebEngine webEngine = webView.getEngine();
        String html = "<iframe\n" +
                "    allow=\"microphone;\"\n" +
                "    width=\"400\"\n" +
                "    height=\"500\"\n" +
                "    src=\"" + DialogflowURL + "\">\n" +
                "</iframe>";
        webEngine.loadContent(html);
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
            public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
                if (newState == State.SUCCEEDED) {
                    //stage.setTitle(webEngine.getLocation());
                    stage.setTitle(webEngine.getTitle());
                }
            }
        });
        VBox root = new VBox();
        root.getChildren().add(webView);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setHeight(610);
        stage.setWidth(470);
        stage.show();
    }
}