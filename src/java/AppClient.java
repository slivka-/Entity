import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
/**
 * @author Michał Śliwa
 */
public class AppClient
{
    private static int studentId;
    
    private static int courseId;
    private static int courseSemester;

    private static int studentMark;
    
    private static ArrayList<Integer> allMarks;
    
    public static void main(String[] args)
    {
        ArrayList<String> fileContent = new ArrayList<>();
        String filePath = (args.length>0)? args[0] : null;
        if (filePath != null)
        {
            try (BufferedReader rdr = new BufferedReader(new FileReader(filePath)))
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
        if (fileContent.size() > 0)
        {
            EntityManagerFactory emf = javax.persistence.Persistence
                .createEntityManagerFactory("myPersistence");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            try
            {
                getCourseInfo(em, fileContent.get(0));
                getStudentInfo(em, fileContent.get(1));
                getStudnetMark(em);
                getMarksOfSemesterStudents(em);
                System.out.println(calculateMedianPerc()+"%");
            }
            catch (Exception ex)
            {
                System.out.println(ex.toString());
                em.getTransaction().rollback();
            }
            finally
            {
                em.close();
            }
        }
    }
    
    public static void getCourseInfo(EntityManager em, String course)
    {
        List<TblCourses> l;
        String query = "SELECT t FROM TblCourses t WHERE"+
            " t.courseName = :courseName";
        l = em.createQuery(query, TblCourses.class)
                .setParameter("courseName", course)
                .getResultList();
        if(l.size() == 1)
        {
            TblCourses c = l.get(0);
            courseId = c.getId();
            courseSemester = c.getCourseSem();
        }
    }
       
    public static void getStudentInfo(EntityManager em, String student)
    {
        String[] parts = student.split("\\ ");
        List<TblStudents> l;
        String query = "SELECT t FROM TblStudents t WHERE"+
        " t.firstName = :firstName AND t.lastName = :lastName";
        l = em.createQuery(query, TblStudents.class)
                .setParameter("firstName", parts[0])
                .setParameter("lastName", parts[1])
                .getResultList();
        if(l.size() == 1)
        {
            TblStudents s = l.get(0);
            studentId = s.getId();
        }
    }
    
    public static void getStudnetMark(EntityManager em)
    {
        List<TblStudentCourse> l;
        String query = "SELECT t FROM TblStudentCourse t WHERE "+
                "t.tblStudentCoursePK = :pk";
        TblStudentCoursePK pk = new TblStudentCoursePK(studentId, courseId);
        l = em.createQuery(query, TblStudentCourse.class)
                .setParameter("pk", pk)
                .getResultList();
        if(l.size() == 1)
        {
            TblStudentCourse sc = l.get(0);
            studentMark = sc.getMark();
        }
    }
    
    public static void getMarksOfSemesterStudents(EntityManager em)
    {
        List<TblStudentCourse> l;
        String query = "SELECT sc FROM TblStudentCourse sc JOIN"+
                " sc.tblStudents s WHERE sc.mark >= 50 AND"+
                " sc.tblStudentCoursePK.courseId = :courseId"+
                " AND s.semester = :semester";
        l = em.createQuery(query, TblStudentCourse.class)
                .setParameter("courseId", courseId)
                .setParameter("semester", courseSemester)
                .getResultList();
        allMarks = new ArrayList<>();
        for (TblStudentCourse sc : l)
        {
            allMarks.add(sc.getMark());
        }
    }
    
    public static String calculateMedianPerc()
    {
        double median;
        Collections.sort(allMarks);
        if (allMarks.size() % 2 == 0)
            median = (((double)allMarks.get(allMarks.size()/2)) + ((double)allMarks.get((allMarks.size()/2)-1)))/2;
        else
            median = (double)allMarks.get(allMarks.size()/2);
        
        double percent =  (((double)studentMark/median)-1)*100; 
                
        
        return String.format("%.0f", percent);
    }
}
