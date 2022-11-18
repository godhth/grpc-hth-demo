package hth.demo.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;


import java.io.IOException;

/**
 * @Author : huangtianhui
 * @create 2022/11/18 16:22
 * @description :
 */
public class DemoGrpcServer {
    static public void main(String [] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080)
                .addService(new TestServiceImpl()).build();
        System.out.println("Starting server...");
        server.start();
        System.out.println("Server started!");
        server.awaitTermination();
    }

    public static class TestServiceImpl extends DemoServiceGrpc.DemoServiceImplBase {
        @Override
        public void test (DemoRequest request, StreamObserver<DemoResponse> responseObserver) {
            System.out.println(request);
            String result = "Hello there, " + request.getName();
            DemoResponse response = DemoResponse.newBuilder().setResult(result).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
