package Server;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class fwq extends Application {


    public ServerSocket serverSocket = null;
    public Socket socket = null;
    public BufferedReader in = null;
    public BufferedWriter out = null;
    public TextArea textArea;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fwq.fxml"));
        Parent root = loader.load();
        Scene myScence = new Scene(root, 600, 400);
        primaryStage.setScene(myScence);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception{
        launch(args);
        while (true){
            new Thread().start();
        }
    }

    public void btnStartServer() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        serverSocket = new ServerSocket();
                        SocketAddress socketAddress = new InetSocketAddress("localhost",50000);
                        serverSocket.bind(socketAddress);
                        System.out.println("start");
                        socket = serverSocket.accept();
                        System.out.println("success");
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        String str = in.readLine();
                        textArea.appendText(str + "\n");
                        System.out.println("服务器:" + str);
                        out.write("你好");
                        out.newLine();
                        out.flush();
                        System.out.println("回送");
                        if (in != null)in.close();
                        if (out != null)out.close();
                        if (serverSocket != null)serverSocket.close();
                        if (socket != null)socket.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

