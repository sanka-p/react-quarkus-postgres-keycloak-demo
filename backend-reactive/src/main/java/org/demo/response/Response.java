package org.demo.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Response<T> {
    private int statusCode;
    private T body;
}
