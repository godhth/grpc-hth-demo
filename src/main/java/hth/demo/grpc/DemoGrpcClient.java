package hth.demo.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @Author : huangtianhui
 * @create 2022/11/18 17:11
 * @description :
 */
public class DemoGrpcClient {
    public static void main(String[] args)  {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        DemoServiceGrpc.DemoServiceBlockingStub stub =
                DemoServiceGrpc.newBlockingStub(channel);
        DemoResponse demoResponse = stub.test(
                DemoRequest.newBuilder()
                        .setName("Test")
                        .setAge(18)
                        .setType(DemoType.TEST)
                        .build()
        );

        System.out.println(demoResponse);

        channel.shutdown();
    }
}
