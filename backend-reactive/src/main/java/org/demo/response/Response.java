package org.demo.response;

import io.vertx.core.json.JsonObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private int statusCode;
    private JsonObject payload;

    private Response(int statusCode, JsonObject payload){
        this.statusCode = statusCode;
        this.payload = payload;
    }

    public static Response successResponse(int successCode, Object payload){
        return new Response(successCode, JsonObject.mapFrom(payload));
    }

    public static Response errorResponse(int errorCode, String payload){
        return new Response(errorCode, JsonObject.mapFrom(payload));
    }
}
