package mx.fciencias.distribuida.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import mx.fciencias.distribuida.model.Notificacion;

public interface NotificacionRep extends PagingAndSortingRepository<Notificacion, Integer>{

}
