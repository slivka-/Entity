import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
/**
 * Calculates given students' mark deviation from median
 * @author Michał Śliwa
 */
public class AppClient
{
    //database id of given student
    private static int studentId;
    
    //database id of given course
    private static int courseId;
    //semester of given course
    private static int courseSemester;
    
    //students mark from course
    private static double studentMark;
    
    //all course marks from students of the same semester
    private static ArrayList<Double> allMarks;
    
    /**
     * Main class
     * @param args 
     */
    public static void main(String[] args)
    {
        //initialize input list
        ArrayList<String> fileContent = new ArrayList<>();
        //check if input file path is given
        String filePath = (args.length>0)? args[0] : null;
        if (filePath != null)
        {
            //read input file
            try (BufferedReader rdr = 
                    new BufferedReader(new FileReader(filePath)))
            {
                String line;
                while ((line = rdr.readLine()) != null)
                {
                    fileContent.add(line);
                }
            }
            catch (IOException ex)
            {
                System.out.println(ex.toString());
            }
        }
        //if file had content, proceed
        if (fileContent.size() > 0)
        {
            //get entity manager factory
            EntityManager em = javax.persistence.Persistence
                .createEntityManagerFactory("myPersistence")
                .createEntityManager();
            //begin transaction
            try
            {
                //get course info
                getCourseInfo(em, fileContent.get(0));
                //get student info
                getStudentInfo(em, fileContent.get(1));
                //get student mark
                getStudnetMark(em);
                //get all students marks
                getMarksOfSemesterStudents(em);               
                //write deviation from median
                System.out.println(calculateMedianPerc()+"%");                
            }
            catch (Exception ex)
            {
                System.out.println("0%");
            }            
        }
    }
    
    /**
     * Gets given course infor from database
     * @param em entity manager
     * @param course course name
     */
    public static void getCourseInfo(EntityManager em, String course)
    {
        //initialize list
        List<TblCourses> l;
        //execute query
        l = em.createNamedQuery("TblCourses.getCourse",TblCourses.class)
                .setParameter("courseName", course)
                .getResultList();
        //check if only one row is returned
        if (l.size() == 1)
        {
            //set value of course id
            courseId = l.get(0).getId();
            //set value of semester
            courseSemester = l.get(0).getCourseSem();
        }
    }
    
    /**
     * Gets given student info from databse
     * @param em entity manager
     * @param student students name and surname
     */
    public static void getStudentInfo(EntityManager em, String student)
    {
        //split parts of students name
        String[] parts = student.trim().split("\\ +");
        List<TblStudents> l;
        //execute query
        l = em.createNamedQuery("TblStudents.getStudent",TblStudents.class)
                .setParameter("firstName", parts[0])
                .setParameter("lastName", parts[1])
                .getResultList();
        //check if only one row is returned
        if (l.size() == 1)
        {
            //set value of student id
            studentId = l.get(0).getId();
        }
    }
    
    /**
     * Gets given students' mark from database
     * @param em entity manager
     */
    public static void getStudnetMark(EntityManager em)
    {
        List<TblStudentCourse> l;
        //execute query
        l = em.createNamedQuery("TblStudentCourse.getSingleMark", 
                                                    TblStudentCourse.class)
                .setParameter("courseId", courseId)
                .setParameter("studentId", studentId)
                .getResultList();
        //check if only one row is returned
        if (l.size() == 1)
        {
            TblStudentCourse sc = l.get(0);
            //set value of student mark
            studentMark = sc.getMark();
        }
    }
    
    /**
     * Gets passing marks of all students from given semester
     * @param em 
     */
    public static void getMarksOfSemesterStudents(EntityManager em)
    {
        List<TblStudentCourse> l;
        //execute query
        l = em.createNamedQuery("TblStudentCourse.getAllMarks", 
                                                TblStudentCourse.class)
                .setParameter("courseId", courseId)
                .setParameter("semester", courseSemester)
                .getResultList();
        //initialize marks list
        allMarks = new ArrayList<>();
        //write all marks to list
        for (TblStudentCourse sc : l)
        {
            allMarks.add(sc.getMark());
        }
    }
    
    /**
     * Calculates deviation from median
     * @return deviation from median in percents
     */
    public static String calculateMedianPerc()
    {
        double median;
        //sort marks list
        Collections.sort(allMarks);
        //check if number of marks is even
        if (allMarks.size() % 2 == 0)
            //calculate median for even number of marks
            median = (((double)allMarks.get(allMarks.size()/2)) + 
                    ((double)allMarks.get((allMarks.size()/2)-1)))/2;
        else
            //calculate median for uneven number of marks
            median = (double)allMarks.get(allMarks.size()/2);
        
        //calculate marks deviation from median
        double percent =  ((studentMark/median)-1)*100; 
                
        //return deviation
        return String.format("%.0f", percent);
    }
}
