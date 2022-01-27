package br.com.iotplatform.adminservice.service.impl;

import br.com.iotplatform.adminservice.dto.IotDTO;
import br.com.iotplatform.adminservice.entity.Iot;
import br.com.iotplatform.adminservice.mapper.IotMapper;
import br.com.iotplatform.adminservice.repository.IotRepository;
import br.com.iotplatform.adminservice.service.IotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IotServiceImpl implements IotService {

    @Autowired
    private IotRepository repository;

    @Autowired
    private IotMapper iotMapper;

    @Override
    public IotDTO save(IotDTO iotDTO) {
        Iot iot = iotMapper.toEntity(iotDTO);
        iot = repository.save(iot);
        return iotMapper.toDto(iot);
    }

    @Override
    public boolean deleteById(String id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public IotDTO findById(String id) {
        return repository.findById(id)
                .map(iotMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<IotDTO> findAll() {
        return repository.findAll().stream()
                .map(iotMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<IotDTO> findPageable(Pageable pageable) {
        return repository.findAll(pageable)
                .map(iotMapper::toDto);
    }
}
