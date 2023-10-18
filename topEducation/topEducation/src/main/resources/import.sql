INSERT INTO public.estudiante (rut_estudiante, apellidos, egreso_colegio, fecha_nacimiento, nombre_colegio, nombres, tipo_colegio, promedio)
VALUES 
('233456789', 'González Rojas', 2019, '2002-05-10', 'Colegio San Juan', 'María Ignacia', 'Particular', 850),
('234567890', 'Moreno Soto', 2018, '2003-07-15', 'Liceo Los Pájaros', 'Krishna Belen', 'Municipal', 720),
('235678901', 'Martínez Pérez', 2020, '2001-12-02', 'Colegio Los Álamos', 'Fiorella Andrea', 'Subvencionado', 780),
('226785012', 'Sánchez Rodríguez', 2018, '2004-03-20', 'Liceo Santa María', 'Diego Alejandro', 'Municipal', 740),
('225895123', 'Rodríguez Valdés', 2019, '2002-09-05', 'Colegio San Antonio', 'Daniela Constanza', 'Particular', 910),
('238901234', 'Miranda López', 2021, '2001-01-18', 'Liceo Los Robles', 'Tomás Andrés', 'Subvencionado', 800),
('239012345', 'González León', 2017, '2003-08-11', 'Colegio Los Pinos', 'Valentina María', 'Particular', 950),
('250123456', 'Hernandez Estrada', 2022, '2000-04-25', 'Liceo Santa Cruz', 'Oscar Isaac', 'Municipal', 730),
('201234567', 'Martínez Rojas', 2019, '2004-11-30', 'Colegio San José', 'Paloma Andrea', 'Subvencionado', 870),
('202253445', 'Sánchez Rodríguez', 2020, '2001-03-08', 'Liceo Los Álamos', 'Matías Ignacio', 'Municipal', 720),
('224456881', 'Valdés Rojas', 2021, '2002-07-14', 'Colegio San Antonio', 'Antonia Paz', 'Particular', 930),
('206599112', 'Pérez Miranda', 2018, '2003-10-21', 'Liceo Los Robles', 'Maximiliano Antonio', 'Subvencionado', 800),
('225456789', 'González Pérez', 2019, '2002-05-10', 'Colegio San Juan', 'María Fernanda', 'Particular', 850),
('254567890', 'Balmaceda Pascal', 2018, '2003-07-15', 'Liceo Los Pájaros', 'Jose Pedro', 'Municipal', 720),
('275678901', 'Moreno Pérez', 2020, '2001-12-02', 'Colegio Los Álamos', 'Diana Andrea', 'Subvencionado', 780),
('206789012', 'Sánchez León', 2018, '2004-03-20', 'Liceo Santa María', 'Diego Alejandro', 'Municipal', 740),
('227890123', 'Rodríguez Valdés', 2019, '2002-09-05', 'Colegio San Antonio', 'Isabella Antonia', 'Particular', 910),
('208901034', 'Pérez Rodríguez ', 2021, '2001-01-18', 'Liceo Los Robles', 'Yerko Andrés', 'Subvencionado', 800),
('209012045', 'González Pérez', 2017, '2003-08-11', 'Colegio Los Pinos', 'Valentina María', 'Particular', 950),
('220173456', 'López González', 2022, '2000-04-25', 'Liceo Santa Cruz', 'Jose Pablo', 'Municipal', 730);


-- Inserciones para arancel con tipo_de_pago = 'Contado'
INSERT INTO public.arancel (id_arancel, cantidad_cuotas, dcto_colegio_procedencia, dcto_media_examenes, dcto_tiempo_egreso, dcto_tipo_pago, monto_pagar, tipo_de_pago, rut_estudiante)
VALUES 
(1, 0, 0, 0, 0, 50, 750000, 'Contado', '233456789'),
(2, 0, 0, 0, 0, 50, 750000, 'Contado', '234567890'),
(3, 0, 0, 0, 0, 50, 750000, 'Contado', '235678901'),
(4, 0, 0, 0, 0, 50, 750000, 'Contado', '226785012'),
(5, 0, 0, 0, 0, 50, 750000, 'Contado', '225895123'),
(6, 0, 0, 0, 0, 50, 750000, 'Contado', '238901234'),
(7, 0, 0, 0, 0, 50, 750000, 'Contado', '239012345'),
(8, 0, 0, 0, 0, 50, 750000, 'Contado', '250123456'),
(9, 0, 0, 0, 0, 50, 750000, 'Contado', '201234567'),
(10, 10, 20, 15, 4, 0, 1125000, 'Contado', '202253445'),
(11, 10, 10, 15, 0, 0, 1350000, 'Cuotas', '224456881'),

(12, 10, 20, 15, 8, 0, 1275000, 'Cuotas', '206599112'),
(13, 7, 0, 15, 8, 0, 1350000, 'Cuotas', '225456789'),
(14, 4, 0, 15, 4, 0, 1425000, 'Cuotas', '254567890'),
(15, 10, 10, 15, 0, 0, 1350000, 'Cuotas', '275678901'),
(16, 10, 20, 15, 0, 0, 1350000, 'Cuotas', '206789012'),
(17, 0, 0, 0, 0, 50, 750000, 'Contado', '227890123'),
(18, 7, 10, 15, 0, 0, 1350000, 'Cuotas', '208901034'),
(19, 7, 0, 15, 8, 0, 1425000, 'Cuotas', '209012045'),
(20, 4, 0, 15, 4, 0, 1425000, 'Cuotas', '220173456');
