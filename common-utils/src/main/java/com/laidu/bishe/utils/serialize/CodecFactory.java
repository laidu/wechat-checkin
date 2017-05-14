package com.laidu.bishe.utils.serialize;

import java.io.IOException;

/**
 * Created by chenwen on 16/9/20.
 */
public interface CodecFactory {
    byte[] serialize(Object obj) throws IOException;

    Object deSerialize(byte[] in) throws IOException;
}
