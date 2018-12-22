package icpc.njust.test.table;

import javax.persistence.*;

/**
 * Created by DELL on 2018/12/22.
 */
@Entity
@Table(name = "studentstatus", schema = "", catalog = "classroom")
public class StudentstatusEntity {
    private String recordid;
    private String classid;
    private String classcnt;
    private String attend;
    private String warningnumber;

    @Id
    @Column(name = "recordid")
    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    @Basic
    @Column(name = "classid")
    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    @Basic
    @Column(name = "classcnt")
    public String getClasscnt() {
        return classcnt;
    }

    public void setClasscnt(String classcnt) {
        this.classcnt = classcnt;
    }

    @Basic
    @Column(name = "attend")
    public String getAttend() {
        return attend;
    }

    public void setAttend(String attend) {
        this.attend = attend;
    }

    @Basic
    @Column(name = "warningnumber")
    public String getWarningnumber() {
        return warningnumber;
    }

    public void setWarningnumber(String warningnumber) {
        this.warningnumber = warningnumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentstatusEntity that = (StudentstatusEntity) o;

        if (recordid != null ? !recordid.equals(that.recordid) : that.recordid != null) return false;
        if (classid != null ? !classid.equals(that.classid) : that.classid != null) return false;
        if (classcnt != null ? !classcnt.equals(that.classcnt) : that.classcnt != null) return false;
        if (attend != null ? !attend.equals(that.attend) : that.attend != null) return false;
        if (warningnumber != null ? !warningnumber.equals(that.warningnumber) : that.warningnumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = recordid != null ? recordid.hashCode() : 0;
        result = 31 * result + (classid != null ? classid.hashCode() : 0);
        result = 31 * result + (classcnt != null ? classcnt.hashCode() : 0);
        result = 31 * result + (attend != null ? attend.hashCode() : 0);
        result = 31 * result + (warningnumber != null ? warningnumber.hashCode() : 0);
        return result;
    }
}
