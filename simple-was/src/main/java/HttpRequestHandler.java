import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

public class HttpRequestHandler implements Runnable {

    private final Socket socket;

    public HttpRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                InputStream clientInputStream = socket.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(clientInputStream);
                BufferedReader in = new BufferedReader(inputStreamReader);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            printRequest(in);
            sendResponse(out);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void printRequest(BufferedReader br) throws IOException {
        String requestLine;
        while (!(requestLine = br.readLine()).isEmpty()) {
            System.out.println(requestLine);
        }
    }

    private void sendResponse(PrintWriter out) throws IOException {
        File htmlFile = new File(System.getProperty("user.dir") + "/src/main/resources/index.html");
        byte[] htmlBytes = Files.readAllBytes(htmlFile.toPath());

        // Headers
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
        out.println("Content-Length: " + htmlBytes.length);
        out.println();

        // Body
        out.println(new String(htmlBytes));
        out.flush();
    }
}
