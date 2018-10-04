/*
 * Copyright (c) 2018, The University of Memphis, MD2K Center of Excellence
 *
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

package org.md2k.mcerebrum.api.core.datakitapi.datatype;

import org.md2k.mcerebrum.api.core.datakitapi.datatype.dataannotation.DataAnnotationEnum;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointBoolean;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointByte;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointDouble;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointEnum;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointInt;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointLong;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointObject;
import org.md2k.mcerebrum.api.core.datakitapi.datatype.datapoint.DataPointString;

/**
 * This class enumerates the types of <code>Data</code> objects that can exist
 * and provides <code>hashCode</code>s for them.
 * The possible types are:
 * <ul>
 * <li>DATAPOINT_BOOLEAN</li>
 * <li>DATAPOINT_BYTE</li>
 * <li>DATAPOINT_INT</li>
 * <li>DATAPOINT_LONG</li>
 * <li>DATAPOINT_DOUBLE</li>
 * <li>DATAPOINT_STRING</li>
 * <li>DATAPOINT_ENUM</li>
 * <li>DATAPOINT_OBJECT</li>
 * <li>DATAANNOTATION_ENUM</li>
 * <li>UNKNOWN</li>
 * </ul>
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

    /**
     * Constructor
     *
     * @param hashCode HashCode for the <code>DataType</code>. This should be the
     *                 <code>hashCode</code> for the specific class of the <code>DataType</code>
     *                 as enumerated above.
     */
    DataType(int hashCode) {
        this.hashCode = hashCode;
    }

    /**
     * Returns the <code>hashCode</code>.
     *
     * @return The <code>hashCode</code>.
     */
    public int getHashCode() {
        return hashCode;
    }
}
