import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * @author Michał Śliwa
 */
@Embeddable
public class TblStudentCoursePK implements Serializable 
{

    private static final long serialVersionUID = 1982453983745238598L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "studentId")
    private int studentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "courseId")
    private int courseId;

    /**
     * 
     */
    public TblStudentCoursePK() 
    {
        
    }

    /**
     * 
     * @param studentId
     * @param courseId 
     */
    public TblStudentCoursePK(int studentId, int courseId) 
    {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    /**
     * 
     * @return 
     */
    public int getStudentId() 
    {
        return studentId;
    }

    /**
     * 
     * @param studentId 
     */
    public void setStudentId(int studentId) 
    {
        this.studentId = studentId;
    }

    /**
     * 
     * @return 
     */
    public int getCourseId() 
    {
        return courseId;
    }

    /**
     * 
     * @param courseId 
     */
    public void setCourseId(int courseId) 
    {
        this.courseId = courseId;
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() 
    {
        int hash = 0;
        hash += studentId;
        hash += courseId;
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
        if (!(object instanceof TblStudentCoursePK)) 
        {
            return false;
        }
        TblStudentCoursePK other = (TblStudentCoursePK) object;
        if (this.studentId != other.studentId) 
        {
            return false;
        }
        if (this.courseId != other.courseId) 
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
        return "a.TblStudentCoursePK[ studentId=" 
                + studentId + ", courseId=" + courseId + " ]";
    }
    
}
