import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleWas {

    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("=== RUN on 8080 port ===");
        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            threadPool.submit(new HttpRequestHandler(socket));
        }
    }

    public static void main(String[] args) throws IOException {
        SimpleWas server = new SimpleWas();
        server.run();
    }
}
