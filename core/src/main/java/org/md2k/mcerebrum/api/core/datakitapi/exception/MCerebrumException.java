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

package org.md2k.mcerebrum.api.core.datakitapi.exception;

import org.md2k.mcerebrum.api.core.datakitapi.status.MCerebrumStatus;

/**
 * Generic <code>MCerebrumException</code>.
 */
public class MCerebrumException extends Exception {

    /**
     * Constructor
     * This constructor takes in a cause.
     *
     * @param cause What caused the exception to be thrown.
     */
    public MCerebrumException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor
     * This constructor takes in a cause and a message.
     *
     * @param message Message to display when the exception is triggered.
     * @param cause   What caused the exception to be thrown.
     */
    public MCerebrumException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor
     * This constructor take in a message.
     *
     * @param message Message to display when the exception is triggered.
     */
    public MCerebrumException(String message) {
        super(message);
    }

    /**
     * Constructor
     * This constructor take in a status.
     *
     * @param status <code>MCerebrumStatus</code> that triggered the exception.
     */
    public MCerebrumException(int status) {
        super(MCerebrumStatus.getStatusCodeString(status));
    }
}
