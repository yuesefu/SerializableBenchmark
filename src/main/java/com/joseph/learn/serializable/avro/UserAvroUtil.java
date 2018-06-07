package com.joseph.learn.serializable.avro;

import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * ClassName: LogInfoAvroUtil
 * Description: 日志信息Avro序列化工具类
 * date: 2015-12-04 上午09:31:31
 *
 * @author Joseph
 * @version V1.0.5
 */
public class UserAvroUtil {

    public static byte[] toSerialize(User user) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DatumWriter<User> write = new SpecificDatumWriter<User>(User.class);
        Encoder encoder = EncoderFactory.get().binaryEncoder(out, null);
        write.write(user, encoder);
        encoder.flush();
        out.close();
        return out.toByteArray();
    }

    public static User toDeserialize(byte[] data) throws IOException {
        DatumReader<User> reader = new SpecificDatumReader<User>(User.class);
        Decoder decoder = DecoderFactory.get().binaryDecoder(data, null);
        return reader.read(null, decoder);
    }
}
