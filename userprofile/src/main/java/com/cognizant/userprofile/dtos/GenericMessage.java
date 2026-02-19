package com.cognizant.userprofile.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Object --> payload it will be
//message -> readable text either error or null
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericMessage<T> {
    private T object;
    private String message;

    public GenericMessage(T object) { this.object = object; }
    public GenericMessage(String message) { this.message = message; }
}