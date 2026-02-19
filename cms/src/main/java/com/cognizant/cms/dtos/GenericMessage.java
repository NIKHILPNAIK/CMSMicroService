 package com.cognizant.cms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
  Generic response wrapper that can carry either an object payload or a message.
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericMessage<T> {
    private T object;
    private String message;

    public GenericMessage(T object) {
        this.object = object;
    }

    public GenericMessage(String message) {
        this.message = message;
    }
}