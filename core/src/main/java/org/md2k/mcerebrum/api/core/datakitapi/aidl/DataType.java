package org.md2k.mcerebrum.api.core.datakitapi.aidl;

import android.util.SparseArray;

/*
 * Copyright (c) 2016, The University of Memphis, MD2K Center
 * - Syed Monowar Hossain <monowar.hossain@gmail.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
public enum DataType {
    UNKNOWN((byte) -1), BYTE((byte) 1), BYTE_ARRAY((byte) 2), BOOLEAN((byte) 3), BOOLEAN_ARRAY((byte) 4), INT((byte) 5), INT_ARRAY((byte) 6), LONG((byte) 7), LONG_ARRAY((byte) 8), DOUBLE((byte) 9), DOUBLE_ARRAY((byte) 10), STRING((byte) 11), STRING_ARRAY((byte) 12), ENUM((byte) 13), ENUM_ARRAY((byte) 14), OBJECT((byte) 15), OBJECT_ARRAY((byte) 16);
    private byte value;
    private static final SparseArray<DataType> sparseArray = new SparseArray<>();

    static {
        for (DataType type : DataType.values()) {
            sparseArray.put(type.value, type);
        }
    }
    public DataType getType() {
        return DataType.valueOf(value);
    }
    DataType(byte value){
        this.value = value;
    }
    public byte getValue() {
        return value;
    }
    public static DataType valueOf(byte value){
        return sparseArray.get((int) value);
    }
}
