/*
 * Copyright gauravnewton
 * 13-12-2024 at 9:0 IST
 */

package com.tech.reloded.exceptions.types;

/**
 * The type Write permission exception.
 */
public class WritePermissionException extends RuntimeException {

    /**
     * Instantiates a new Write permission exception.
     *
     * @param message the message
     */
    public WritePermissionException(String message) {
        super(message);
    }
}
