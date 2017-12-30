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
    
    private static ArrayList<TblStudentCoursePK> semesterStudents;
    
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
                System.out.println("------------------START------------------------");
                getCourseInfo(em, fileContent.get(0));
                getStudentInfo(em, fileContent.get(1));
                //getStudnetMark();
                System.out.println(courseId);
                System.out.println(courseSemester);
                System.out.println(studentId);
                System.out.println(studentSemester);
                System.out.println(studentMark);
                //getSemesterStudents();
                System.out.println("------------------END------------------------");
                int x = 1;
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
        String query = "SELECT t FROM coursesTable t WHERE"+
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
        String query = "SELECT t FROM studentsTable t WHERE"+
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
    
    public static void getSemesterStudents()
    {
        EntityManagerFactory emf = javax.persistence.Persistence
                .createEntityManagerFactory("myPersistence");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try
        {
            List<TblStudents> l;
            String query = "SELECT t FROM TblStudents t WHERE"+
            " t.semester = :semester";
            l = em.createQuery(query, TblStudents.class)
                    .setParameter("semester", courseSemester)
                    .getResultList();
            semesterStudents = new ArrayList<>();
            for (TblStudents s : l)
            {
                if (s.getId() != studentId)
                    semesterStudents
                            .add(new TblStudentCoursePK(s.getId(), courseId));
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
