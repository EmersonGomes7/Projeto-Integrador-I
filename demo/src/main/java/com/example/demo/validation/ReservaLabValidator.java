package com.example.demo.validation;


import com.example.demo.usuario_reserva_lab.DTOReservaLab;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaLabValidator implements ConstraintValidator<ValidReservaLab, DTOReservaLab> {

    @Override
    public boolean isValid(DTOReservaLab reservaLab, ConstraintValidatorContext context) {
        // Verifica se data_fim é depois ou igual a data_inicio
        LocalDate dataInicio = reservaLab.data_inicio();
        LocalDate dataFim = reservaLab.data_fim();
        if (dataFim.isBefore(dataInicio)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("A data de término deve ser maior ou igual à data de início")
                    .addPropertyNode("data_fim")
                    .addConstraintViolation();
            return false;
        }

        // Verifica se hora_fim é depois de hora_inicio
        LocalTime horaInicio = reservaLab.hora_inicio();
        LocalTime horaFim = reservaLab.hora_fim();
        if (horaFim.isBefore(horaInicio)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("A hora de término deve ser depois da hora de início")
                    .addPropertyNode("hora_fim")
                    .addConstraintViolation();
            return false;
        }

        // Se tudo estiver certo, retorna true
        return true;
    }
}
