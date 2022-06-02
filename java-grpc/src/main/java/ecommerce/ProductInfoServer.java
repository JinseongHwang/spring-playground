package ecommerce;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class ProductInfoServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50051;
        Server server = ServerBuilder.forPort(port) // 서버가 바인딩해 메시지를 수신하는 포트이다
                                     .addService(new ProductInfoImpl())
                                     .build()
                                     .start();
        System.out.println("Server started, listening on " + port);

        Runtime.getRuntime()
               .addShutdownHook(new Thread(() -> {
                   // JVM 종료 시 gRPC 서버를 종료하고자 Runtime Shutdown Hook 가 추가된다.
                   System.err.println("Shutting down gRPC server since JVM is shutting down");
                   if (server != null) {
                       server.shutdown();
                   }
                   System.err.println("Server shut down");
               }));

        // 서버 스레드는 서버가 종료될 때까지 대기한다.
        server.awaitTermination();
    }
}
