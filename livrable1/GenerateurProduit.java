class GenerateurProduit extends Program{
    int indiceCaractere(String texte, char c){
        int i = 0;
        boolean trouve = false;
        while (!trouve){
            if (charAt(texte, i) == c){
                trouve = true;
            }
            i++;
        }
        return i;
    }

    String rechercheValeur(String txt, int cle){
        String sous_chaine = txt;
        for (int i=0; i<cle; i++){
            sous_chaine=substring(sous_chaine, indiceCaractere(sous_chaine, ':') + 1, length(sous_chaine));
        }
        for (int i=0; i<5-cle; i++){
            sous_chaine = substring(sous_chaine, 0, (indiceCaractere(sous_chaine, '\n')));
        }
        return substring(sous_chaine, 0, length(sous_chaine)-1);
    }

    String genererProduit(String file){
        String produit = "";
        if (fileExist(file)){
            String nom = rechercheValeur(fileAsString(file), 1);
            String date = rechercheValeur(fileAsString(file), 2);
            String entreprise = rechercheValeur(fileAsString(file), 3);
            String prix = rechercheValeur(fileAsString(file), 4);
            String description = rechercheValeur(fileAsString(file), 5);
            produit = 
                "<!DOCTYPE html>\n" +
                "<html lang=\"fr\">\n" +
                "  <head>\n" +
                "    <title>" + nom + "</title>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <h1>" + nom + " (" + entreprise + ")</h1>\n" +
                "    <h2>" + prix + " (Sortie en " + date + ")</h2>\n" +
                "    <p>\n" +
                    description + "\n" +
                "    </p>\n" +
                "  </body>\n"+
                "</html>";
        } else {
            error("Le fichier n'existe pas !");
        }
        return produit;
    }

    void algorithm(){
        String arg = argument(0);
        print(genererProduit(arg));
    }
}