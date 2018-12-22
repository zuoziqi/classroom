package icpc.njust.test.table;

import javax.persistence.*;

/**
 * Created by DELL on 2018/12/22.
 */
@Entity
@Table(name = "class_student", schema = "", catalog = "classroom")
public class ClassStudentEntity {
    private String chooseid;
    private String id;
    private String classid;

    @Id
    @Column(name = "chooseid")
    public String getChooseid() {
        return chooseid;
    }

    public void setChooseid(String chooseid) {
        this.chooseid = chooseid;
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
    @Column(name = "classid")
    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassStudentEntity that = (ClassStudentEntity) o;

        if (chooseid != null ? !chooseid.equals(that.chooseid) : that.chooseid != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (classid != null ? !classid.equals(that.classid) : that.classid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = chooseid != null ? chooseid.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (classid != null ? classid.hashCode() : 0);
        return result;
    }
}
