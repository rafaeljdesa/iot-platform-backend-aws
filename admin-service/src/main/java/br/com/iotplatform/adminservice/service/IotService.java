package br.com.iotplatform.adminservice.service;

import br.com.iotplatform.adminservice.dto.IotDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IotService {

    IotDTO save(IotDTO iotDTO);

    boolean deleteById(String id);

    IotDTO findById(String id);

    List<IotDTO> findAll();

    Page<IotDTO> findPageable(Pageable pageable);

}
