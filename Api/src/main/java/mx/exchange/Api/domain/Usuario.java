package mx.exchange.Api.domain;

import jakarta.persistence.*;
import lombok.*;
import mx.exchange.Api.dto.ActualizarUsuarioDTO;
import mx.exchange.Api.dto.CrearUsuarioDTO;

import java.time.LocalDateTime;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La base de datos se encarga de asignar el ID
    @Column(name = "id_usuario")
    private Long id;
    @Column(name = "nombre_completo")
    private String nombre;
    private String telefono;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public Usuario(CrearUsuarioDTO usuario) {
        this.nombre = usuario.nombre();
        this.telefono = usuario.telefono();
        this.correoElectronico = usuario.correoElectronico();
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    public void actualizarDatos(ActualizarUsuarioDTO usuario){

        if (usuario.nombre() != null)
            this.nombre = usuario.nombre();

        if(usuario.telefono() != null)
            this.telefono = usuario.telefono();

        if (usuario.correoElectronico() != null)
            this.correoElectronico = usuario.correoElectronico();

        this.fechaActualizacion = LocalDateTime.now();
    }
}
