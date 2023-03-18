package com.marko.piano.service;

import com.marko.piano.dto.StackOverflowMainEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Interface for getting information from StackOverflow api
 * @author Markov Vlad
 * @param <T> main element
 */
@Service
public interface StackOverflowService<T extends StackOverflowMainEntity> {
   /**
    * Get entities by parameter from StackOverflow api
    * @param param some parameter
    * @return entities
    */
   Flux<T> getEntities(String param);
}


