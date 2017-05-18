package com.laidu.bishe.utils.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

/**
 * Created by huangpin on 16/11/16.
 */
public class JAXBUtil<T> {
    /**
     * 对象转换为xml
     *
     * @param element
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    public String marshal(T element) throws JAXBException, IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            JAXBContext jc = JAXBContext.newInstance(element.getClass());
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(element, os);
            String xml = new String(os.toByteArray(), "UTF-8");
            return xml;
        } finally {
            os.close();
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public T unmarshal(Class c, String xml) throws JAXBException {
        JAXBContext context;
        context = JAXBContext.newInstance(c);
        Unmarshaller unmarshal = context.createUnmarshaller();
        T obj = (T) unmarshal.unmarshal(new StringReader(xml));
        return obj;

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public T unmarshal(Class c, InputStream is) throws JAXBException {
        JAXBContext context;
        context = JAXBContext.newInstance(c);
        Unmarshaller unmarshal = context.createUnmarshaller();
        T obj = (T) unmarshal.unmarshal(is);
        return obj;
    }
}
