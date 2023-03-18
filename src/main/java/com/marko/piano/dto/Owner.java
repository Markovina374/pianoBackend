package com.marko.piano.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Information about the owner of the question
 * @param name User name
 * @author Markov Vlad
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Owner(@JsonProperty("display_name") String name) {
}
