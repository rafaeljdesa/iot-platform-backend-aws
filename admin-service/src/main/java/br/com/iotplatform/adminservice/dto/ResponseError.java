package br.com.iotplatform.adminservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError implements Serializable {

    private Integer status;
    private String reason;
    private String message;
    private Map<String, String> errors;
    private LocalDateTime timestamp;

}
