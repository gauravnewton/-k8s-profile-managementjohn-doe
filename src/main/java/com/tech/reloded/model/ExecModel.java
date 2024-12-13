/*
 * Copyright gauravnewton
 * 13-12-2024 at 1:1 IST
 */

package com.tech.reloded.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * The type Exec model.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExecModel {
    private String apiVersion;
    private List<String> args;
    private String command;
}
