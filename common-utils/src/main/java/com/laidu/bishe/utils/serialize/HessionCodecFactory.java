package com.laidu.bishe.utils.serialize;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by chenwen on 16/9/20.
 */
@Slf4j
public class HessionCodecFactory implements CodecFactory {
    @Override
    public byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream baos = null;
        HessianOutput output = null;
        try {
            baos = new ByteArrayOutputStream(1024);
            output = new HessianOutput(baos);
            output.startCall();
            output.writeObject(obj);
            output.completeCall();
        } catch (final IOException ex) {
            throw ex;
        } finally {
            if (output != null) {
                try {
                    baos.close();
                } catch (final IOException ex) {
                    log.error("Failed to close stream.", ex);
                }
            }
        }
        return baos.toByteArray();
    }

    @Override
    public Object deSerialize(byte[] in) throws IOException {
        Object obj = null;
        ByteArrayInputStream bais = null;
        HessianInput input = null;
        try {
            bais = new ByteArrayInputStream(in);
            input = new HessianInput(bais);
            input.startReply();
            obj = input.readObject();
            input.completeReply();
        } catch (final IOException ex) {
            throw ex;
        } catch (final Throwable e) {
            log.error("Failed to decode object.", e);
        } finally {
            if (input != null) {
                try {
                    bais.close();
                } catch (final IOException ex) {
                    log.error("Failed to close stream.", ex);
                }
            }
        }
        return obj;
    }
}
