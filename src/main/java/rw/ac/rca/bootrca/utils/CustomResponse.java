package rw.ac.rca.bootrca.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomResponse<T> {
    String message;
    T data;


    public CustomResponse(String message) {
        this.message = message;
        this.data = null;
    }

}
