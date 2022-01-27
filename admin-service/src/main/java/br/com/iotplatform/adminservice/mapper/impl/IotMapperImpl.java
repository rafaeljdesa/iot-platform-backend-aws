package br.com.iotplatform.adminservice.mapper.impl;

import br.com.iotplatform.adminservice.dto.IotDTO;
import br.com.iotplatform.adminservice.entity.Iot;
import br.com.iotplatform.adminservice.mapper.IotMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IotMapperImpl implements IotMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public IotDTO toDto(Iot entity) {
        return modelMapper.map(entity, IotDTO.class);
    }

    @Override
    public Iot toEntity(IotDTO dto) {
        return modelMapper.map(dto, Iot.class);
    }

}
