class GenerateurSite extends Program {
    
    final char NEW_LINE = '\n';
    final String ENTETE = "<!DOCTYPE html>" + NEW_LINE +
                          "<html lang=\"fr\">" + NEW_LINE;

    final int IDX_NOM = 0;
    final int IDX_DATE = 1;
    final int IDX_ENTREPRISE = 2;
    final int IDX_PRIX = 3;
    final int IDX_DESCRIPTION = 4;

    final String[] CHAMPS = new String[]{"nom : ", "date : ", "entreprise : ", "prix : ", "description : "};

    String rechercherValeur(String chaine, String cle) {
        String valeur = "";
        int indice = 0;
        while (indice < length(chaine) && indice+length(cle) < length(chaine) && 
               !equals(cle, substring(chaine, indice, indice+length(cle)))) {
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

    String genererHead(String titre) {
        return 
            "  <head>" + NEW_LINE + 
            "    <title>" + titre + "</title>" + NEW_LINE + 
            "    <meta charset=\"utf-8\">" + NEW_LINE +  
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">" + NEW_LINE +  
            "  </head>" + NEW_LINE;
    }

    String genererHeader(String titre) {
        return
            "    <header>" + NEW_LINE +
            "      <h1>" + titre + "</h1>" + NEW_LINE +
            "    </header>" + NEW_LINE;
    }

    String genererNav(String[][] tableau, int numProduit) {
        String chaine = 
            "    <nav>" + NEW_LINE +
            "      <ul>" + NEW_LINE +
            "        <li><a href=\"index.html\">Accueil</a></li>" + NEW_LINE;
        for(int i=numProduit; i<length(tableau, 1); i++){
            if (i - numProduit < 5){
                chaine = chaine + "        <li><a href=\"produit" + (i+1) + ".html\">" + tableau[i][IDX_NOM] +"</a></li>" + NEW_LINE;
            }
        }
        chaine = chaine +
            "      </ul>" + NEW_LINE +
            "    </nav>" + NEW_LINE;
        return chaine;
    }

    String genererNavAccueil(){
            return
                "    <nav>" + NEW_LINE +
                "      <ul>" + NEW_LINE +
                "        <li><a href=\"index.html\">Accueil</a></li>" + NEW_LINE +
                "        <li><a href=\"produit1.html\">Altair 8800</a></li>" + NEW_LINE +
                "        <li><a href=\"produit2.html\">NeXT Computer</a></li>" + NEW_LINE +
                "        <li><a href=\"produit3.html\">Sinclair ZX Spectrum</a></li>" + NEW_LINE +
                "        <li><a href=\"produit4.html\">Amiga 1000</a></li>" + NEW_LINE +
                "        <li><a href=\"produit5.html\">ENIAC</a></li>" + NEW_LINE +
                "      </ul>" + NEW_LINE +
                "    </nav>" + NEW_LINE;
    }

    String genererContenuProduit(String nom, String date, String entreprise, String prix, String description) {
        return             
            "    <main>" + NEW_LINE +
            "      <section>" + NEW_LINE +
            "        <h2>" + nom + " (" + entreprise + ")</h2>" + NEW_LINE +
            "        <h3>" + prix + " (Sortie en " + date + ")</h3>" + NEW_LINE +
            "        <p>" + NEW_LINE +
            description + NEW_LINE + 
            "        </p>" + NEW_LINE + 
            "      </section>" + NEW_LINE +
            "    </main>" + NEW_LINE;
    }

    String genererPageProduit(String[][] tableau, int numProduit, String head_titre) {
        final String NOM         = tableau[numProduit][IDX_NOM];
        final String DATE        = tableau[numProduit][IDX_DATE];
        final String ENTREPRISE  = tableau[numProduit][IDX_ENTREPRISE];
        final String PRIX        = tableau[numProduit][IDX_PRIX];
        final String DESCRIPTION = tableau[numProduit][IDX_DESCRIPTION];

        return 
            ENTETE + 
            genererHead(head_titre) + 
            "  <body>" + NEW_LINE + 
            genererHeader(head_titre) + 
            genererNav(tableau, numProduit) + 
            genererContenuProduit(NOM, DATE, ENTREPRISE, PRIX, DESCRIPTION) + 
            "  </body>" + NEW_LINE + 
            "</html>" + NEW_LINE;
    }

    String genererAccueil(String head_titre) {
        return ENTETE +
            genererHead(head_titre) +
            "  <body>" + NEW_LINE + 
            genererHeader(head_titre) + 
            genererNavAccueil() +
            "    <main>" + NEW_LINE +
            "      <section>" + NEW_LINE +
            "        <h2>Tout ce que vous avez toujours voulu savoir sur les vieux ordis sans jamais avoir osé le demander !</h2>" + NEW_LINE +
            "          <p>" + NEW_LINE +
            "Bienvenue dans le musée virtuel d'ordinateurs mythiques de l'histoire de l'informatique. "+ 
            "Vous trouverez ici des éléments sur quelques machines qui ont marqué l'histoire de l'informatique "+
            "que cela soit par leurs caractéristiques techniques ou l'impact commercial qu'elles ont eu et qui "+
            "ont contribué au développement du secteur informatique." + NEW_LINE +
            "          </p>" + NEW_LINE +
            "      </section>" + NEW_LINE +
            "    </main>" + NEW_LINE +
            "  </body>" + NEW_LINE + 
            "</html>" + NEW_LINE + NEW_LINE;
    }

    String[][] chargerProduits(String repertoire, String prefixe){
        String[] produits = getAllFilesFromDirectory(repertoire);
        String[][] tableau = new String[length(produits)][length(CHAMPS)];
        for(int l=0; l<length(produits); l++){
            for(int c=0; c<length(tableau, 2); c++){
                tableau[l][c] = rechercherValeur(fileAsString(repertoire + "/" + prefixe + (l+1) + ".txt"), CHAMPS[c]);
            }
        }
        return tableau;
    }

    String toString(String[][] produits){
        String chaine = "";
        for(int l=0; l<length(produits, 1); l++){
            chaine = chaine + produits[l][IDX_NOM] + "(" + produits[l][IDX_DATE] + ") - " + produits[l][IDX_PRIX] + " - " + produits[l][IDX_DESCRIPTION] + "\n";
        }
        return chaine;
    }



    void algorithm(){
        String[][] tab_produits = chargerProduits("data", "produit");
        final String TITLE = "Ordinateurs mythiques";
        final String PAGE_ACCUEIL = genererAccueil(TITLE);
        stringAsFile("output/index.html", PAGE_ACCUEIL);

        for (int nb = 0; nb < length(tab_produits, 1); nb = nb + 1) {
            final String CIBLE  = "output/" + "produit" + (nb+1) + ".html";
            final String PAGE_PRODUIT = genererPageProduit(tab_produits, nb, TITLE);
            stringAsFile(CIBLE, PAGE_PRODUIT);
        }
    }
}