import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Michał Śliwa
 */
@Entity
@Table(name = "Tbl_Students")
@NamedQueries(
{
    @NamedQuery(name = "TblStudents.findAll", query = "SELECT t FROM TblStudents t")
    , @NamedQuery(name = "TblStudents.findById", query = "SELECT t FROM TblStudents t WHERE t.id = :id")
    , @NamedQuery(name = "TblStudents.findByFirstName", query = "SELECT t FROM TblStudents t WHERE t.firstName = :firstName")
    , @NamedQuery(name = "TblStudents.findByLastName", query = "SELECT t FROM TblStudents t WHERE t.lastName = :lastName")
    , @NamedQuery(name = "TblStudents.findBySemester", query = "SELECT t FROM TblStudents t WHERE t.semester = :semester")
})
public class TblStudents implements Serializable
{

    private static final long serialVersionUID = 1L;
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

    public TblStudents()
    {
    }

    public TblStudents(Integer id)
    {
        this.id = id;
    }

    public TblStudents(Integer id, String firstName, String lastName, int semester)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.semester = semester;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getSemester()
    {
        return semester;
    }

    public void setSemester(int semester)
    {
        this.semester = semester;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblStudents))
        {
            return false;
        }
        TblStudents other = (TblStudents) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "p.TblStudents[ id=" + id + " ]";
    }
    
}
