package ecommerce;

import ecommerce.ProductInfoOuterClass.Product;
import ecommerce.ProductInfoOuterClass.ProductID;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProductInfoImpl extends ProductInfoGrpc.ProductInfoImplBase {

    private final Map<String, Product> productMap = new HashMap<>();

    /**
     * @param request          : ProductInfoOuterClass.Product, 서비스 정의에 의해 생성됨
     * @param responseObserver : 클라이언트에게 응답을 보내고, 스트림을 닫는 데 사용됨
     */
    @Override
    public void addProduct(Product request, StreamObserver<ProductID> responseObserver) {
        String randomUUIDString = UUID.randomUUID().toString();
        request = request.toBuilder()
                         .setId(randomUUIDString)
                         .build();
        productMap.put(randomUUIDString, request);
        ProductID id = ProductID.newBuilder()
                                .setValue(randomUUIDString)
                                .build();
        responseObserver.onNext(id);
        responseObserver.onCompleted();
    }

    /**
     * @param request          : ProductInfoOuterClass.ProductID, 서비스 정의에 의해 생성됨
     * @param responseObserver : 클라이언트에게 응답을 보내고, 스트림을 닫는 데 사용됨
     */
    @Override
    public void getProduct(ProductID request, StreamObserver<Product> responseObserver) {
        String id = request.getValue();
        if (productMap.containsKey(id)) {
            responseObserver.onNext(productMap.get(id)); // 클라이언트에게 응답을 보냄
            responseObserver.onCompleted(); // 스트림을 닫아 클라이언트 호출을 종료한다
        }
        else {
            responseObserver.onError(new StatusException(Status.NOT_FOUND)); // 클라이언트에게 에러를 보낸다
        }
    }
}