package icpc.njust.test.table;

import javax.persistence.*;

/**
 * Created by DELL on 2018/12/22.
 */
@Entity
@Table(name = "class_teacher", schema = "", catalog = "classroom")
public class ClassTeacherEntity {
    private String classid;
    private String id;
    private String classname;
    private String classtimes;

    @Id
    @Column(name = "classid")
    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    @Basic
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "classname")
    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Basic
    @Column(name = "classtimes")
    public String getClasstimes() {
        return classtimes;
    }

    public void setClasstimes(String classtimes) {
        this.classtimes = classtimes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassTeacherEntity that = (ClassTeacherEntity) o;

        if (classid != null ? !classid.equals(that.classid) : that.classid != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (classname != null ? !classname.equals(that.classname) : that.classname != null) return false;
        if (classtimes != null ? !classtimes.equals(that.classtimes) : that.classtimes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classid != null ? classid.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (classname != null ? classname.hashCode() : 0);
        result = 31 * result + (classtimes != null ? classtimes.hashCode() : 0);
        return result;
    }
}
