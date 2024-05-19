package com.mergeteam.coincontrol.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class DataResponse<T> extends BaseResponse {

    private T data;

    public DataResponse(boolean success, T data) {
        super(success);
        this.data = data;
    }

}
