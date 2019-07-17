package com.stylefeng.guns.rest.modular.film.serialization;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.support.hessian.Hessian2ObjectInput;
import com.alibaba.dubbo.common.serialize.support.hessian.Hessian2ObjectOutput;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Hessian2Serialization implements Serialization{

    public static final byte ID = 2;

    @Override
    public byte getContentTypeId() {
        return ID;
    }

    @Override
    public String getContentType() {
        return "x-application/hessian2";
    }

    @Override
    public ObjectOutput serialize(URL url, OutputStream output) throws IOException {
        return new Hessian2ObjectOutput(output);
    }

    @Override
    public ObjectInput deserialize(URL url, InputStream input) throws IOException {
        return new Hessian2ObjectInput(input);
    }
}
