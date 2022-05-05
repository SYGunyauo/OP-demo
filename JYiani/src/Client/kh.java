package Client;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;

public class kh extends Application {
    public Socket socket = null;
    public BufferedReader in = null;
    public BufferedWriter out = null;
    public SocketAddress socketAddress = null;

    @FXML
    TextArea textArea;
    @FXML
    TextArea FYB;


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("kh.fxml"));
        Parent root = loader.load();
        Scene myScence = new Scene(root, 600, 400);
        primaryStage.setScene(myScence);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    public void btnStartConnect() throws IOException {
        try{
            socket = new Socket();
            socketAddress = new InetSocketAddress("localhost",50000);
            System.out.println("客户");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btnFaY() throws IOException {
        try {
            socket = new Socket();
            socketAddress = new InetSocketAddress("localhost",50000);
            System.out.println("客户");
            socket.connect(socketAddress);
            System.out.println("success");
            System.out.println("端口:" + socket.getLocalSocketAddress());
            String str = FYB.getText();
            if (str != "" && str != "\n"){
                textArea.appendText(str);
                FYB.setText("");
            }
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            textArea.appendText("\n");
            out.write(str);
            out.newLine();
            out.flush();
            System.out.println("发送成功" + str);
            String restr = in.readLine();
            System.out.println("服务器接收" + restr);
            textArea.appendText("服务器：" + restr + "\n");
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            if (in != null)in.close();
            if (out != null)out.close();
            if (socket != null)socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}