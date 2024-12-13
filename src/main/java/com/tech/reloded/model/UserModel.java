/*
 * Copyright gauravnewton
 * 13-12-2024 at 0:55 IST
 */

package com.tech.reloded.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * The type User model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserModel {
    private String name;
    private ExecModel exec;
}
