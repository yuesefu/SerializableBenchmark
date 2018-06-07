package com.joseph.jmh;

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
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3)
@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
@Threads(4)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class SerializableEncodeTest {

    @Benchmark
    public void testJavaSerializableEncode() throws IOException {
        User user = new User().setAge(1).setUsername("Test").setAddress("江苏省苏州市工业园区XX路XX号");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(user);
        os.flush();
        os.close();
        byte[] data = bos.toByteArray();
        bos.close();
    }

    @Benchmark
    public void testAvroSerializableEncode() throws IOException {
        com.joseph.learn.serializable.avro.User.Builder builder = com.joseph.learn.serializable.avro.User.newBuilder
            ().setAge(1).setUsername("Test").setAddress("江苏省苏州市工业园区XX路XX号");
        com.joseph.learn.serializable.avro.User user = builder.build();
        UserAvroUtil.toSerialize(user);
    }

    @Benchmark
    public void testProtobufSerializableEncode() {
        Reg.User.Builder builder = Reg.User.newBuilder();
        Reg.User user = builder.setAge(1).setUsername("Test").setAddress("江苏省苏州市工业园区XX路XX号").build();
        byte[] result = user.toByteArray();
    }

    @Benchmark
    public void testThriftSerializableEncode() throws TException {
        com.joseph.learn.serializable.thrift.User user = new com.joseph.learn.serializable.thrift.User();
        user.setAge(1).setUsername("Test").setAddress("江苏省苏州市工业园区XX路XX号");
        UserThriftUtil.toSerialize(user);
    }
}
