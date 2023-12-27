package rw.ac.rca.bootrca.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomResponse<T> {
    private String message;
    private T data;


    public CustomResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public CustomResponse(T data) {
        this.message = "Operation successful";
        this.data = data;
    }

    public CustomResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public CustomResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

}