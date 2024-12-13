/*
 * Copyright gauravnewton
 * 13-12-2024 at 9:1 IST
 */

package com.tech.reloded.exceptions.types;

/**
 * The type File not found exception.
 */
public class FileNotFoundException extends RuntimeException {

    /**
     * Instantiates a new File not found exception.
     *
     * @param message the message
     */
    public FileNotFoundException(String message) {
        super(message);
    }
}
