import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
/**
 * @author Michał Śliwa
 */
public class AppClient
{
    private static int studentId;
    private static int studentSemester;
    
    private static int courseId;
    private static int courseSemester;

    private static int studentMark;    
    
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
            System.out.println("------------------START------------------------");
            getCourseInfo(fileContent.get(0));
            getStudentInfo(fileContent.get(1));
            getStudnetMark();
            System.out.println(courseId);
            System.out.println(courseSemester);
            System.out.println(studentId);
            System.out.println(studentSemester);
            System.out.println(studentMark);
            System.out.println("------------------END------------------------");
            int x = 1;
        }
    }
       
    public static void getStudentInfo(String student)
    {
        EntityManagerFactory emf = javax.persistence.Persistence
                .createEntityManagerFactory("myPersistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try
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
                studentSemester = s.getSemester();
            }
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
    
    public static void getCourseInfo(String course)
    {
        EntityManagerFactory emf = javax.persistence.Persistence
                .createEntityManagerFactory("myPersistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try
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
    
    public static void getStudnetMark()
    {
        EntityManagerFactory emf = javax.persistence.Persistence
                .createEntityManagerFactory("myPersistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try
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
