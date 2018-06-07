package com.joseph.learn.serializable.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class UserThriftUtil {

    public static byte[] toSerialize(com.joseph.learn.serializable.thrift.User user) throws TException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        TTransport transport = new TIOStreamTransport(out);
        TBinaryProtocol tp = new TBinaryProtocol(transport);
        user.write(tp);
        return out.toByteArray();
    }

    public static com.joseph.learn.serializable.thrift.User toDeserialize(byte[] data) throws TException {
        com.joseph.learn.serializable.thrift.User user = new com.joseph.learn.serializable.thrift.User();
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        TTransport transport = new TIOStreamTransport(bis);
        TBinaryProtocol tp = new TBinaryProtocol(transport);
        user.read(tp);
        return user;
    }
}
