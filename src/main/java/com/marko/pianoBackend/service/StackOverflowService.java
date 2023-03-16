package com.marko.pianoBackend.service;

import com.marko.pianoBackend.dto.StackOverflowRootEntity;
import reactor.core.publisher.Mono;

/**
 * Interface for getting information from StackOverflow api
 * @param <T> root element
 */
public interface StackOverflowService<T extends StackOverflowRootEntity> {
   /**
    * Version StackOverflow api
    */
   String VERSION = "2.3";
   Mono<T> getEntity(String param);
}
