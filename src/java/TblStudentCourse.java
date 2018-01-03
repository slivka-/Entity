import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Automatically generated Entity class
 * @author Michał Śliwa
 */
@Entity
@Table(name = "Tbl_StudentCourse")
public class TblStudentCourse implements Serializable
{

    private static final long serialVersionUID = 1982457984715234598L;
    
    /**
     * 
     */
    @EmbeddedId
    protected TblStudentCoursePK tblStudentCoursePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mark")
    private int mark;
    @JoinColumn(name = "courseId", referencedColumnName = "Id", 
            insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TblCourses tblCourses;
    @JoinColumn(name = "studentId", referencedColumnName = "Id", 
            insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TblStudents tblStudents;

    /**
     * 
     */
    public TblStudentCourse()
    {
    }

    /**
     * 
     * @param tblStudentCoursePK 
     */
    public TblStudentCourse(TblStudentCoursePK tblStudentCoursePK)
    {
        this.tblStudentCoursePK = tblStudentCoursePK;
    }

    /**
     * 
     * @param tblStudentCoursePK
     * @param mark 
     */
    public TblStudentCourse(TblStudentCoursePK tblStudentCoursePK, int mark)
    {
        this.tblStudentCoursePK = tblStudentCoursePK;
        this.mark = mark;
    }

    /**
     * 
     * @param studentId
     * @param courseId 
     */
    public TblStudentCourse(int studentId, int courseId)
    {
        this.tblStudentCoursePK = new TblStudentCoursePK(studentId, courseId);
    }

    /**
     * 
     * @return 
     */
    public TblStudentCoursePK getTblStudentCoursePK()
    {
        return tblStudentCoursePK;
    }

    /**
     * 
     * @param tblStudentCoursePK 
     */
    public void setTblStudentCoursePK(TblStudentCoursePK tblStudentCoursePK)
    {
        this.tblStudentCoursePK = tblStudentCoursePK;
    }

    /**
     * 
     * @return 
     */
    public int getMark()
    {
        return mark;
    }

    /**
     * 
     * @param mark 
     */
    public void setMark(int mark)
    {
        this.mark = mark;
    }

    /**
     * 
     * @return 
     */
    public TblCourses getTblCourses()
    {
        return tblCourses;
    }

    /**
     * 
     * @param tblCourses 
     */
    public void setTblCourses(TblCourses tblCourses)
    {
        this.tblCourses = tblCourses;
    }

    /**
     * 
     * @return 
     */
    public TblStudents getTblStudents()
    {
        return tblStudents;
    }

    /**
     * 
     * @param tblStudents 
     */
    public void setTblStudents(TblStudents tblStudents)
    {
        this.tblStudents = tblStudents;
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (tblStudentCoursePK != null ? 
                tblStudentCoursePK.hashCode() : 0);
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
        if (!(object instanceof TblStudentCourse))
        {
            return false;
        }
        TblStudentCourse other = (TblStudentCourse) object;
        if ((this.tblStudentCoursePK == null && 
                other.tblStudentCoursePK != null) || 
                (this.tblStudentCoursePK != null && 
                !this.tblStudentCoursePK.equals(other.tblStudentCoursePK)))
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
        return "a.TblStudentCourse[ tblStudentCoursePK=" + 
                tblStudentCoursePK + " ]";
    }
    
}
