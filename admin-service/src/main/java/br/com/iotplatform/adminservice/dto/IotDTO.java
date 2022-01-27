package br.com.iotplatform.adminservice.dto;

import br.com.iotplatform.adminservice.enums.IotTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IotDTO implements Serializable {

    private String id;

    private String macAddress;

    @NotNull
    private BigDecimal latitude;

    @NotNull
    private BigDecimal longitude;

    @NotNull
    private String model;

    @NotNull
    private IotTypeEnum type;

}
