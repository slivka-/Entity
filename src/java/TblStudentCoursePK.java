/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Michał Śliwa
 */
@Embeddable
public class TblStudentCoursePK implements Serializable
{

    @Basic(optional = false)
    @NotNull
    @Column(name = "studentId")
    private int studentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "courseId")
    private int courseId;

    public TblStudentCoursePK()
    {
    }

    public TblStudentCoursePK(int studentId, int courseId)
    {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId()
    {
        return studentId;
    }

    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }

    public int getCourseId()
    {
        return courseId;
    }

    public void setCourseId(int courseId)
    {
        this.courseId = courseId;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) studentId;
        hash += (int) courseId;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
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

    @Override
    public String toString()
    {
        return "a.TblStudentCoursePK[ studentId=" + studentId + ", courseId=" + courseId + " ]";
    }
    
}
