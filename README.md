
-questions:

1- Le do...while est adapté au menu car le menu doit être affiché au moins une fois avant de tester la condition de sortie. Après chaque choix, le programme vérifie si l'utilisateur a choisi 0 pour quitter. Avec une boucle while, il faudrait vérifier la condition avant la première exécution.

2- Avec int[] b = a, les deux variables référencent le même tableau. Modifier b modifie donc également a.

Pour créer une vraie copie, il faut créer un nouveau tableau et copier les éléments. Les deux tableaux sont alors indépendants et une modification de l'un ne modifie pas l'autre.

3- La méthode subirDegatsBruts() est protected car elle doit être accessible par les classes filles comme Mage, mais elle ne doit pas être accessible directement depuis l'extérieur. Cela permet de conserver l'encapsulation tout en donnant aux sous-classes l'accès dont elles ont besoin.

4- Si on écrit Combattant c = new Mage(...); puis c.attaquer(x), c'est la méthode attaquer() de Mage qui est appelée.

Le type déclaré de la variable est Combattant, mais l'objet réel est un Mage. Java utilise le type réel de l'objet pour choisir la méthode redéfinie à l'exécution. C'est le principe du polymorphisme.

---------

Sur 100 simulations, le Guerrier a remporté 57 tournois, le Voleur 21, le Mage 11 et le Paladin 11.

Dans cette série de simulations, le Guerrier est donc la classe qui a remporté le plus de tournois. Les résultats restent déséquilibrés : le Guerrier gagne encore plus de la moitié des tournois, mais les autres classes gagnent aussi, y compris le Mage. Ces résultats peuvent varier d'une série de 100 simulations à une autre à cause de l'aléatoire.