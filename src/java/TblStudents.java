import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Automatically generated Entity class
 * @author Michał Śliwa
 */
@Entity
@Table(name = "Tbl_Students")
public class TblStudents implements Serializable
{

    private static final long serialVersionUID = 1982457982785234598L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "firstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "lastName")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semester")
    private int semester;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblStudents")
    private List<TblStudentCourse> tblStudentCourseList;

    /**
     * 
     */
    public TblStudents()
    {
    }

    /**
     * 
     * @param id 
     */
    public TblStudents(Integer id)
    {
        this.id = id;
    }

    /**
     * 
     * @param id
     * @param firstName
     * @param lastName
     * @param semester 
     */
    public TblStudents(Integer id, String firstName, 
            String lastName, int semester)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.semester = semester;
    }

    /**
     * 
     * @return 
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 
     * @return 
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * 
     * @param firstName 
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * 
     * @return 
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * 
     * @param lastName 
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * 
     * @return 
     */
    public int getSemester()
    {
        return semester;
    }

    /**
     * 
     * @param semester 
     */
    public void setSemester(int semester)
    {
        this.semester = semester;
    }

    /**
     * 
     * @return 
     */
    public List<TblStudentCourse> getTblStudentCourseList()
    {
        return tblStudentCourseList;
    }

    /**
     * 
     * @param tblStudentCourseList 
     */
    public void setTblStudentCourseList(
            List<TblStudentCourse> tblStudentCourseList)
    {
        this.tblStudentCourseList = tblStudentCourseList;
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * 
     * @param object
     * @return 
     */
    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof TblStudents))
        {
            return false;
        }
        TblStudents other = (TblStudents) object;
        if ((this.id == null && other.id != null) || 
                (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        return "a.TblStudents[ id=" + id + " ]";
    }
    
}
