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
@Table(name = "tbl_courses")
public class TblCourses implements Serializable 
{

    private static final long serialVersionUID = 1982457983785234598L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "courseName")
    private String courseName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
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
        
    /**
     * 
     */
    public TblCourses() 
    {
    }
    
    /**
     * 
     * @param id 
     */
    public TblCourses(Integer id) 
    {
        this.id = id;
    }
    
    /**
     * 
     * @param id
     * @param courseDescr
     * @param courseHours
     * @param courseSem 
     */
    public TblCourses(Integer id, String courseDescr, 
            int courseHours, int courseSem) 
    {
        this.id = id;
        this.courseDescr = courseDescr;
        this.courseHours = courseHours;
        this.courseSem = courseSem;
    }
    
    /**
     * 
     * @return 
     */
    public Integer getId() {
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
    public String getCourseName() 
    {
        return courseName;
    }

    /**
     * 
     * @param courseName 
     */
    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    /**
     * 
     * @return 
     */
    public String getCourseDescr() 
    {
        return courseDescr;
    }

    /**
     * 
     * @param courseDescr 
     */
    public void setCourseDescr(String courseDescr) 
    {
        this.courseDescr = courseDescr;
    }
    
    /**
     * 
     * @return 
     */
    public int getCourseHours() 
    {
        return courseHours;
    }

    /**
     * 
     * @param courseHours 
     */
    public void setCourseHours(int courseHours) 
    {
        this.courseHours = courseHours;
    }

    /**
     * 
     * @return 
     */
    public int getCourseSem() 
    {
        return courseSem;
    }

    /**
     * 
     * @param courseSem 
     */
    public void setCourseSem(int courseSem) 
    {
        this.courseSem = courseSem;
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
    public void setTblStudentCourseList
        (List<TblStudentCourse> tblStudentCourseList) 
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
        if (!(object instanceof TblCourses)) 
        {
            return false;
        }
        TblCourses other = (TblCourses) object;
        if ((this.id == null && other.id != null) 
                || (this.id != null && !this.id.equals(other.id))) 
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
        return "a.TblCourses[ id=" + id + " ]";
    }
}
