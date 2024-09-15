package com.example.demo.usuario_reserva_lab;

import com.example.demo.usuario_reserva_lab.Usuario_reserva_lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Usuario_reserva_lab, Long> {

    @Query("SELECT r FROM usuario_reserva_lab r WHERE r.id_lab_reservado.id_lab = :idLaboratorio")
    List<Usuario_reserva_lab> findReservasAtivasByLaboratorio(@Param("idLaboratorio") Long idLaboratorio);

    @Query("SELECT u FROM usuario_reserva_lab u WHERE u.idSolicitante.idUsuario = :idSolicitante")
    List<Usuario_reserva_lab> findByIdSolicitante_Id(@Param("idSolicitante") Long idSolicitante);
    //List<Usuario_reserva_lab> findByIdSolicitante_IdUsuario(@Param("idSolicitante") Long idSolicitante);

}
