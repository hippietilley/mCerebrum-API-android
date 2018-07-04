package org.md2k.mcerebrum.api.datakitapi.datatype;

import android.util.SparseArray;

import org.md2k.mcerebrum.api.datakitapi.datatype.dataannotation.DataAnnotationEnum;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointBoolean;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointByte;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointDouble;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointEnum;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointInt;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointLong;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointObject;
import org.md2k.mcerebrum.api.datakitapi.datatype.datapoint.DataPointString;

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
    DATAPOINT_BOOLEAN(DataPointBoolean.class.hashCode()),
    DATAPOINT_BYTE(DataPointByte.class.hashCode()),
    DATAPOINT_INT(DataPointInt.class.hashCode()),
    DATAPOINT_LONG(DataPointLong.class.hashCode()),
    DATAPOINT_DOUBLE(DataPointDouble.class.hashCode()),
    DATAPOINT_STRING(DataPointString.class.hashCode()),
    DATAPOINT_ENUM(DataPointEnum.class.hashCode()),
    DATAPOINT_OBJECT(DataPointObject.class.hashCode()),
    DATAANNOTATION_ENUM(DataAnnotationEnum.class.hashCode()),
    UNKNOWN(-1);

    int hashCode;
    DataType(int hashCode){
        this.hashCode = hashCode;
    }
    public int getHashCode() {
        return hashCode;
    }
}
