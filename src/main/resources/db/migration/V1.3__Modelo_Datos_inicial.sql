INSERT INTO consecutivos (ID, CATEGORIA, CONSECUTIVO) VALUES (1,'Externo',0), (2, 'Interno',0), (3, 'Prioritario',0);
INSERT INTO categorias (ID, NOMBRE) VALUES (1,'Externo'), (2, 'Interno'), (3, 'Prioritario');
INSERT INTO asesores (ID, NOMBRE) VALUES (1,'Carlos Lugo'), (2, 'David Lopez'), (3, 'Johan Lopez'), (4, 'Sebastian Velez'), (5, 'Cesar Diaz');

SET GLOBAL EVENT_SCHEDULER = ON;

DELIMITER $$	
CREATE EVENT `reiniciar_consecutivo`
	ON SCHEDULE
		EVERY 1 DAY STARTS '2018-12-08 23:59:59'
	ON COMPLETION PRESERVE
	ENABLE
	COMMENT ''
	DO BEGIN
 		update consecutivos set consecutivo = 0;
	END $$
DELIMITER;
	