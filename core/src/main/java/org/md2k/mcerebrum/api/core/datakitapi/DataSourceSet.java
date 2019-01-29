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

package org.md2k.mcerebrum.api.core.datakitapi;

/**
 * This class allows <code>DataSource</code>s to be grouped into sets.
 */
public class DataSourceSet {
    private DataSource[] dataSource;
    private int status;

    /**
     * Constructor
     * This constructor takes an array of <code>DataSource</code>s and a status value.
     *
     * @param dataSources <code>DataSources</code> to add to the set.
     * @param status      Status
     */
    DataSourceSet(DataSource[] dataSources, int status) {
        this.dataSource = dataSources;
        if (this.dataSource == null) this.dataSource = new DataSource[0];
        this.status = status;
    }

    /**
     * Returns an array of <code>DataSource</code>s.
     * If <code>dataSource</code> is null, then a new array is initialized.
     *
     * @return An array of <code>DataSource</code>s.
     */
    public DataSource[] getDataSources() {
        if (this.dataSource == null) this.dataSource = new DataSource[0];
        return dataSource;
    }

    /**
     * Returns the status of the set.
     *
     * @return The status of the set.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Compares the passed object to the calling object.
     * If the passed object is not an instance of this class, false is returned.
     *
     * @param toCompare Object to compare.
     * @return True if the objects are equivalent and false if they are not.
     */
    @Override
    public boolean equals(Object toCompare) {
        if (toCompare instanceof DataSourceSet) {
            if (this.getStatus() != ((DataSourceSet) toCompare).getStatus())
                return false;
            for (int i = 0; i < this.getDataSources().length; i++) {
                if (!(this.getDataSources()[i].equals(((DataSourceSet) toCompare).getDataSources()[i])))
                    return false;
            }
            return true;
        } else
            return false;
    }

    /**
     * Calculates and returns a hash code for the calling object.
     * The hash code is calculated using the method denoted in "Effective Java" and described in this Medium
     * <a href="https://medium.com/codelog/overriding-hashcode-method-effective-java-notes-723c1fedf51c">post</a>.
     *
     * @return The hash code of the calling object.
     */
    @Override
    public int hashCode() {
        int result = 17;
        for (DataSource ds : dataSource) {
            result = 31 * result + ds.hashCode();
        }
        result = 31 * result + status;
        return result;
    }
}
