-- connaitre le type de moteur de la base remplacer papyrus par le nom de votre choix
SELECT TABLE_NAME, ENGINE FROM information_schema.`TABLES` WHERE TABLE_SCHEMA = 'papyrus'

-- interdire la modification de la table reservation 
DELIMITER |
    CREATE TRIGGER modif_reservation AFTER UPDATE ON reservation
    FOR EACH ROW
    BEGIN 
		if NEW.res_id != OLD.res_id OR
			NEW.res_cha_id != OLD.res_cha_id OR
			NEW.res_cli_id != OLD.res_cli_id OR 
			NEW.res_date != OLD.res_date OR 
			NEW.res_date_debut != OLD.res_date_debut OR 
			NEW.res_date_fin != OLD.res_date_fin OR 
			NEW.res_prix != OLD.res_prix OR 
			NEW.res_arrhes != OLD.res_arrhes then 
			SIGNAL SQLSTATE '40000' SET MESSAGE_TEXT = 'Modification interdite!!!';
		END if;
	END |
DELIMITER ;

-- limiter à 10 le nombre d'insertion dans la table 
DELIMITER |
CREATE TRIGGER insert_reservation AFTER INSERT ON reservation  
	FOR EACH ROW 
	BEGIN
		if (SELECT COUNT(res_id) FROM reservation) > 10 then 
			SIGNAL SQLSTATE '40000' SET MESSAGE_TEXT = 'Ajout impossible vous avez atteind 10 réservation!!!';
		END if;
	END |
DELIMITER ;

-- limiter à 3 le nombre d'insertion par client dans la table reservation 
DELIMITER |
CREATE TRIGGER insert_reservation2 AFTER INSERT ON reservation  
	FOR EACH ROW 
	BEGIN
		if (SELECT COUNT(res_cli_id) FROM reservation GROUP BY res_cli_id HAVING res_cli_id = NEW.res_cli_id) > 3 then 
			SIGNAL SQLSTATE '40000' SET MESSAGE_TEXT = 'Ajout impossible vous avez atteind 3 réservation par client!!!';
		END if;
	END |
DELIMITER ;

-- limiter le nombre de chambre a 50 
DELIMITER |
CREATE TRIGGER insert_chambre AFTER INSERT ON chambre  
	FOR EACH ROW 
	BEGIN
		if (SELECT SUM(cha_capacite)FROM chambre GROUP BY cha_hot_id HAVING cha_hot_id = NEW.cha_hot_id) > 50 then 
			SIGNAL SQLSTATE '40000' SET MESSAGE_TEXT = 'Quantité de chambre limité à 50 par hotel!!!';
		END if;
	END |
DELIMITER ;

-- mise a jour du total commande avec application de la remise
-- pour l'update
DELIMITER |
CREATE TRIGGER update_maj_total AFTER UPDATE ON lignedecommande  
	FOR EACH ROW 
	BEGIN
        DECLARE id_c INT;
        DECLARE tot DOUBLE;
        declare rem DOUBLE;
        SET id_c = NEW.id_commande; -- nous captons le numéro de commande concerné
        SET tot = (SELECT sum(prix*quantite) FROM lignedecommande WHERE id_commande=id_c); -- on recalcul le total
        SET rem = (SELECT tot*(remise/100) FROM commande WHERE id = id_c);
        if (SELECT remise FROM commande WHERE id = id_c) > 0 then
        UPDATE commande SET total= (tot-rem) WHERE id=id_c; -- on stocke le total dans la table commande
        else  
        UPDATE commande SET total = tot WHERE id=id_c; -- on stocke le total dans la table commande
        end if;
    END |
DELIMITER ;

-- pour l'insert
DELIMITER |

CREATE TRIGGER insert_maj_total AFTER UPDATE ON lignedecommande
    FOR EACH ROW
    BEGIN
        DECLARE id_c INT;
        DECLARE tot DOUBLE;
        declare rem DOUBLE;
        SET id_c = NEW.id_commande; -- nous captons le numéro de commande concerné
        SET tot = (SELECT sum(prix*quantite) FROM lignedecommande WHERE id_commande=id_c); -- on recalcul le total
        SET rem = (SELECT tot*(remise/100) FROM commande WHERE id = id_c);
        if (SELECT remise FROM commande WHERE id = id_c) > 0 then
        UPDATE commande SET total= (tot-rem) WHERE id=id_c; -- on stocke le total dans la table commande
        else  
        UPDATE commande SET total = tot WHERE id=id_c; -- on stocke le total dans la table commande
        end if;
    END |
DELIMITER ;

-- pour le delete
CREATE TRIGGER delete_maj_total AFTER UPDATE ON lignedecommande
    FOR EACH ROW
    BEGIN
        DECLARE id_c INT;
        DECLARE tot DOUBLE;
        SET id_c = OLD.id_commande; -- nous captons le numéro de commande concerné
        SET tot = (SELECT sum(prix*quantite) FROM lignedecommande WHERE id_commande=id_c); -- on recalcul le total
        UPDATE commande SET total=tot WHERE id=id_c; -- on stocke le total dans la table commande
    END
DELIMITER ;

