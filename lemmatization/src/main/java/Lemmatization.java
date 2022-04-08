import java.io.*;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Lemmatization {

    public static void main(String[] args) throws IOException {
        tokenization();
    }

    static Pattern pattern = Pattern.compile("[A-Z][a-z]+|[a-z][a-z]+|[A-Z][A-Z]+");

    public static void tokenization() throws IOException {
        String text;
        Matcher matcher;
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        String[] s;
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/Александра/IdeaProjects/lemmatization/src/lemma.txt"));
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Александра\\IdeaProjects\\lemmatization\\src\\text.txt"));
        try {
            while (reader.ready()) {
                text = reader.readLine();
                CoreDocument document = pipeline.processToCoreDocument(text);
                for (CoreLabel tok : document.tokens()) {
                    matcher = pattern.matcher(tok.toString());
                    while (matcher.find()) {
                        writer.write(tok.lemma() + ":");
                        writer.write(tok.word() + " ");
                        writer.write("\n");
                    }
                    //System.out.printf("%s\t%s%n", tok.word(), tok.lemma());
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }


    }
}
