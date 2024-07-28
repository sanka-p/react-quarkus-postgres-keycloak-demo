package org.demo.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SuccessResponse<T> implements Response<T>{
    private int statusCode;
    private T body;
}
