/*
 * Copyright gauravnewton
 * 13-12-2024 at 0:54 IST
 */

package com.tech.reloded.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The type Context model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContextModel {
    private String cluster;
    private String user;
    private String name;
}
