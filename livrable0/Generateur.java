class Generateur extends Program{
    String fileAsString(String filename) {
        extensions.File f = new extensions.File(filename);
        String content = "";
        while (ready(f)) {
            content = content + readLine(f) + '\n';
        }
        return content;
    }

    void algorithm(){
        String texte = fileAsString("data/Zen of Python.txt");
        print(
            "<!DOCTYPE html>\n" +
            "<html lang=\"fr\">\n" +
            "    <head>\n" +
            "        <title> Zen of Python </title>\n" +
            "        <meta charset=\"utf-8\">\n" +
            "    </head>\n" +
            "    <body>\n" +
            "        <p>\n" +
                texte + "\n" +
            "        </p>\n" +
            "    </body>\n"+
            "</html>"
        );
    }
}