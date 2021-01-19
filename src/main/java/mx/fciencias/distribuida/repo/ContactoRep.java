package mx.fciencias.distribuida.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import mx.fciencias.distribuida.model.Contacto;

public interface ContactoRep extends PagingAndSortingRepository<Contacto, Integer>{

}
