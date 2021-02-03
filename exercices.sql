-- exercices 1
SELECT prenom, nodept FROM employe
INNER JOIN dept
ON employe.nodep = dept.nodept;

-- exercices 2 
SELECT nodept AS 'Numéro de départements', dept.nom AS 'nom départements', employe.nom AS 'Nom employé' FROM employe
INNER JOIN dept
ON employe.nodep = dept.nodept
ORDER BY nodept DESC;

-- exercices 3
DELIMITER |
CREATE TRIGGER InsertSameCountryOrder AFTER INSERT ON `order details`
	FOR EACH ROW 
	BEGIN
		DECLARE productCountry VARCHAR;
		SET productCountry = (SELECT Country FROM suppliers INNER JOIN products ON suppliers.SupplierID = products.SupplierID WHERE products.ProductID = NEW.ProductID);
		if (SELECT Country FROM suppliers 
			 INNER JOIN products
			 ON suppliers.SupplierID = products.SupplierID
			 WHERE products.ProductID = NEW.ProductID) != (SELECT Country FROM orders
			 INNER JOIN customers
			 ON orders.CustomerID = customers.CustomerID
			 WHERE orders.OrderID = NEW.OrderID) then 
			 	SIGNAL SQLSTATE '40000' SET MESSAGE_TEXT = 'Produit non disponible dans ce pays';
			END if;		 
	END |
DELIMITER ;




