package io.github.eealba.example;

import lombok.Data;

@Data
public class PrimitiveData {
    byte dataByte;
    short dataShort;
    int dataInt;
    long dataLong;
    float dataFloat;
    double dataDouble;
    boolean dataBoolean;
    char dataChar;
    public static PrimitiveData maxValues() {
        PrimitiveData data = new PrimitiveData();
        data.setDataByte(Byte.MAX_VALUE);
        data.setDataShort(Short.MAX_VALUE);
        data.setDataInt(Integer.MAX_VALUE);
        data.setDataLong(Long.MAX_VALUE);
        data.setDataFloat(Float.MAX_VALUE);
        data.setDataDouble(Double.MAX_VALUE);
        data.setDataBoolean(true);
        data.setDataChar(Character.MAX_VALUE);
        return data;
    }
    public static PrimitiveData minValues() {
        PrimitiveData data = new PrimitiveData();
        data.setDataByte(Byte.MIN_VALUE);
        data.setDataShort(Short.MIN_VALUE);
        data.setDataInt(Integer.MIN_VALUE);
        data.setDataLong(Long.MIN_VALUE);
        data.setDataFloat(Float.MIN_VALUE);
        data.setDataDouble(Double.MIN_VALUE);
        data.setDataBoolean(false);
        data.setDataChar(Character.MIN_VALUE);
        return data;
    }
}

