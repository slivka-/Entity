import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Michał Śliwa
 */
@Entity
@Table(name = "Tbl_StudentCourse")
public class TblStudentCourse implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TblStudentCoursePK tblStudentCoursePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mark")
    private int mark;

    public TblStudentCourse()
    {
    }

    public TblStudentCourse(TblStudentCoursePK tblStudentCoursePK)
    {
        this.tblStudentCoursePK = tblStudentCoursePK;
    }

    public TblStudentCourse(TblStudentCoursePK tblStudentCoursePK, int mark)
    {
        this.tblStudentCoursePK = tblStudentCoursePK;
        this.mark = mark;
    }

    public TblStudentCourse(int studentId, int courseId)
    {
        this.tblStudentCoursePK = new TblStudentCoursePK(studentId, courseId);
    }

    public TblStudentCoursePK getTblStudentCoursePK()
    {
        return tblStudentCoursePK;
    }

    public void setTblStudentCoursePK(TblStudentCoursePK tblStudentCoursePK)
    {
        this.tblStudentCoursePK = tblStudentCoursePK;
    }

    public int getMark()
    {
        return mark;
    }

    public void setMark(int mark)
    {
        this.mark = mark;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (tblStudentCoursePK != null ? tblStudentCoursePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblStudentCourse))
        {
            return false;
        }
        TblStudentCourse other = (TblStudentCourse) object;
        if ((this.tblStudentCoursePK == null && other.tblStudentCoursePK != null) || (this.tblStudentCoursePK != null && !this.tblStudentCoursePK.equals(other.tblStudentCoursePK)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "a.TblStudentCourse[ tblStudentCoursePK=" + tblStudentCoursePK + " ]";
    }
    
}
