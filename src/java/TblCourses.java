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
 * @author Michał Śliwa
 */
@Entity
@Table(name = "Tbl_Courses")
public class TblCourses implements Serializable
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
    @Column(name = "courseName")
    private String courseName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000000000)
    @Column(name = "courseDescr")
    private String courseDescr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "courseHours")
    private int courseHours;
    @Basic(optional = false)
    @NotNull
    @Column(name = "courseSem")
    private int courseSem;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblCourses")
    private List<TblStudentCourse> tblStudentCourseList;

    public TblCourses()
    {
    }

    public TblCourses(Integer id)
    {
        this.id = id;
    }

    public TblCourses(Integer id, String courseName, String courseDescr, int courseHours, int courseSem)
    {
        this.id = id;
        this.courseName = courseName;
        this.courseDescr = courseDescr;
        this.courseHours = courseHours;
        this.courseSem = courseSem;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getCourseDescr()
    {
        return courseDescr;
    }

    public void setCourseDescr(String courseDescr)
    {
        this.courseDescr = courseDescr;
    }

    public int getCourseHours()
    {
        return courseHours;
    }

    public void setCourseHours(int courseHours)
    {
        this.courseHours = courseHours;
    }

    public int getCourseSem()
    {
        return courseSem;
    }

    public void setCourseSem(int courseSem)
    {
        this.courseSem = courseSem;
    }

    public List<TblStudentCourse> getTblStudentCourseList()
    {
        return tblStudentCourseList;
    }

    public void setTblStudentCourseList(List<TblStudentCourse> tblStudentCourseList)
    {
        this.tblStudentCourseList = tblStudentCourseList;
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
        if (!(object instanceof TblCourses))
        {
            return false;
        }
        TblCourses other = (TblCourses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "a.TblCourses[ id=" + id + " ]";
    }
    
}
