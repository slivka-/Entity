import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
/**
 * @author Michał Śliwa
 */
public class AppClient
{
    public static void main(String[] args)
    {
        TblStudents s = new TblStudents(2);
        s.setFirstName("Jan");
        s.setLastName("Kowalski");
        s.setSemester(2);
        persist(s);
    }
    
    public static void persist(Object object)
    {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("EntityPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try
        {
            em.persist(object);
            em.getTransaction().commit();
        }
        catch(Exception ex)
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
