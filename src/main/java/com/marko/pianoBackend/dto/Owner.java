package com.marko.pianoBackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Information about the owner of the question
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Owner(@JsonProperty("display_name") String name) {
}
