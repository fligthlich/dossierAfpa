--1 - Liste des contacts français 
SELECT CompanyName AS 'Société', ContactName AS 'Contact', ContactTitle AS 'Fonction', Phone AS 'Téléphone'
FROM customers
WHERE Country = 'france';

--2 - Produits vendus par le fournisseur « Exotic Liquids »
SELECT ProductName AS 'Produits', UnitPrice AS 'Prix' 
FROM Products 
INNER JOIN Suppliers
ON Products.SupplierID = Suppliers.SupplierID
WHERE CompanyName = 'Exotic Liquids';

--3 - Nombre de produits vendus par les fournisseurs Français dans l’ordre décroissant
SELECT CompanyName AS 'Fournisseur', COUNT(*) AS 'Nbs produits'
FROM suppliers
INNER JOIN products
ON suppliers.SupplierID = products.SupplierID
WHERE country = 'france'
GROUP BY companyName
ORDER BY COUNT(*) DESC;
--4 - Liste des clients Français ayant plus de 10 commandes
SELECT CompanyName AS 'Client', COUNT(*) as 'Nbr de commande'
FROM customers
INNER JOIN orders
ON customers.CustomerID = orders.CustomerID
WHERE country = 'france'
GROUP BY CompanyName
HAVING COUNT(*) > 10;
--5 - Liste des clients ayant un chiffre d’affaires > 30.000
SELECT companyName AS 'client' , SUM(`order details`.unitPrice*quantity) AS 'CA', country AS 'Pays'
FROM products
INNER JOIN `order details`
ON products.ProductID = `order details`.ProductID
INNER JOIN orders
ON `order details`.orderID = orders.OrderID
INNER JOIN customers
ON orders.CustomerID = customers.CustomerID
GROUP BY companyName,country
HAVING CA > 30000
ORDER BY CA DESC;

--6 - Liste des pays dont les clients ont passé commande de produits fournis par « Exotic Liquids » 
SELECT DISTINCT customers.country AS 'Pays'
FROM suppliers
INNER JOIN products
ON suppliers.SupplierID = products.SupplierID
INNER JOIN `order details`
ON products.ProductID = `order details`.ProductID
INNER JOIN orders
ON `order details`.orderID = orders.OrderID
INNER JOIN customers
ON orders.CustomerID = customers.CustomerID
WHERE suppliers.CompanyName = 'Exotic Liquids'
ORDER BY pays;
--7 – Montant des ventes de 1997
SELECT SUM(unitPrice*quantity) AS 'Montant des ventes 97' 
FROM `order details`
INNER JOIN orders
ON `order details`.orderID = orders.OrderID
WHERE YEAR(OrderDate) = '1997';
--8 – Montant des ventes de 1997 mois par mois
SELECT MONTH(OrderDate) AS 'Mois 97', SUM(unitPrice*quantity) AS 'Montant des ventes 97' 
FROM `order details`
INNER JOIN orders
ON `order details`.orderID = orders.OrderID
WHERE YEAR(OrderDate) = '1997'
GROUP BY MONTH(orderDate);
--9 – Depuis quelle date le client « Du monde entier » n’a plus commandé ?
SELECT orders.OrderDate AS 'Date de la dernière commande'
FROM orders
INNER JOIN customers
ON orders.CustomerID = customers.CustomerID
WHERE customers.CompanyName = 'Du monde entier'
ORDER BY orderDate DESC
LIMIT 1;

-- avec procédure et paramètre 
DELIMITER |

	CREATE PROCEDURE lastOrderDate(IN NameCompany VARCHAR(50))
	BEGIN 
		SELECT orders.OrderDate AS 'Date de la dernière commande'
		FROM orders
		INNER JOIN customers
		ON orders.CustomerID = customers.CustomerID
		WHERE customers.CompanyName = NameCompany
		ORDER BY orderDate DESC
		LIMIT 1;
	END |

DELIMITER ;

--10 – Quel est le délai moyen de livraison en jours ?
SELECT ROUND(avg(DATEDIFF(ShippedDate,orderDate)))AS 'Délai moyen de livraison' 
FROM orders
WHERE shippedDate IS NOT NULL;

-- avec procédure sans paramètre
DELIMITER |

	CREATE PROCEDURE avgDeliveryTime()
	BEGIN 
	SELECT ROUND(avg(DATEDIFF(ShippedDate, orderDate)))AS 'Délai moyen de livraison en jours' 
	FROM orders;
	END |

DELIMITER ;

-- exercices trigger
DELIMITER |
CREATE TRIGGER InsertSameCountryOrder AFTER INSERT ON `order details`
FOR EACH ROW
BEGIN
	DECLARE productCountry VARCHAR(50); 
	DECLARE customerCountry VARCHAR(50); 
	SET productCountry = (SELECT Country FROM suppliers INNER JOIN products ON suppliers.SupplierID = products.SupplierID WHERE products.ProductID = NEW.ProductID); 
	SET customerCountry = (SELECT Country FROM orders INNER JOIN customers ON orders.CustomerID = customers.CustomerID WHERE orders.OrderID = NEW.OrderID); 
	IF productCountry != customerCountry THEN  
		SIGNAL SQLSTATE '40000' SET MESSAGE_TEXT = 'Produit non disponible dans ce pays';
	END IF;		 
END |
DELIMITER ;