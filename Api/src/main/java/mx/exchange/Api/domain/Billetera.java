package mx.exchange.Api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Table(name = "billetera")
@Entity(name = "Billetera")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Billetera {

    @EmbeddedId
    private BilleteraId id;

    private Double cantidad;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

}
