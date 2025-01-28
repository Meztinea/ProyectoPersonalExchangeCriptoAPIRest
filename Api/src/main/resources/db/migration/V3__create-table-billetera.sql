CREATE TABLE billetera (
    id_usuario INT NOT NULL,
    id_criptomoneda INT NOT NULL,
    cantidad DECIMAL(18,8) NOT NULL,
    fecha_creacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id_usuario, id_criptomoneda),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (id_criptomoneda) REFERENCES criptomonedas(id_criptomoneda) ON DELETE CASCADE
) ENGINE=InnoDB;