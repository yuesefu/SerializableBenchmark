package com.joseph.jmh;

import com.google.protobuf.InvalidProtocolBufferException;
import com.joseph.learn.protobuf.Reg;
import com.joseph.learn.serializable.avro.UserAvroUtil;
import com.joseph.learn.serializable.java.User;
import com.joseph.learn.serializable.thrift.UserThriftUtil;
import org.apache.thrift.TException;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Measurement(iterations = 10, time = 5)
@Threads(4)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class SerializableDecodeTest {

    private byte[] javaData;

    private byte[] avroData;

    private byte[] protobufData;

    private byte[] thriftData;

    @Setup
    public void init() throws IOException, TException {
        User user1 = new User().setAge(1).setUsername("Test").setAddress("江苏省苏州市工业园区XX路XX号");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(user1);
        os.flush();
        os.close();
        javaData = bos.toByteArray();
        bos.close();

        com.joseph.learn.serializable.avro.User.Builder avroBuilder = com.joseph.learn.serializable.avro.User
            .newBuilder().setAge(1).setUsername("Test").setAddress("江苏省苏州市工业园区XX路XX号");
        com.joseph.learn.serializable.avro.User user2 = avroBuilder.build();
        avroData = UserAvroUtil.toSerialize(user2);

        Reg.User.Builder probotufBuilder = Reg.User.newBuilder();
        Reg.User user = probotufBuilder.setAge(1).setUsername("Test").setAddress("江苏省苏州市工业园区XX路XX号").build();
        protobufData = user.toByteArray();

        com.joseph.learn.serializable.thrift.User user3 = new com.joseph.learn.serializable.thrift.User();
        user3.setAge(1).setUsername("Test").setAddress("江苏省苏州市工业园区XX路XX号");
        thriftData = UserThriftUtil.toSerialize(user3);
    }

    @Benchmark
    public void testJavaSerializableDecode() throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(javaData);
        ObjectInputStream is = new ObjectInputStream(bis);
        User user = (User) is.readObject();
        is.close();
        bis.close();
    }

    @Benchmark
    public void testAvroSerializableDecode() throws IOException {
        com.joseph.learn.serializable.avro.User user = UserAvroUtil.toDeserialize(avroData);
    }

    @Benchmark
    public void testProtobufSerializableDecode() throws InvalidProtocolBufferException {
        Reg.User user = Reg.User.parseFrom(protobufData);
    }

    @Benchmark
    public void testThriftSerializableDecode() throws TException {
        com.joseph.learn.serializable.thrift.User user = UserThriftUtil.toDeserialize(thriftData);
    }


}
