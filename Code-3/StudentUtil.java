public class StudentUtil {

    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {
        double[] gpa = new double[studentIdList.length];
        for(int i=0;i<studentIdList.length;i++){
            double average= 0.0;
            for(int j=0;j<studentsGrades[i].length;j++){
                if(studentsGrades[i][j] =='A') average = average+4.0;
                if(studentsGrades[i][j] =='B') average = average+3.0;
                if(studentsGrades[i][j] =='C') average = average+2.0;
            }
              average /= studentsGrades[i].length; 
              gpa[i] = average;
        }
        return gpa;
        
        // your code
    }
    
    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrades) {
        // perform parameter validation. Return null if passed parameters are not valid
          if(lower>higher ||higher<0 ||lower<0) return null;
        // invoke calculateGPA
      double[] gpa = calculateGPA(studentIdList, studentsGrades);
       int count = 0;
       for (int i = 0 ; i < studentIdList.length ; i++){
           if (gpa[i] >= lower && higher <=gpa[i] ){
                count++;
           }
       }
       int[] ret = new int[count];
       for (int i = 0,j=0 ; i < gpa.length ; i++){
           if (gpa[i] >= lower && higher >= gpa[i] ){
               ret[j] = studentIdList[i];
               j++;
           }
       }
       return ret;
        
        // construct the result array and return it. You would need an extra for loop to compute the size of the resulting array
    }
    
}
