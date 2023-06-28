package com.mdbytes.app.rest.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddAlertRequest {

    private Integer productId;

    private Integer userId;

    private double alertPrice;

}
