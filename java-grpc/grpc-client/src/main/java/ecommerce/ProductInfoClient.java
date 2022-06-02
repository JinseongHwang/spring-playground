package ecommerce;

import ecommerce.ProductInfoGrpc.ProductInfoBlockingStub;
import ecommerce.ProductInfoOuterClass.Product;
import ecommerce.ProductInfoOuterClass.ProductID;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ProductInfoClient {

    public static void main(String[] args) {
        // localhost:50051 에서 수신을 대기하는 gRPC 서버에 채널을 만들어서 연결한다.
        // 평문을 사용하며, 보안되지 않은 연결을 한다.
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                                                      .usePlaintext()
                                                      .build();

        // 서버의 응답을 받을 때까지 대기하는 BlockingStub을 사용한다.
        // 서버 응답을 기다리지 않고 observer 를 등록해 응답을 받는 NonBlockingStub 도 있다.
        ProductInfoBlockingStub stub = ProductInfoGrpc.newBlockingStub(channel);

        // 제품 정보와 함께 addProduct 메서드를 호출한다.
        // 호출이 완료되면 ProductID 객체를 반환한다.
        ProductID productID = stub.addProduct(
            Product.newBuilder()
                   .setName("Apple iPhone 11")
                   .setDescription("Meet Apple iPhone 11. "
                                   + "All new dual camera system with "
                                   + "Ultra wide and Night mode.")
                   .setPrice(1000.0f)
                   .build()
        );
        System.out.println(productID.getValue());

        // ProductID 로 getProduct 메서드를 호출한다.
        // 호출이 완료되면 Product 객체를 반환한다.
        Product product = stub.getProduct(productID);
        System.out.println(product.toString());

        // 모든 작업이 완료되면 연결을 종료해서, 사용한 리소스를 안전하게 반납한다.
        channel.shutdown();
    }
}
