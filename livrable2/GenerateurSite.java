class GenerateurSite extends Program {

    final char NEW_LINE = '\n';
    final String INDEX = 
            "        <h2>Tout ce que vous avez toujours voulu savoir sur les vieux ordis sans jamais avoir osé le demander !</h2>"  + NEW_LINE +
            "          <p>"  + NEW_LINE +
            "Bienvenue dans le musée virtuel d'ordinateurs mythiques de l'histoire de l'informatique. Vous trouverez ici des éléments sur quelques machines qui ont marqué l'histoire de l'informatique que cela soit par leurs caractéristiques techniques ou l'impact commercial qu'elles ont eu et qui ont contribué au développement du secteur informatique."  + NEW_LINE +
            "          </p>";

    String rechercherValeur(String chaine, String cle) {
        String valeur = "";
        int indice = 0;
        while (indice < length(chaine) && indice+length(cle) < length(chaine) && !equals(cle, substring(chaine, indice, indice+length(cle)))) {
            indice = indice + 1;
        }
        if (indice < length(chaine)-length(cle)) {
            int indiceRetourLigne = indice;
            while (indiceRetourLigne < length(chaine) && charAt(chaine, indiceRetourLigne) != NEW_LINE) {
                indiceRetourLigne = indiceRetourLigne + 1;
            }
            valeur = substring(chaine, indice+length(cle), indiceRetourLigne);
        }
        return valeur;
    }

    String genererCorps(String content){
        return 
            "<!DOCTYPE html>" + NEW_LINE +
            "<html lang=\"fr\">" + NEW_LINE +
            "  <head>"  + NEW_LINE +
            "    <title>Ordinateurs mythiques</title>"  + NEW_LINE +
            "    <meta charset=\"utf-8\">"  + NEW_LINE +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">"  + NEW_LINE +
            "  </head>"  + NEW_LINE +
            "  <body>"  + NEW_LINE +
            "    <header>"  + NEW_LINE +
            "      <h1>Ordinateurs mythiques</h1>"  + NEW_LINE +
            "    </header>"  + NEW_LINE +
            "    <nav>"  + NEW_LINE +
            "      <ul>"  + NEW_LINE +
            "        <li><a href=\"index.html\">Accueil</a></li>"  + NEW_LINE +
            "        <li><a href=\"produit1.html\">Produit 1</a></li>"  + NEW_LINE +
            "        <li><a href=\"produit2.html\">Produit 2</a></li>"  + NEW_LINE +
            "        <li><a href=\"produit3.html\">Produit 3</a></li>"  + NEW_LINE +
            "        <li><a href=\"produit4.html\">Produit 4</a></li>"  + NEW_LINE +
            "        <li><a href=\"produit5.html\">Produit 5</a></li>"  + NEW_LINE +
            "      </ul>"  + NEW_LINE +
            "    </nav>"  + NEW_LINE +
            "    <main>"  + NEW_LINE +
            "      <section>"  + NEW_LINE + content + NEW_LINE +
            "      </section>"  + NEW_LINE +
            "    </main>"  + NEW_LINE +
            "  </body>"  + NEW_LINE +
            "</html>";
    }

    String genererProduit(String nomFichier) {
        final String CONTENU     = fileAsString(nomFichier);
        final String NOM         = rechercherValeur(CONTENU, "nom : ");
        final String DATE        = rechercherValeur(CONTENU, "date : ");
        final String ENTREPRISE  = rechercherValeur(CONTENU, "entreprise : ");
        final String PRIX        = rechercherValeur(CONTENU, "prix : ");
        final String DESCRIPTION = rechercherValeur(CONTENU, "description : ");

        return 
            "        <h2>" + NOM + " (" + ENTREPRISE + ")</h2>" + NEW_LINE +
            "        <h3>" + PRIX + " (Sortie en " + DATE + ")</h3>" + NEW_LINE +
            "        <p>" + NEW_LINE +
            DESCRIPTION + NEW_LINE + 
            "        </p>";
    }

    void algorithm() {
        stringAsFile("output/index.html", genererCorps(INDEX));
        String[] fichiers = new String[5];
        for (int i=0; i<length(fichiers); i++){
            fichiers[i] = "produit" + (i+1);
            stringAsFile("output/" + fichiers[i] + ".html", genererCorps(genererProduit("data/"+ fichiers[i] + ".txt")));
        }
    }
}