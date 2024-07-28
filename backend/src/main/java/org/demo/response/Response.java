package org.demo.response;

public interface Response<T> {
    void setStatusCode(int statusCode);
    int getStatusCode();

    T getBody();
    void setBody(T object);
}
