import java.util.Arrays;

public class SentimentAnalyzer {
    // Tip: labeled continue can be used when iterating featureSet + convert review to lower-case
    public int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords, String[] negOpinionWords) {
        int[] featureOpinions = new int[featureSet.length]; // output
        // your code ~ you will be invoking getOpinionOnFeature
        for (int i = 0 ; i < featureSet.length ; i++){
            for (int j = 0 ; j < featureSet[i].length ; j++){
                featureOpinions[i] = featureOpinions[i]+ getOpinionOnFeature(review.toLowerCase(), featureSet[i][j],posOpinionWords , negOpinionWords);
            }
        }
        return featureOpinions;
    }

    // First invoke checkForWasPhrasePattern and
    // if it cannot find an opinion only then invoke checkForOpinionFirstPattern
    private int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        // your code
        int op= 0;
        op = op + checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
        op = op + checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
        return op;
    }

    // Tip: Look at String API doc. Methods like indexOf, length, substring(beginIndex), startsWith can come into play
    // Return 1 if positive opinion found, -1 for negative opinion, 0 for no opinion
    // You can first look for positive opinion. If not found, only then you can look for negative opinion
    private int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        int op = 0;
        String pattern = feature + " was ";
        // your code
        int patternPosition = review.indexOf(pattern);
        patternPosition += pattern.length();
        String tempReview;

        for(int i=0;i<posOpinionWords.length;i++){
          tempReview = review.substring(patternPosition , posOpinionWords[i].length() + patternPosition);
          if (posOpinionWords[i].equals(tempReview)){
              op++;
          }
        }
        for(int i=0;i<negOpinionWords.length;i++){
          tempReview = review.substring(patternPosition , negOpinionWords[i].length() + patternPosition);
          if ( negOpinionWords[i].equals(tempReview)){
              op--;
          }
        }

        return op;
    }

    // You can first look for positive opinion. If not found, only then you can look for negative opinion
    private int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {
        // Extract sentences as feature might appear multiple times.
        // split() takes a regular expression and "." is a special character
        // for regular expression. So, escape it to make it work!!
        String[] sentences = review.split("\\.");
        int op = 0;
        // your code for processing each sentence. You can return if opinion is found in a sentence (no need to process subsequent ones)
        for(int i=0;i<sentences.length;i++){
          int featurePosition = sentences[i].indexOf(feature);
          String tempReview;

          for(int j=0;j<posOpinionWords.length;j++){
            if (featurePosition - posOpinionWords[j].length() > 0) {
                tempReview = sentences[i].substring(featurePosition - posOpinionWords[j].length() - 1, featurePosition - 1);
                if (posOpinionWords[j].equals(tempReview)) {
                    op++;
                }
            }
          }
          for(int k=0;k<negOpinionWords.length;k++){
            if (featurePosition - negOpinionWords[k].length() > 0) {
            tempReview = sentences[i].substring(featurePosition - negOpinionWords[k].length() - 1, featurePosition - 1);
            if (negOpinionWords[k].equals(tempReview)) {
                op--;
            }
          }
        }
      }
        return op;
    }

    public static void main(String[] args) {
        //String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";

        String review = "Sorry OG, but you just lost some loyal customers. Horrible service, no smile or greeting just attitude. The breadsticks were stale and burnt, appetizer was cold and the food came out before the salad.";

        String[][] featureSet = {
                { "ambiance", "ambience", "atmosphere", "decor" },
                { "dessert", "ice cream", "desert" },
                { "food" },
                { "soup" },
                { "service", "management", "waiter", "waitress", "bartender", "staff", "server" } };
        String[] posOpinionWords = { "good", "fantastic", "friendly", "great", "excellent", "amazing", "awesome",
                "delicious" };
        String[] negOpinionWords = { "slow", "bad", "horrible", "awful", "unprofessional", "poor" };
        SentimentAnalyzer analyzer = new SentimentAnalyzer();
        int[] featureOpinions = analyzer.detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
        System.out.println("Opinions on Features: " + Arrays.toString(featureOpinions));
    }
}
